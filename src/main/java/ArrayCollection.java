import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollection<T> implements Collection<T> {
    private T[] m = (T[])new Object[1];
    private int size;
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (m[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new elementsIterator();
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[])new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return (T1[])this.toArray();
    }

    @Override
    public boolean add(final T t) {
        if (m.length == size) {
            final T[] oldM = m;
            m = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            if (m[i].equals(o)) {
                System.arraycopy(m, i + 1, m, i, this.size() - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object elements: collection) {
            if (!this.contains(elements)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (final T elements: collection) {
            add(elements);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (final Object elements: collection) {
            remove(elements);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        for (final Object elements: this) {
            if (!collection.contains(elements)) {
                this.remove(elements);
                this.size--;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        m = (T[])new Object[1];
        size = 0;
    }

    private class elementsIterator implements Iterator<T> {
        private int index;
        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in this m");
            }
            return ArrayCollection.this.m[index++];

        }
    }
}

