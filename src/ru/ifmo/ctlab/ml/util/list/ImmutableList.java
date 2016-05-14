package ru.ifmo.ctlab.ml.util.list;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class ImmutableList<E> implements List<E>, RandomAccess, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private int from, to;
    private Object[] elementData;

    private static final RuntimeException addExc = new UnsupportedOperationException("Can't add element to immutable collection");
    private static final RuntimeException remExc = new UnsupportedOperationException("Can't remove element from immutable collection");
    private static final RuntimeException chExc = new UnsupportedOperationException("Can't change immutable collection");

    public ImmutableList(Object[] elementData, int from, int to) {
        this.elementData = elementData;
        this.from = from;
        this.to = to;
    }

    @Deprecated
    @Override
    public boolean add(E e) {
        throw addExc;
    }

    @Override
    public int size() {
        return to - from;
    }

    @Override
    public boolean isEmpty() {
        return to == from;
    }

    private int offset(int index) {
        int size = size();
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return index + from;
    }

    @Override
    public boolean contains(Object object) {
        for (int i = from; i < to; i++) {
            if (elementData[i].equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(elementData, from, to);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOfRange(elementData, from, to, a.getClass());
    }

    @Override
    @Deprecated
    public boolean remove(Object o) {
        throw remExc;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean addAll(Collection<? extends E> c) {
        throw addExc;
    }

    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends E> c) {
        throw addExc;
    }

    @Deprecated
    @Override
    public boolean removeAll(Collection<?> c) {
        throw remExc;
    }

    @Deprecated
    @Override
    public boolean retainAll(Collection<?> c) {
        throw remExc;
    }

    @Deprecated
    @Override
    public void clear() {
        throw remExc;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        return (E) elementData[offset(index)];
    }

    @Deprecated
    @Override
    public E set(int index, E element) {
        throw chExc;
    }

    @Deprecated
    @Override
    public void add(int index, E element) {
        throw addExc;
    }

    @Deprecated
    @Override
    public E remove(int index) {
        throw remExc;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = from; i < to; i++) {
            if (elementData[i].equals(object)) {
                return i - from;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = to - 1; i >= from; i--) {
            if (elementData[i].equals(object)) {
                return i - from;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        int size = size();
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException("index < 0 || index > size()");
        }

        return new ListIterator<E>() {
            int cursor = index + from;

            @Override
            public boolean hasNext() {
                return cursor < to;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (hasNext()) {
                    return (E) elementData[cursor++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasPrevious() {
                return from < cursor;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E previous() {
                if (hasPrevious()) {
                    return (E) elementData[--cursor];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public int nextIndex() {
                return cursor - from;
            }

            @Override
            public int previousIndex() {
                return cursor - from - 1;
            }

            @Override
            public void remove() {
                throw remExc;

            }

            @Override
            public void set(E e) {
                throw chExc;

            }

            @Override
            public void add(E e) {
                throw addExc;
            }

        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (toIndex < fromIndex) {
            
        }

        return null;
    }

}
