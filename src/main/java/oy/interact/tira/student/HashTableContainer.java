package oy.interact.tira.student;

import oy.interact.tira.model.Coder;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;
import java.util.function.Predicate;

public class HashTableContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

    private int size;
    private static final double REALLOCATION_THRESHOLD = 0.65;
    private static int DEFAULT_ARRAY_CAPACITY = 20;
    private Pair<K, V>[] table;

    public HashTableContainer() {
        this(DEFAULT_ARRAY_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTableContainer(int capacity) {
        this.table = (Pair<K, V>[]) new Pair[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        Coder coderKey = (Coder) key;
        return coderKey.hashCode() % table.length;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("They key or value is null");
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public V find(Predicate<V> searcher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return table.length;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ensureCapacity'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

}