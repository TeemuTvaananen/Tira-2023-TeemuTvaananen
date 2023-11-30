package oy.interact.tira.student;

public class HashTableTest {
    public static void main(String[] args) {
        HashTableContainer<String, Integer> hashTable = new HashTableContainer<>();

        // Adding elements
        hashTable.add("One", 1);
        hashTable.add("Two", 2);
        hashTable.add("Three", 3);

        System.out.println("Added elements\n" + hashTable.get("One"));
        System.out.println(hashTable.get("Two"));
        System.out.println(hashTable.get("Three"));
       
        System.out.println("Removing the elements\n");

        System.out.println("Removing the first element " + hashTable.remove("One"));
        System.out.println("Size should be 2 and it is " + hashTable.size());
        System.out.println("Removing the second element " + hashTable.remove("Two"));
        System.out.println("Size should be 1 and it is " + hashTable.size());
        System.out.println("Removing the Third element " + hashTable.remove("Three"));
        System.out.println("Checking that size is 0 and it is " + hashTable.size());

    }
}
