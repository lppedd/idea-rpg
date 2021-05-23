package com.github.lppedd.rpg.lang.lexer.token;

import com.github.lppedd.rpg.lang.extensions.CharSequenceExtensions;
import com.intellij.psi.TokenType;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class BlankTokenLexer implements TokenLexer {
  private final int startIndex;
  private final int endIndex;

  public BlankTokenLexer(final int startIndex, final int endIndex) {
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    final var endIndex = Math.min(this.endIndex, line.length());
    final var value = line.subSequence(startIndex, endIndex);
    final var indexOfNonWhitespace = value.indexOfNonWhitespace();

    if (indexOfNonWhitespace < 0) {
      lexemes.add(new LineLexeme(TokenType.WHITE_SPACE, startIndex, endIndex));
      return;
    }

    if (indexOfNonWhitespace > 0) {
      lexemes.add(new LineLexeme(TokenType.WHITE_SPACE, startIndex, startIndex + indexOfNonWhitespace));
    }

    lexemes.add(new LineLexeme(TokenType.BAD_CHARACTER, startIndex + indexOfNonWhitespace, endIndex));
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
