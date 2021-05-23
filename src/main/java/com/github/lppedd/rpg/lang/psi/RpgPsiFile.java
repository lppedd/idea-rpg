package com.github.lppedd.rpg.lang.psi;

import com.github.lppedd.rpg.lang.RpgFileType;
import com.github.lppedd.rpg.lang.RpgLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

/**
 * @author Edoardo Luppi
 */
public class RpgPsiFile extends PsiFileBase {
  public RpgPsiFile(final FileViewProvider viewProvider) {
    super(viewProvider, RpgLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return RpgFileType.INSTANCE;
  }
}
