package oy.interact.tira.student;

import java.util.Comparator;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    private class TreeNode {
        private K key;
        private V value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

    }

    TreeNode root;
    private Comparator<K> comparator;
    int size;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key or value was null");
        }
        root = addHelper(root, key, value);
        size++;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key is null");
        }
        return getHelper(root, key);
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key is null");
        }

        root = removeHelper(root, key);

        return root != null ? root.value : null;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capacity'");
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ensureCapacity'");
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public int indexOf(K itemKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIndex'");
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findIndex'");
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    // helper methods

    private TreeNode addHelper(TreeNode node, K key, V value) {
        if (node == null) {
            return new TreeNode(key, value);
        }

        if (comparator.compare(key, node.key) < 0) {
            node.left = addHelper(node.left, key, value);
        } else if (comparator.compare(key, node.key) > 0) {
            node.right = addHelper(node.right, key, value);
        }

        return node;
    }

    private V getHelper(TreeNode node, K key) {
        if (node == null) {
            return null;
        }

        if (comparator.compare(key, node.key) == 0) {
            return node.value;
        } else if (comparator.compare(key, node.key) < 0) {
            return getHelper(node.left, key);
        } else {
            return getHelper(node.right, key);
        }
    }

        private TreeNode removeHelper(TreeNode node, K key){
            if(node == null){
                return node;
            }
            int comparatorResult = comparator.compare(key, node.key);

            if(comparatorResult < 0){
                node.left = removeHelper(node.left, key);
            } else if(comparatorResult > 0){
                node.right = removeHelper(node.right, key);
            } else{
                // todo: finish this, the key is found here to be deleted
                //according to the amount of children the node has
            }
            return node;
            
        }

}