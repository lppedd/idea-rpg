package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.token.CommentTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.IdentifierTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.IntegerLiteralTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.KeywordTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.KeywordsTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.LongIdentifierTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.SpecificationTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.TokenLexer;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

import static com.github.lppedd.rpg.lang.lexer.token.LongIdentifierTokenLexer.IDENTIFIER_PATTEN;

/**
 * Handles definition lines, identified by a specification type '{@code D}'.
 *
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class DefinitionLineLexer implements LineLexer {
  private static final TokenLexer[] TOKEN_LEXERS = {
      new CommentTokenLexer(0, 5),
      new SpecificationTokenLexer(5, 6),
      new IdentifierTokenLexer(6, 21),      // Variable name
      new KeywordTokenLexer(21, 22),        // Externally described
      new KeywordTokenLexer(22, 23),        // Data structure type
      new KeywordTokenLexer(23, 25),        // Definition type
      new IntegerLiteralTokenLexer(25, 32), // From
      new IntegerLiteralTokenLexer(32, 39), // To/Length
      new KeywordTokenLexer(39, 41),        // Internal data type
      new IntegerLiteralTokenLexer(41, 43), // Decimal positions
      new KeywordsTokenLexer(43, 80),       // Keywords
      new CommentTokenLexer(80, Integer.MAX_VALUE)
  };

  private static final TokenLexer[] CONTINUED_TOKEN_LEXERS = {
      new CommentTokenLexer(0, 5),
      new SpecificationTokenLexer(5, 6),
      new LongIdentifierTokenLexer(6, 80),
      new CommentTokenLexer(80, Integer.MAX_VALUE),
  };

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    // A definition identifier can spawn more then 15 characters, if it is valid and it ends with '...'.
    // Thus we first need to check that, and pick the correct token lexers.
    //      1     7                                                                          80
    // E.g. '9001 DMYLONGIDENTIFIERNAME...                                                   '
    final var tokenLexers = isContinuedLine(line)
        ? CONTINUED_TOKEN_LEXERS
        : TOKEN_LEXERS;

    for (final var tokenLexer : tokenLexers) {
      if (tokenLexer.getStartIndex() >= line.length()) {
        break;
      }

      tokenLexer.lex(line, lexemes);
    }
  }

  private boolean isContinuedLine(final CharSequence line) {
    final var lineLength = line.length();

    if (lineLength < 10) {
      return false;
    }

    final var identifier = line.subSequence(6, Math.min(80, lineLength)).trimEnd();
    return identifier.endsWith("...") && IDENTIFIER_PATTEN.matcher(identifier).matches();
  }
}
