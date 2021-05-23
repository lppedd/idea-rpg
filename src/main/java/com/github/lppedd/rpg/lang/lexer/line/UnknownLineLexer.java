package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.token.CommentTokenLexer;
import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.LineLexeme;
import com.intellij.psi.TokenType;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * Handles lines which do not have a specification type.
 *
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class UnknownLineLexer implements LineLexer {
  public static final CommentTokenLexer COMMENT_TOKEN_LEXER = new CommentTokenLexer(0, 5);

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    COMMENT_TOKEN_LEXER.lex(line, lexemes);
    final var lineLength = line.length();

    if (lineLength > 5) {
      final var type = line.subSequence(5, lineLength).isBlank()
          ? TokenType.WHITE_SPACE
          : TokenType.BAD_CHARACTER;

      lexemes.add(new LineLexeme(type, 5, lineLength));
    }
  }
}
