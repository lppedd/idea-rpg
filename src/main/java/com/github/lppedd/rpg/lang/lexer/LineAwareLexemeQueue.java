package com.github.lppedd.rpg.lang.lexer;

import com.github.lppedd.rpg.lang.lexer.reader.LineBufferReader;
import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
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
public class LineAwareLexemeQueue implements Queue<Lexeme> {
  private final LineBufferReader bufferReader;
  private final Queue<Lexeme> delegate;

  public LineAwareLexemeQueue(
      @NotNull final LineBufferReader bufferReader,
      @NotNull final Queue<Lexeme> delegate) {
    this.bufferReader = bufferReader;
    this.delegate = delegate;
  }

  @Override
  public boolean add(final Lexeme lexeme) {
    final var lineStartIndex = bufferReader.getLineStartIndex();
    final var shiftedLexeme = lexeme.shift(lineStartIndex);
    return delegate.add(shiftedLexeme);
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
