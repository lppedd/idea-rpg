package com.github.lppedd.rpg.lang.lexer.token;

import com.intellij.psi.tree.IElementType;

/**
 * @author Edoardo Luppi
 */
public interface Lexeme {
  IElementType getType();
  int getStartIndex();
  int getEndIndex();
  Lexeme shift(final int positions);
}
