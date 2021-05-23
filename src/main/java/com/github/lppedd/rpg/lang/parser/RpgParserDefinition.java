package com.github.lppedd.rpg.lang.parser;

import com.github.lppedd.rpg.lang.RpgLanguage;
import com.github.lppedd.rpg.lang.lexer.FixedRpgLexer;
import com.github.lppedd.rpg.lang.lexer.RpgTokenType;
import com.github.lppedd.rpg.lang.psi.RpgPsiFile;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.FakePsiElement;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

/**
 * @author Edoardo Luppi
 */
public class RpgParserDefinition implements ParserDefinition {
  public static final IFileElementType FILE = new IFileElementType("RPG File", RpgLanguage.INSTANCE);

  @NotNull
  @Override
  public Lexer createLexer(final Project project) {
    return new FixedRpgLexer();
  }

  @NotNull
  @Override
  public PsiParser createParser(final Project project) {
    return new RpgPsiParser();
  }

  @NotNull
  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return RpgTokenType.COMMENT_TYPES;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return RpgTokenType.STRING_TYPES;
  }

  @NotNull
  @Override
  public PsiElement createElement(final ASTNode astNode) {
    return createPsiElementFromNode(astNode);
  }

  @NotNull
  @Override
  public PsiFile createFile(@NotNull final FileViewProvider viewProvider) {
    return new RpgPsiFile(viewProvider);
  }

  private PsiElement createPsiElementFromNode(final ASTNode astNode) {
    return new FakePsiElement() {
      @Override
      public PsiElement getParent() {
        return null;
      }

      @Override
      public TextRange getTextRange() {
        return astNode.getTextRange();
      }
    };
  }
}
