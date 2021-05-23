package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.lang.lexer.token.CommentTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.TokenLexer;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * Handles comment lines, identified by a specification type '{@code *}'.
 *
 * @author Edoardo Luppi
 */
public class CommentLineLexer implements LineLexer {
  private static final TokenLexer[] TOKEN_LEXERS = {
      new CommentTokenLexer(0, 5),
      new CommentTokenLexer(5, 80),
      new CommentTokenLexer(80, Integer.MAX_VALUE)
  };

  @Override
  public void lex(@NotNull final CharSequence line, final @NotNull Queue<? super Lexeme> lexemes) {
    for (final var tokenLexer : TOKEN_LEXERS) {
      if (tokenLexer.getStartIndex() >= line.length()) {
        break;
      }

      tokenLexer.lex(line, lexemes);
    }
  }
}
