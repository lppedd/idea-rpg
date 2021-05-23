package com.github.lppedd.rpg.lang.lexer.token;

import com.github.lppedd.rpg.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.RpgTokenType;
import com.intellij.psi.TokenType;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.UNICODE_CASE;

/**
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class LongIdentifierTokenLexer implements TokenLexer {
  public static final Pattern IDENTIFIER_PATTEN =
      Pattern.compile("^ *[a-z][a-z0-9_]*\\.\\.\\. *", CASE_INSENSITIVE | UNICODE_CASE);

  private final int startIndex;
  private final int endIndex;

  public LongIdentifierTokenLexer(final int startIndex, final int endIndex) {
    assert startIndex < endIndex && endIndex > 0;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  @SuppressWarnings("DuplicatedCode")
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    // The identifier could be incomplete because of the line terminating too soon
    final var endIndex = Math.min(this.endIndex, line.length());
    final var value = line.subSequence(startIndex, endIndex);

    if (value.isBlank()) {
      lexemes.add(new LineLexeme(TokenType.WHITE_SPACE, startIndex, endIndex));
      return;
    }

    final var matcher = IDENTIFIER_PATTEN.matcher(value);

    if (matcher.find()) {
      final var validStartIndex = startIndex + matcher.start();
      final var validEndIndex = startIndex + matcher.end();
      lexemes.add(new LineLexeme(RpgTokenType.IDENTIFIER, validStartIndex, validEndIndex));

      // The identifier might be partially correct
      if (validEndIndex < endIndex) {
        // If the remaining part of the identifier is blank, then the identifier is ok.
        // Otherwise it's an error
        final var type = value.subSequence(matcher.end(), value.length()).isBlank()
            ? TokenType.WHITE_SPACE
            : TokenType.BAD_CHARACTER;

        lexemes.add(new LineLexeme(type, validEndIndex, endIndex));
      }
    } else {
      // The identifier is totally incorrect
      lexemes.add(new LineLexeme(TokenType.BAD_CHARACTER, startIndex, endIndex));
    }
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
