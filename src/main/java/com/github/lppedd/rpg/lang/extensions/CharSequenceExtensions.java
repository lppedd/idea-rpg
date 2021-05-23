package com.github.lppedd.rpg.lang.extensions;

/**
 * @author Edoardo Luppi
 */
public final class CharSequenceExtensions {
  public static boolean isBlank(final CharSequence value) {
    for (var i = 0; i < value.length(); i++) {
      if (!Character.isWhitespace(value.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  public static boolean isNotBlank(final CharSequence value) {
    return !isBlank(value);
  }

  public static int indexOf(final CharSequence value, final char c) {
    return indexOf(value, c, 0);
  }

  public static int indexOf(final CharSequence value, final char c, final int startIndex) {
    for (var i = startIndex; i < value.length(); i++) {
      if (value.charAt(i) == c) {
        return i;
      }
    }

    return -1;
  }

  public static int indexOfNonWhitespace(final CharSequence value) {
    return indexOfNonWhitespace(value, 0);
  }

  public static int indexOfNonWhitespace(final CharSequence value, final int startIndex) {
    for (var i = startIndex; i < value.length(); i++) {
      if (!Character.isWhitespace(value.charAt(i))) {
        return i;
      }
    }

    return -1;
  }

  public static int lastIndexOf(final CharSequence value, final int endIndex, final char c) {
    for (var i = value.length() - 1; i >= 0 && i >= endIndex; i--) {
      if (value.charAt(i) == c) {
        return i;
      }
    }

    return -1;
  }

  public static int lastIndexOf(final CharSequence value, final int endIndex, final char... chars) {
    for (var i = value.length() - 1; i >= 0 && i >= endIndex; i--) {
      for (final var c : chars) {
        if (value.charAt(i) == c) {
          return i;
        }
      }
    }

    return -1;
  }

  public static boolean endsWith(final CharSequence value, final CharSequence suffix) {
    final var suffixLength = suffix.length();
    final var valueOffset = value.length() - suffixLength;

    if (valueOffset < 0) {
      return false;
    }

    for (var i = 0; i < suffixLength; i++) {
      if (value.charAt(valueOffset + i) != suffix.charAt(i)) {
        return false;
      }
    }

    return true;
  }

  public static CharSequence trimEnd(final CharSequence value) {
    final var length = value.length();

    for (var i = length - 1; i >= 0; i--) {
      if (!Character.isWhitespace(value.charAt(i))) {
        return i == length - 1
            ? value
            : value.subSequence(0, i + 1);
      }
    }

    return value.subSequence(0, 0);
  }
}
