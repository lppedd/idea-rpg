package com.github.lppedd.rpg.lang.lexer.mini;

/**
 * @author Edoardo Luppi
 */
public class KeywordsRpgLexer extends CollectingFlexLexer {
  public KeywordsRpgLexer() {
    super(new KeywordsRpgFlexLexer(null));
  }
}
