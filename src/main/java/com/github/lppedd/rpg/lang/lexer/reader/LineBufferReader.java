package com.github.lppedd.rpg.lang.lexer.reader;

/**
 * @author Edoardo Luppi
 */
public interface LineBufferReader {
  /** Reads an entire line out of the buffer. */
  CharSequence readLine();

  /** The index inside the buffer at which the reader currently points to. */
  int getCurrentIndex();

  /** The start index of the last read line. */
  int getLineStartIndex();
}
