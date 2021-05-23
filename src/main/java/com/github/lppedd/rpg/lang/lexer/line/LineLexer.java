package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * A line lexer is responsible for reading a line and splitting it
 * into sequential tokens.
 *
 * @author Edoardo Luppi
 */
public interface LineLexer {
  /**
   * @param line    the line to be lexed.<br>
   *                The end-of-line character is never present
   * @param lexemes the queue which holds all the lexed tokens
   */
  void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes);
}
