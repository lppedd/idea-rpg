package com.github.lppedd.rpg.lang.lexer.token;

import com.intellij.psi.tree.IElementType;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

/**
 * @author Edoardo Luppi
 */
@EqualsAndHashCode
public class LineLexeme implements Lexeme {
  private final IElementType type;
  private final int startIndex;
  private final int endIndex;

  public LineLexeme(@NotNull final IElementType type, final int startIndex, final int endIndex) {
    this.type = type;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  public IElementType getType() {
    return type;
  }

  @Override
  public int getStartIndex() {
    return startIndex;
  }

  @Override
  public int getEndIndex() {
    return endIndex;
  }

  @Override
  public Lexeme shift(final int positions) {
    return positions == 0
        ? this
        : new LineLexeme(type, positions + startIndex, positions + endIndex);
  }

  @Override
  public String toString() {
    return LineLexeme.class.getSimpleName() + "(" + type + ", " + startIndex + ":" + endIndex + ')';
  }
}
