package com.github.lppedd.rpg.lang.lexer.token;

import com.github.lppedd.rpg.extensions.CharSequenceExtensions;
import com.github.lppedd.rpg.lang.lexer.RpgTokenType;
import com.intellij.psi.TokenType;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

@ExtensionMethod(CharSequenceExtensions.class)
public class SpecificationTokenLexer implements TokenLexer {
  private final int startIndex;
  private final int endIndex;

  public SpecificationTokenLexer(final int startIndex, final int endIndex) {
    assert startIndex < endIndex && endIndex > 0;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    final var endIndex = Math.min(this.endIndex, line.length());
    final var value = line.subSequence(startIndex, endIndex);
    final var type = value.isBlank()
        ? TokenType.WHITE_SPACE
        : RpgTokenType.SPECIFICATION;

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
}
