package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E> {

    // Node class, from the task05 markdown
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public QueueImplementation() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot add a null element to the queue");
        }
        if (size == Integer.MAX_VALUE) {
            throw new OutOfMemoryError("This queue is full");
        }

        Node<E> newElement = new Node<>(element);

        if (isEmpty()) {
            head = newElement;
            tail = newElement;
        } else {
            tail.next = newElement;
            tail = newElement;
        }
        size++;
    }

    @Override
    public E dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The queue seems to be empty");
        }
        E headElement = (E) head.data;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }

        size--;
        return headElement;
    }

    @Override
    public E element() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The queue seems to be empty!");
        }

        return head.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder queueStringBuilder = new StringBuilder("[");
        Node<E> currentElement = head;

        for (int index = 0; index < size; index++) {
            if (currentElement == null) {
                continue;
            } else {
                queueStringBuilder.append(currentElement.data);
                currentElement = currentElement.next;
                if (index < size - 1) {
                    queueStringBuilder.append(", ");
                }
            }
        }
        queueStringBuilder.append("]");
        return queueStringBuilder.toString();
    }

}
