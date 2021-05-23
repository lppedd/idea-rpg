package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.lang.lexer.token.BlankTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.CommentTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.IdentifierTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.IntegerLiteralTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.KeywordTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.KeywordsTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.SpecificationTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.TokenLexer;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * Handles a file description line, identified by the specification type '{@code F}'.
 *
 * @author Edoardo Luppi
 */
public class FileLineLexer implements LineLexer {
  private static final TokenLexer[] TOKEN_LEXERS = {
      new CommentTokenLexer(0, 5),
      new SpecificationTokenLexer(5, 6),
      new IdentifierTokenLexer(6, 16),      // File name
      new KeywordTokenLexer(16, 17),        // File type
      new KeywordTokenLexer(17, 18),        // File designation
      new KeywordTokenLexer(18, 19),        // End of file
      new KeywordTokenLexer(19, 20),        // File addition
      new KeywordTokenLexer(20, 21),        // Sequence
      new KeywordTokenLexer(21, 22),        // File format
      new IntegerLiteralTokenLexer(22, 27), // Record length
      new KeywordTokenLexer(27, 28),        // Limits processing
      new IntegerLiteralTokenLexer(28, 33), // Length of key field
      new KeywordTokenLexer(33, 34),        // Record address type
      new KeywordTokenLexer(34, 35),        // File organization
      new KeywordTokenLexer(35, 42),        // Device
      new BlankTokenLexer(42, 43),
      new KeywordsTokenLexer(43, 80),       // Keywords
      new CommentTokenLexer(80, Integer.MAX_VALUE),
  };

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    for (final var tokenLexer : TOKEN_LEXERS) {
      if (tokenLexer.getStartIndex() >= line.length()) {
        break;
      }

      tokenLexer.lex(line, lexemes);
    }
  }
}
