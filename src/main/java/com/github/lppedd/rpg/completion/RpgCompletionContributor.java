package com.github.lppedd.rpg.completion;

import com.github.lppedd.rpg.lang.psi.RpgPsiFile;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionInitializationContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author Edoardo Luppi
 */
public class RpgCompletionContributor extends CompletionContributor {
  @Override
  public void beforeCompletion(@NotNull final CompletionInitializationContext context) {
    if (context.getFile() instanceof RpgPsiFile) {
      context.setDummyIdentifier("");
    }
  }
}
