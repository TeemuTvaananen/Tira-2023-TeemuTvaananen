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
        this.table = new Pair[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        Coder temporaryCoder = new Coder(key.toString());
        return temporaryCoder.hashCode() % table.length;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("They key or value is null");
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
        return Math.abs(hash(key) + hashModifier) % capacity();
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
                    System.out.println("Found something with " + table[index].getKey() + " " + table[index].getValue());
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
        int index = Math.abs(hash(key));

        while (table[index] != null && !table[index].isRemoved()) {
            if (table[index].getKey().equals(key)) {
                table[index].setRemoved();
                size--;
                return table[index].getValue();
            }
            index = (index + 1) % table.length;
        }
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        for (Pair<K, V> pair : table) {
            if (pair != null && !pair.isRemoved() && searcher.test(pair.getValue())) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        int count = 0;
    for (Pair<K, V> pair : table) {
        if (pair != null && !pair.isRemoved()) {
            count++;
        }
    }
    return count;
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
    
        Pair<K, V>[] newArray = table;
    
        table = new Pair[newCapacity];
    
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] != null && !newArray[i].isRemoved()) {
                add(newArray[i].getKey(), newArray[i].getValue());
            }
        }

        size = size();

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
        int index = 0;
        for (Pair<K, V> current : table) {
            if (current != null && !current.isRemoved()) {
                copy[index++] = current;
            }
        }
        return copy;
    }
}