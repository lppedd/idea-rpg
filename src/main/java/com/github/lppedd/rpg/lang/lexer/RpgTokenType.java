package com.github.lppedd.rpg.lang.lexer;

import com.github.lppedd.rpg.lang.RpgLanguage;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author Edoardo Luppi
 */
public class RpgTokenType extends IElementType {
  public static final IElementType SPECIFICATION = new RpgTokenType("SPECIFICATION");
  public static final IElementType INNER_SPECIFICATION = new RpgTokenType("INNER_SPECIFICATION");
  public static final IElementType IDENTIFIER = new RpgTokenType("IDENTIFIER");
  public static final IElementType LITERAL = new RpgTokenType("LITERAL");
  public static final IElementType NUMERIC_LITERAL = new RpgTokenType("NUMBER_LITERAL");
  public static final IElementType STRING_LITERAL = new RpgTokenType("STRING_LITERAL");
  public static final IElementType COMMENT = new RpgTokenType("COMMENT");
  public static final IElementType LPAREN = new RpgTokenType("LPAREN (");
  public static final IElementType RPAREN = new RpgTokenType("RPAREN )");

  public static final TokenSet COMMENT_TYPES = TokenSet.create(COMMENT, TokenType.WHITE_SPACE);
  public static final TokenSet STRING_TYPES = TokenSet.create(STRING_LITERAL);

  public RpgTokenType(final String debugName) {
    super(debugName, RpgLanguage.INSTANCE);
  }
}
