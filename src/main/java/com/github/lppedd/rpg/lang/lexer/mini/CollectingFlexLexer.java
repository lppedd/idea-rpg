package com.github.lppedd.rpg.lang.lexer.mini;

import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.LineLexeme;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * @author Edoardo Luppi
 */
class CollectingFlexLexer extends FlexAdapter {
  CollectingFlexLexer(@NotNull final FlexLexer flex) {
    super(flex);
  }

  public void collect(final @NotNull Queue<? super Lexeme> lexemes) {
    while (true) {
      final var type = getTokenType();

      if (type == null) {
        break;
      }

      final var startIndex = getTokenStart();
      final var endIndex = getTokenEnd();
      final var lexeme = new LineLexeme(type, startIndex, endIndex);
      lexemes.add(lexeme);
      advance();
    }
  }
}
