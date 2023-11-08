package oy.interact.tira.student;

import java.util.Comparator;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;
import java.util.concurrent.atomic.AtomicInteger;

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
    private int size;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key or value was null");
        }
        insertNode(root, key, value);
        size++;

    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key is null");
        }

        return search(root, key);
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key is null");
        }

        TreeNode<K, V> newRoot = deleteNode(root, key);

        if (newRoot != null) {
            size--;
            root = newRoot;
            return newRoot.value;
        }
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        if (root == null) {
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
        throw new UnsupportedOperationException("Unimplemented method 'Capacity'");

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

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V>[] toArray() throws Exception {
        if (root == null) {
            return (Pair<K, V>[]) new Pair[0];
        }
        Pair<K, V>[] array = (Pair<K, V>[]) new Pair[size];
        AtomicInteger index = new AtomicInteger(0);

        toArrayHelp(root, array, index);
        return array;

    }

    @Override
    public int indexOf(K itemKey) {
        AtomicInteger index = new AtomicInteger(0);
        return indexOfHelper(root, itemKey, index);
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is out of bounds");
        }
        AtomicInteger currentIndex = new AtomicInteger(0);
        return getIndexHelper(root, currentIndex, index);
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        AtomicInteger currentIndex = new AtomicInteger(0);
        AtomicInteger toBeFound = new AtomicInteger(-1);

        findIndexHelper(root, searcher, currentIndex, toBeFound);
        return toBeFound.get();
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    // helper methods
    private TreeNode<K, V> insertNode(TreeNode<K, V> node, K key, V value) {
        if (node == null) {
            System.out.println("Added a node with value " + value + " and key " + key + " size is " + size);

            return new TreeNode<K, V>(key, value);
        }
        int comparatorResult = comparator.compare(node.key, key);
        if (comparatorResult == 0) {
            node.value = value;
            node.key = key;
        } else if (comparatorResult < 0) {
            node.left = insertNode(node.left, key, value);
        } else {
            node.right = insertNode(node.right, key, value);
        }
        return node;
    }

    private V search(TreeNode<K, V> node, K key) {

        if (node == null) {
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
        if (node == null) {
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
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private V findNode(TreeNode<K, V> node, Predicate<V> explorer) {
        V leftSearch = findNode(node.left, explorer);
        V rightSearch = findNode(node.right, explorer);

        if (explorer.test(node.value)) {
            return node.value;
        }
        if (leftSearch != null) {
            return leftSearch;
        }
        if (rightSearch != null) {
            return rightSearch;
        }
        return null;
    }

    private void toArrayHelp(TreeNode<K, V> node, Pair<K, V>[] array, AtomicInteger index) {
        if (node != null) {
            toArrayHelp(node.left, array, index);
            array[index.getAndIncrement()] = new Pair<>(node.key, node.value);
            toArrayHelp(node.right, array, index);
        }
    }

    private int indexOfHelper(TreeNode<K, V> node, K key, AtomicInteger index) {
        if (node == null) {
            return -1;
        }

        index.incrementAndGet();

        int comparatorResult = comparator.compare(key, node.key);

        if (comparatorResult < 0) {
            return indexOfHelper(node.left, key, index);
        } else if (comparatorResult > 0) {
            return indexOfHelper(node.right, key, index);
        } else {
            if (key.equals(node.key)) {
                return index.get() - 1;
            } else {
                return -1;
            }
        }
    }
    private Pair<K, V> getIndexHelper(TreeNode<K, V> node, AtomicInteger currentIndex, int index) {
        if (node != null) {
            Pair<K, V> leftResult = getIndexHelper(node.left, currentIndex, index);
            if (leftResult != null) {
                return leftResult;  // Propagate the result from the left subtree.
            }
            if (currentIndex.get() == index) {
                return new Pair<>(node.key, node.value);
            }
            currentIndex.incrementAndGet();
            Pair<K, V> rightResult = getIndexHelper(node.right, currentIndex, index);
            if (rightResult != null) {
                return rightResult;  // Propagate the result from the right subtree.
            }
        }
        throw new IndexOutOfBoundsException("The index is out of bounds");
    }

    private void findIndexHelper(TreeNode<K, V> node, Predicate<V> tester, AtomicInteger currentIndex,
            AtomicInteger toBeFound) {
        if (node == null || toBeFound.get() != -1) {
            return;
        }
        findIndexHelper(node.left, tester, currentIndex, toBeFound);

        if (tester.test(node.value)) {
            toBeFound.set(currentIndex.get());
            return;
        }
        currentIndex.incrementAndGet();
        findIndexHelper(node.right, tester, currentIndex, toBeFound);
    }
}