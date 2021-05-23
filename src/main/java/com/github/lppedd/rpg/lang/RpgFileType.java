package com.github.lppedd.rpg.lang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Edoardo Luppi
 */
public class RpgFileType extends LanguageFileType {
  public static final LanguageFileType INSTANCE = new RpgFileType();

  private RpgFileType() {
    super(RpgLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "RPG file";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "RPG language file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "rpg";
  }

  @Override
  public Icon getIcon() {
    return null;
  }
}
