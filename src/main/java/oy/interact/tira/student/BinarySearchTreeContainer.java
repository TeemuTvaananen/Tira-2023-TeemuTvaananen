package oy.interact.tira.student;

import java.util.Comparator;
import java.util.function.Predicate;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

import java.util.concurrent.atomic.AtomicInteger;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    private class TreeNode<K, V> {
        private K key;
        private V value;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

    }

    private TreeNode<K, V> root;
    private Comparator<K> comparator;
    private int size;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        if (size >= Integer.MAX_VALUE) {
            throw new OutOfMemoryError("Maximum size exceeded");
        }
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key or value was null");
        }

        if (root == null) {
            root = new TreeNode<K, V>(key, value);
            size++;
        } else {
            if (insertNode(root, key, value)) {
                size++;
            }
        }
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
        return size;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'ensureCapacity'");
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V>[] toArray() {
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
        if (root == null) {
            return -1;
        }
        AtomicInteger index = new AtomicInteger(0);
        StackImplementation<TreeNode<K, V>> nodes = new StackImplementation<>();

        TreeNode<K, V> current = root;
        TreeNode<K, V> parent = null;

        while (!nodes.isEmpty() || current != null) {
            if (current != null) {
                nodes.push(current);
                parent = current;
                current = current.left;
            } else {
                parent = nodes.pop();
                current = parent.right;
                if(parent.key.equals(itemKey)){
                    return index.get();
                }
                index.incrementAndGet();
            }

        }
        return -1;
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The given index is not valid.");
        }
        return getIndexHelper(root, new AtomicInteger(0), index);
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        AtomicInteger index = new AtomicInteger(0);
        return findIndexHelper(root, searcher, index);
    }

    // Helper methods

    private boolean insertNode(TreeNode<K, V> node, K key, V value) {
        boolean insertionResult = false;

        if (node != null) {
            if (node.value.equals(value)) {
                node.value = value;
                node.key = key;
                return false;
            }

            int comparatorResult = comparator.compare(key, node.key);
            if (comparatorResult < 0) {
                if (node.left == null) {
                    node.left = new TreeNode<K, V>(key, value);
                    return true;
                } else {
                    return insertNode(node.left, key, value);
                }
            } else if (comparatorResult > 0) {
                if (node.right == null) {
                    node.right = new TreeNode<K, V>(key, value);
                    return true;
                } else {
                    return insertNode(node.right, key, value);
                }
            }

        }
        return insertionResult;
    }

    private V getHelper(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int comparatorResult = comparator.compare(key, node.key);
        if (comparatorResult < 0) {
            return getHelper(node.left, key);
        } else if (comparatorResult > 0) {
            return getHelper(node.right, key);
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
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
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
        if (node != null) {
            findNode(node.left, explorer);
            findNode(node.right, explorer);
            if (explorer.test(node.value)) {
                return node.value;
            }

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

    private Pair<K, V> getIndexHelper(TreeNode<K, V> node, AtomicInteger currentIndex, int index) {
        if (node != null) {
            Pair<K, V> leftResult = getIndexHelper(node.left, currentIndex, index);
            if (leftResult != null) {
                return leftResult;
            }
            if (currentIndex.get() == index) {
                return new Pair<>(node.key, node.value);
            }
            currentIndex.incrementAndGet();
            Pair<K, V> rightResult = getIndexHelper(node.right, currentIndex, index);
            if (rightResult != null) {
                return rightResult;
            }
        }
        return null;
    }

    private int findIndexHelper(TreeNode<K, V> node, Predicate<V> tester, AtomicInteger currentIndex) {
        if (node != null) {

            int left = findIndexHelper(node.left, tester, currentIndex);
            if (left != -1) {
                return left;
            }

            if (tester.test(node.value)) {
                return currentIndex.get();
            }
            currentIndex.incrementAndGet();

            int right = findIndexHelper(node.right, tester, currentIndex);
            if (right != -1) {
                return right;
            }

        }

        return -1;
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }
}
