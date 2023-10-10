package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E> {

    private Object[] itemArray;
    private int latestIndex;
    private static final int DEFAULT_STACK_SIZE = 10;
    private int itemCount;

    public StackImplementation() {
        this.itemArray = new Object[DEFAULT_STACK_SIZE];
        this.latestIndex = -1;
        this.itemCount = 0;
    }

    public StackImplementation(int arraySize) {
        this.itemArray = new Object[arraySize];
        this.latestIndex = -1;
        this.itemCount = 0;
    }

    @Override
    public int capacity() {
        return itemArray.length;
    }

    @Override
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot add a null element to the Stack!");
        }

        if (itemCount == itemArray.length) {
        checkStackCapacity();
        }
        itemArray[++latestIndex] = element;
        itemCount++;
        

    }

    @Override
    public E pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E element = peek();
        
        itemArray[latestIndex] = null;
        latestIndex--;
        itemCount--;
          
       return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        E item = (E) itemArray[latestIndex];
        return item;
    }

    @Override
    public int size() {
        return latestIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return latestIndex == -1;
    }

    @Override
    public void clear() {
        itemArray = new Object[DEFAULT_STACK_SIZE];
        itemCount = 0;
        latestIndex = -1;
    }

    //Own method for reallocating the stack
    private void checkStackCapacity() {
        
            int reallocatedLength = itemArray.length * 2;

            //tarkistin muistin ylittymiselle
            if (reallocatedLength >= Integer.MAX_VALUE) {
                throw new OutOfMemoryError("No room in the stack!");
            }
            Object[] reallocated = new Object[reallocatedLength];

            for (int index = 0; index <= latestIndex; index++) {
                reallocated[index] = itemArray[index];
            }
            itemArray = reallocated;
        
    }

    @Override
    public String toString() {
        StringBuilder stackStringBuilder = new StringBuilder();
        stackStringBuilder.append("[");

        for (int index = 0; index < itemCount; index++) {
            if (itemArray[index] == null) {
                continue;
            }
            stackStringBuilder.append(itemArray[index]);
            if (index < itemCount - 1) {
                stackStringBuilder.append(", ");
            }
        }

        stackStringBuilder.append("]");
        return stackStringBuilder.toString();
    }

}
