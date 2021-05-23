package com.github.lppedd.rpg.lang.lexer.reader;

import com.github.lppedd.rpg.lang.extensions.CharSequenceExtensions;
import lombok.experimental.ExtensionMethod;
import org.jetbrains.annotations.NotNull;

/**
 * @author Edoardo Luppi
 */
@ExtensionMethod(CharSequenceExtensions.class)
public class BasicLineBufferReader implements LineBufferReader {
  private final CharSequence buffer;
  private int lineStartIndex;
  private int currentIndex;

  public BasicLineBufferReader(@NotNull final CharSequence buffer) {
    this.buffer = buffer;
  }

  @NotNull
  @Override
  public CharSequence readLine() {
    assert currentIndex < buffer.length();

    lineStartIndex = currentIndex;
    final var eolIndex = buffer.indexOf('\n', currentIndex);

    if (eolIndex >= 0) {
      final var line = buffer.subSequence(currentIndex, eolIndex + 1);
      currentIndex = eolIndex + 1;
      return line;
    }

    final var line = buffer.subSequence(currentIndex, buffer.length());
    currentIndex = buffer.length();
    return line;
  }

  @Override
  public int getLineStartIndex() {
    return lineStartIndex;
  }

  @Override
  public int getCurrentIndex() {
    return currentIndex;
  }
}
