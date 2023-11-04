package oy.interact.tira.student;

import java.util.Comparator;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    private class TreeNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        TreeNode<K, V> left;
        TreeNode<K, V> right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

    }

    TreeNode<K, V> root;
    private Comparator<K> comparator;
    int size;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key.equals(null) || value.equals(null)) {
            throw new IllegalArgumentException("The key or value was null");
        }
        insertNode(root, key, value);
        size++;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key.equals(null)) {
            throw new IllegalArgumentException("The key is null");
        }

        return search(root, key);
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        if (key.equals(null)) {
            throw new IllegalArgumentException("The key is null");
        }

        TreeNode<K, V> newRoot = deleteNode(root, key);

        if (!newRoot.equals(null)) {
            size--;
            root = newRoot;
            return newRoot.value;
        }
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
      if(root.equals(null)){
        return null;
      }
       return findNode(root, searcher);
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

    private TreeNode<K, V> insertNode(TreeNode<K, V> node, K key, V value) {
        if (node.equals(null)) {
            return new TreeNode<>(key, value);
        }

        int comparatorResult = comparator.compare(node.key, key);

        if (comparatorResult < 0) {
            node.left = insertNode(node.left, key, value);
        } else if (comparatorResult > 0) {
            node.right = insertNode(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    private V search(TreeNode<K, V> node, K key) {

        if (node.equals(null)) {
            return null;
        }
        int comparatorResult = comparator.compare(key, node.key);

        if (comparatorResult < 0) {
            return search(node.left, key);
        } else if (comparatorResult > 0) {
            return search(node.right, key);
        } else {
            return node.value;
        }
    }

    private TreeNode<K, V> deleteNode(TreeNode<K, V> node, K key) {
        if (node.equals(null)) {
            return node;
        }

        int comparatorResult = comparator.compare(node.key, key);

        if (comparatorResult < 0) {
            node.left = deleteNode(node.left, key);
        } else if (comparatorResult > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            // the key is found here
            if (root.left.equals(null)) {
                return root.right;
            } else if (root.right.equals(null)) {
                return root.left;
            }
            // here we can presume that the node has two children so lets find the inorder
            // predecessor(node with largest value in the left subtree)
            TreeNode<K, V> maxValueTreeNode = findMaxNode(node.left);
            node.key = maxValueTreeNode.key;
            node.value = maxValueTreeNode.value;
            node.left = deleteNode(node.left, node.key);

        }
        return node;
    }

    private TreeNode<K, V> findMaxNode(TreeNode<K, V> node) {
        while (!node.right.equals(null)) {
            node = node.right;
        }
        return node;
    }


    private V findNode(TreeNode<K, V> node, Predicate<V> explorer){
        V leftSearch = findNode(node.left, explorer);
        V rightSearch = findNode(node.right, explorer);

        if(node.equals(null)){
            return node.value;
        }
        
        if(explorer.test(node.value)){
            return node.value;
        }
        
        if(leftSearch != null){
            return leftSearch;
        }
        if(rightSearch != null){
            return rightSearch;
        }
        return null;
    }
}