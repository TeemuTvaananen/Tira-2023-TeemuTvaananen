package oy.interact.tira.student;

import java.util.function.Predicate;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTableContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

    private int size;
    private static final double REALLOCATION_THRESHOLD = 0.65;
    private static int DEFAULT_ARRAY_CAPACITY = 20;
    private Pair<K, V>[] table;

    @SuppressWarnings("unchecked")
    public HashTableContainer() {
        this.table = (Pair<K, V>[]) new Pair[DEFAULT_ARRAY_CAPACITY];
        this.size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % table.length);
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("They key or value is null");
        }
        if (size == Integer.MAX_VALUE) {
            throw new OutOfMemoryError("The hashtable cannot take anymore elements");
        }

        if ((double) size > capacity() * REALLOCATION_THRESHOLD) {
            ensureCapacity(table.length * 2);
        }

        int index = 0;
        boolean added = false;
        int hashModifier = 0;

        do {
            index = indexFor(key, hashModifier);
            if (table[index] == null) {
                table[index] = new Pair<K, V>(key, value);
                added = true;
                size++;
            } else if (table[index].getKey().equals(key)) {
                table[index] = new Pair<K, V>(key, value);
                added = true;
            } else {
                hashModifier++;
            }
        } while (!added);
    }

    private int indexFor(K key, int hashModifier) {
        return (hash(key) + hashModifier) % capacity();
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null");
        }
        int index = 0;
        int hashModifier = 0;
        boolean keyfound = false;
        V result = null;

        do {
            index = indexFor(key, hashModifier);
            if (table[index] != null) {
                if (table[index].getKey().equals(key)) {
                    result = table[index].getValue();
                    keyfound = true;
                } else {
                    hashModifier++;
                }
            } else {
                keyfound = true;
            }
        } while (!keyfound);

        return result;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null");
        }
        int index = 0;
        boolean removed = false;
        int hashModifier = 0;
        V result = null;

        do {
            index = indexFor(key, hashModifier);
            if (table[index] != null) {
                if (table[index].getKey().equals(key)) {
                    result = table[index].getValue();
                    table[index] = null;
                    removed = true;
                    size--;
                } else {
                    hashModifier++;
                }
            } else {
                removed = true;
            }
        } while (!removed);

        return result;
    }

    @Override
    public V find(Predicate<V> searcher) {
        int index = 0;
        int hashModifier = 0;
        V result = null;
        boolean found = false;
        do {
            if (table[index] != null && !table[index].isRemoved() && searcher.test(table[index].getValue())) {
                result = table[index].getValue();
                found = true;
            } else {
                hashModifier++;
                index = indexFor(null, hashModifier);
                if (hashModifier >= capacity()) {
                    break;
                }
            }
        } while (!found);

        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return table.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int newCapacity) throws OutOfMemoryError, IllegalArgumentException {
        if (newCapacity <= capacity()) {
            throw new IllegalArgumentException("New capacity must be greater than the current capacity");
        }

        if (newCapacity == Integer.MAX_VALUE) {
            throw new OutOfMemoryError("Maxmimum container memory exceeded!");
        }

        Pair<K, V>[] newArray = table;
        table = (Pair<K, V>[]) new Pair[newCapacity];
        size = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] != null && !newArray[i].isRemoved()) {
                add(newArray[i].getKey(), newArray[i].getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.table = new Pair[DEFAULT_ARRAY_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K, V>[] copy = (Pair<K, V>[]) new Pair[size];
        if (copy.length == Integer.MAX_VALUE) {
            throw new Exception("Maximum memory exceeded");
        }
        int copyIndex = 0;
        for (Pair<K, V> pair : table) {
            if (pair != null && !pair.isRemoved()) {
                copy[copyIndex++] = pair;
            }
        }
        return copy;
    }
}
