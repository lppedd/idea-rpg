package com.github.lppedd.rpg.lang.lexer;

import com.github.lppedd.rpg.lang.lexer.line.LineLexer;
import com.github.lppedd.rpg.lang.lexer.line.RootLineLexer;
import com.github.lppedd.rpg.lang.lexer.reader.BasicLineBufferReader;
import com.github.lppedd.rpg.lang.lexer.reader.LineBufferReader;
import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Queue;

/**
 * An RPG fixed format lexer.
 *
 * @author Edoardo Luppi
 */
public class FixedRpgLexer extends LexerBase {
  private static final int STATE_INITIAL = 0;
  private static final int STATE_EOF = 1;

  private final LineLexer rootLineLexer = new RootLineLexer();

  private CharSequence buffer;
  private int endOffset;
  private int state;
  private LineBufferReader bufferReader;
  private Queue<Lexeme> lexemes;

  @Nullable
  private Lexeme currentLexeme;

  @Override
  public void start(
      @NotNull final CharSequence buffer,
      final int startOffset,
      final int endOffset,
      final int initialState) {
    state = STATE_INITIAL;
    this.buffer = buffer;
    this.endOffset = endOffset;

    bufferReader = new BasicLineBufferReader(buffer);
    lexemes = new LineAwareLexemeQueue(bufferReader, new LexemeQueue());

    // Lex first line immediately
    advance();
  }

  @Override
  public void advance() {
    currentLexeme = lexemes.poll();

    if (currentLexeme == null) {
      if (bufferReader.getCurrentIndex() < endOffset) {
        lexLine();
        currentLexeme = lexemes.poll();
      } else {
        state = STATE_EOF;
      }
    }
  }

  private void lexLine() {
    final var line = bufferReader.readLine();
    rootLineLexer.lex(line, lexemes);
  }

  @Override
  @Nullable
  public IElementType getTokenType() {
    return currentLexeme != null
        ? currentLexeme.getType()
        : null;
  }

  @Override
  public int getTokenStart() {
    assert currentLexeme != null;
    return currentLexeme.getStartIndex();
  }

  @Override
  public int getTokenEnd() {
    assert currentLexeme != null;
    return currentLexeme.getEndIndex();
  }

  @Override
  @NotNull
  public CharSequence getBufferSequence() {
    return buffer;
  }

  @Override
  public int getBufferEnd() {
    return endOffset;
  }

  @Override
  public int getState() {
    return state;
  }
}
