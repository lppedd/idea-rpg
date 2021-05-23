package com.github.lppedd.rpg.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Edoardo Luppi
 */
public class RpgPsiParser implements PsiParser {
  @NotNull
  @Override
  public ASTNode parse(@NotNull final IElementType root, @NotNull final PsiBuilder builder) {
    builder.setDebugMode(true);
    final var rootMarker = builder.mark();

    while (builder.getTokenType() != null && !builder.eof()) {
      builder.advanceLexer();
    }

    rootMarker.done(root);
    return builder.getTreeBuilt();
  }
}
