package com.github.lppedd.rpg.lang.lexer.token;

import com.github.lppedd.rpg.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.RpgTokenType;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class CommentTokenLexer implements TokenLexer {
  private final int startIndex;
  private final int endIndex;

  public CommentTokenLexer(final int startIndex, final int endIndex) {
    assert startIndex < endIndex && endIndex > 0;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    final var endIndex = Math.min(this.endIndex, line.length());
    lexemes.add(new LineLexeme(RpgTokenType.COMMENT, startIndex, endIndex));
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
