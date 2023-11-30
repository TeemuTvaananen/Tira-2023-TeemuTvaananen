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
       
        System.out.println("Removing the elements");

        System.out.println(hashTable.remove("One"));
        System.out.println(hashTable.remove("Two"));
        System.out.println(hashTable.remove("Three"));

        System.out.println("Checking that size is 0");

        System.out.println(hashTable.size());
    }
}
