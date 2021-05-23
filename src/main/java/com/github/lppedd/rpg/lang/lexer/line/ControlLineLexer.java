package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.lang.lexer.token.CommentTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.KeywordsTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.SpecificationTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.TokenLexer;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * Handles control lines, identified by a specification type '{@code C}'.
 *
 * @author Edoardo Luppi
 */
public class ControlLineLexer implements LineLexer {
  private static final TokenLexer[] TOKEN_LEXERS = {
      new CommentTokenLexer(0, 5),
      new SpecificationTokenLexer(5, 6),
      new KeywordsTokenLexer(6, 80),
      new CommentTokenLexer(80, Integer.MAX_VALUE)
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
