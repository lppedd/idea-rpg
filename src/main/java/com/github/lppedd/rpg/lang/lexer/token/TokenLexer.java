package com.github.lppedd.rpg.lang.lexer.token;

import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * @author Edoardo Luppi
 */
public interface TokenLexer {
  /**
   * Lex the token from the inputted line.<br>
   * Called only if {@link #getStartIndex} is smaller then the {@code line}'s length.
   *
   * @param line    the line inside which this token is present, at a specific range.<br>
   *                The end-of-line character is never present
   * @param lexemes the queue which holds all the lexed tokens
   */
  void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes);

  /** The start index of this token inside a line. */
  int getStartIndex();

  /** The end index of this token inside a line. */
  int getEndIndex();
}
