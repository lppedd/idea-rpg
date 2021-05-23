package com.github.lppedd.rpg.lang.lexer.token;

import com.github.lppedd.rpg.lang.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.RpgTokenType;
import com.intellij.psi.TokenType;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class IntegerLiteralTokenLexer implements TokenLexer {
  private final int startIndex;
  private final int endIndex;

  public IntegerLiteralTokenLexer(final int startIndex, final int endIndex) {
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    final var endIndex = Math.min(this.endIndex, line.length());
    final var value = line.subSequence(startIndex, endIndex);

    if (value.isBlank()) {
      lexemes.add(new LineLexeme(TokenType.WHITE_SPACE, startIndex, endIndex));
      return;
    }

    final var type = checkValue(value)
        ? RpgTokenType.NUMERIC_LITERAL
        : TokenType.BAD_CHARACTER;

    lexemes.add(new LineLexeme(type, startIndex, endIndex));
  }

  @Override
  public int getStartIndex() {
    return startIndex;
  }

  @Override
  public int getEndIndex() {
    return endIndex;
  }

  private boolean checkValue(final CharSequence value) {
    // A couple examples:
    // |00001|  valid
    // |   10|  valid
    // |    0|  valid
    // |   1 |  invalid
    // |10   |  invalid
    var hasDigit = false;

    for (var i = 0; i < value.length(); i++) {
      final var c = value.charAt(i);

      // A whitespace cannot follow a digit
      if (hasDigit && Character.isWhitespace(c)) {
        return false;
      }

      if (Character.isDigit(c)) {
        hasDigit = true;
      }
    }

    return hasDigit;
  }
}
