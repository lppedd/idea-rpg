package com.github.lppedd.rpg.lang.lexer.token;

import com.github.lppedd.rpg.lang.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.mini.KeywordsRpgLexer;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class KeywordsTokenLexer implements TokenLexer {
  private final KeywordsRpgLexer keywordsRpgLexer = new KeywordsRpgLexer();
  private final int startIndex;
  private final int endIndex;

  public KeywordsTokenLexer(final int startIndex, final int endIndex) {
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    final var endIndex = Math.min(this.endIndex, line.length());
    keywordsRpgLexer.start(line, startIndex, endIndex, keywordsRpgLexer.getState());
    keywordsRpgLexer.collect(lexemes);
  }

  @Override
  public int getStartIndex() {
    return startIndex;
  }

  @Override
  public int getEndIndex() {
    return endIndex;
  }
}
