package com.github.lppedd.rpg.lang;

import com.intellij.lang.Language;

/**
 * @author Edoardo Luppi
 */
public class RpgLanguage extends Language {
  public static final Language INSTANCE = new RpgLanguage();

  private RpgLanguage() {
    super("RPG");
  }
}
