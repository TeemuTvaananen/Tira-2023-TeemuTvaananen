package oy.interact.tira.student;

import java.util.Arrays;
import oy.interact.tira.util.StackInterface;

public class StackImplementation <E> implements StackInterface {

    private Object [] itemArray;
    private int latestIndex = 0;
    private static final int DEFAULT_STACK_SIZE = 20;
    private int size;

    public StackImplementation(){
        this.itemArray = new Object[DEFAULT_STACK_SIZE];
        this.size = 0;
    }

    public StackImplementation(int arraySize){
        this.itemArray = new Object[arraySize];
        this.size = 0;
    }

    @Override
    public int capacity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capacity'");
    }

    @Override
    public void push(Object element) throws OutOfMemoryError, NullPointerException {
        if(element == null){
            throw new NullPointerException("Cannot add a null element to Stack!")
        }

        if(size == itemArray.length){
            reallocate();
        }

        itemArray[latestIndex++] = element;
    }

    @Override
    public Object pop() throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public Object peek() throws IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

    @Override
    public int size() {
      return size;
    }

    @Override
    public boolean isEmpty() {
        return itemArray == null || itemArray.length == 0;
   }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
    
    private void reallocate(){
        int length = itemArray.length * 2;
        Object [] reallocated = Arrays.copyOf(itemArray, length);
     }
}
