package com.github.lppedd.rpg.lang.lexer;

import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.LineLexeme;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Edoardo Luppi
 */
public class LexemeQueue implements Queue<Lexeme> {
  private final Deque<Lexeme> delegate = new ArrayDeque<>(32);

  @Override
  public boolean add(final Lexeme lexeme) {
    final var last = delegate.peekLast();

    if (last != null &&
        last.getType() == TokenType.WHITE_SPACE &&
        lexeme.getType() == TokenType.WHITE_SPACE) {
      final var mergedLexeme = new LineLexeme(last.getType(), last.getStartIndex(), lexeme.getEndIndex());
      delegate.pollLast();
      return delegate.add(mergedLexeme);
    }

    return delegate.add(lexeme);
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public boolean contains(final Object o) {
    return delegate.contains(o);
  }

  @NotNull
  @Override
  public Iterator<Lexeme> iterator() {
    return delegate.iterator();
  }

  @NotNull
  @Override
  public Object @NotNull [] toArray() {
    return delegate.toArray();
  }

  @NotNull
  @Override
  public <T> T @NotNull [] toArray(@NotNull final T @NotNull [] a) {
    return delegate.toArray(a);
  }

  @Override
  public boolean remove(final Object o) {
    return delegate.remove(o);
  }

  @Override
  public boolean containsAll(@NotNull final Collection<?> c) {
    return delegate.containsAll(c);
  }

  @Override
  public boolean addAll(@NotNull final Collection<? extends Lexeme> c) {
    return delegate.addAll(c);
  }

  @Override
  public boolean removeAll(@NotNull final Collection<?> c) {
    return delegate.removeAll(c);
  }

  @Override
  public boolean retainAll(@NotNull final Collection<?> c) {
    return delegate.retainAll(c);
  }

  @Override
  public void clear() {
    delegate.clear();
  }

  @Override
  public boolean offer(final Lexeme lexeme) {
    return delegate.offer(lexeme);
  }

  @Override
  public Lexeme remove() {
    return delegate.remove();
  }

  @Override
  @Nullable
  public Lexeme poll() {
    return delegate.poll();
  }

  @Override
  public Lexeme element() {
    return delegate.element();
  }

  @Override
  @Nullable
  public Lexeme peek() {
    return delegate.peek();
  }

  @Override
  public <T> T[] toArray(final IntFunction<T[]> generator) {
    return delegate.toArray(generator);
  }

  @Override
  public boolean removeIf(final Predicate<? super Lexeme> filter) {
    return delegate.removeIf(filter);
  }

  @Override
  public Spliterator<Lexeme> spliterator() {
    return delegate.spliterator();
  }

  @Override
  public Stream<Lexeme> stream() {
    return delegate.stream();
  }

  @Override
  public Stream<Lexeme> parallelStream() {
    return delegate.parallelStream();
  }

  @Override
  public void forEach(final Consumer<? super Lexeme> action) {
    delegate.forEach(action);
  }
}
