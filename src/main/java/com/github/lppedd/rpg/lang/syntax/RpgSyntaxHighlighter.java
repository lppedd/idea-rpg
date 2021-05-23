package com.github.lppedd.rpg.lang.syntax;

import com.github.lppedd.rpg.lang.lexer.FixedRpgLexer;
import com.github.lppedd.rpg.lang.lexer.RpgTokenType;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Edoardo Luppi
 */
public class RpgSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey[] KEYWORD_ATTR = {DefaultLanguageHighlighterColors.KEYWORD};
  public static final TextAttributesKey[] INNER_KEYWORD_ATTR = {DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL};
  public static final TextAttributesKey[] IDENTIFIER_ATTR = {DefaultLanguageHighlighterColors.IDENTIFIER};
  public static final TextAttributesKey[] OPERATOR_ATTR = {DefaultLanguageHighlighterColors.OPERATION_SIGN};
  public static final TextAttributesKey[] NUMBER_ATTR = {DefaultLanguageHighlighterColors.NUMBER};
  public static final TextAttributesKey[] STRING_ATTR = {DefaultLanguageHighlighterColors.STRING};
  public static final TextAttributesKey[] ERROR_ATTR = {CodeInsightColors.ERRORS_ATTRIBUTES};
  public static final TextAttributesKey[] BLOCK_COMMENT_ATTR = {DefaultLanguageHighlighterColors.BLOCK_COMMENT};
  public static final TextAttributesKey[] WHITE_SPACE_ATTR = {DefaultLanguageHighlighterColors.DOC_COMMENT};

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new FixedRpgLexer();
  }

  @NotNull
  @Override
  @SuppressWarnings("ControlFlowStatementWithoutBraces")
  public TextAttributesKey @NotNull [] getTokenHighlights(@NotNull final IElementType tokenType) {
    if (tokenType == RpgTokenType.SPECIFICATION) return KEYWORD_ATTR;
    if (tokenType == RpgTokenType.INNER_SPECIFICATION) return INNER_KEYWORD_ATTR;
    if (tokenType == RpgTokenType.COMMENT) return BLOCK_COMMENT_ATTR;
    if (tokenType == RpgTokenType.IDENTIFIER) return IDENTIFIER_ATTR;
    if (tokenType == RpgTokenType.LITERAL) return STRING_ATTR;
    if (tokenType == RpgTokenType.NUMERIC_LITERAL) return NUMBER_ATTR;
    if (tokenType == RpgTokenType.STRING_LITERAL) return STRING_ATTR;
    if (tokenType == RpgTokenType.LPAREN) return KEYWORD_ATTR;
    if (tokenType == RpgTokenType.RPAREN) return KEYWORD_ATTR;
    if (tokenType == TokenType.WHITE_SPACE) return WHITE_SPACE_ATTR;
    if (tokenType == TokenType.BAD_CHARACTER) return ERROR_ATTR;
    throw new IllegalStateException();
  }
}
