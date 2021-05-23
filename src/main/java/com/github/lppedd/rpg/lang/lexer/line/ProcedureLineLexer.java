package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.token.BlankTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.CommentTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.IdentifierTokenLexer;
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
 * Handles a Procedure line, identified by the specification type '{@code P}'.
 *
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class ProcedureLineLexer implements LineLexer {
  private static final TokenLexer[] TOKEN_LEXERS = {
      new CommentTokenLexer(0, 5),
      new SpecificationTokenLexer(5, 6),
      new IdentifierTokenLexer(6, 21),      // Procedure name
      new BlankTokenLexer(21, 23),          // Blank positions
      new KeywordTokenLexer(23, 24),        // Begin/End
      new BlankTokenLexer(24, 43),          // Blank positions
      new KeywordsTokenLexer(43, 80),       // Procedure keywords
      new CommentTokenLexer(80, Integer.MAX_VALUE),
  };

  private static final TokenLexer[] CONTINUED_TOKEN_LEXERS = {
      new CommentTokenLexer(0, 5),
      new SpecificationTokenLexer(5, 6),
      new LongIdentifierTokenLexer(6, 80),  // Procedure continued name
      new CommentTokenLexer(80, Integer.MAX_VALUE),
  };

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    // The procedure specification line has two variants.
    // A procedure name can spawn more than 15 characters if it terminates with '...'
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

    final var identifier = line.subSequence(5, Math.min(80, lineLength)).trimEnd();
    return identifier.endsWith("...") && IDENTIFIER_PATTEN.matcher(identifier).matches();
  }
}
