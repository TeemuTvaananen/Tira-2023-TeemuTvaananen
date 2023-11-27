package oy.interact.tira.task_08_hashtable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.factories.HashTableFactory;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Find tests for Hashtable")
class RemoveFromHashtableTests {

	private static int counter = 0;
	private static Integer foundValue = null;
	private static Pair<String, Integer>[] array = null;

	@Test
	@Order(1)
	// @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	void testRemovingFromSimpleHashtable() {
		try {
            boolean removeAppearsToBeImplemented = false;
			TIRAKeyedContainer<String, Integer> simpleHashtable = HashTableFactory.createHashTable();
			assertNotNull(simpleHashtable,
					() -> "HashTableFactory.createHashTable() returns null, not yet implemented?");
			String[] keys = { "Kissa", "Ihminen", "Lammas", "Hevonen", "Marsu", "Apina", "Örkki", "Zebra", "Banaani", "Jakkihärkä" };
            // value with key:   1        2           3         4          5       6        7       8          9          10
			int count = keys.length;
			counter = 1;
			for (String key : keys) {
				assertDoesNotThrow(() -> simpleHashtable.add(key, counter++), "Hashtable.add must not throw");
			}
			assertDoesNotThrow(() -> array = simpleHashtable.toArray());
			System.out.println("Hashtable filled:");
			printPairArray(array);
			assertEquals(keys.length, simpleHashtable.size(), "Not all test data in Hashtable, size differs");
			counter = 1;
			for (String key : keys) {
				assertDoesNotThrow(() -> foundValue = simpleHashtable.get(key), "Hashtable.get must not throw");
				assertNotNull(foundValue, "Must get the value for the key in Hashtable, got null");
				assertEquals(counter++, foundValue, "Value with key does not match");
			}
			// Now we know Hashtable is correct, try removing from it.
			System.out.print("Removing B from Hashtable...");
			foundValue = null;
			keys[8] = null; // remove "Banaani"
			foundValue = null;
			count--;
			foundValue = simpleHashtable.remove("Banaani");
			if (foundValue != null) {
                removeAppearsToBeImplemented = true;
				assertNotNull(foundValue, "Must get the value for the key removed from Hashtable");
				assertEquals(9, foundValue, "Value of key Banaani does not match");
				assertEquals(count, simpleHashtable.size(), "Size did not shrink after removing.");
                assertNull(simpleHashtable.get("Banaani"), "Banaani must not appear in hashtable anymore.");
                assertNull(simpleHashtable.find(value -> value.equals(9)), "Must not find key using predicate either.");
    			assertNull(simpleHashtable.get("Banaani"), "Banaani was removed, must not find it!");
				assertDoesNotThrow(() -> array = simpleHashtable.toArray());
				System.out.println("------\nHashtable with Banaani removed:");
				printPairArray(array);
			}
			keys[0] = null; // remove "Kissa"
			foundValue = null;
			count--;
			foundValue = simpleHashtable.remove("Kissa");
			if (foundValue != null) {
				assertNotNull(foundValue, "Must get the value for the key removed from Hashtable");
				assertEquals(1, foundValue, "Value of key Kissa does not match");
				assertEquals(count, simpleHashtable.size(), "Size did not shrink after removing.");
                assertNull(simpleHashtable.get("Kissa"), "Kissa must not appear in hashtable anymore.");
                assertNull(simpleHashtable.find(value -> value.equals(1)), "Must not find key using predicate either.");
				assertDoesNotThrow(() -> array = simpleHashtable.toArray());
				System.out.println("------\nHashtable with Kissa removed:");
				printPairArray(array);
			}
			keys[5] = null; // remove "A"
			foundValue = null;
			count--;
			foundValue = simpleHashtable.remove("Apina");
			if (foundValue != null) {
				assertNotNull(foundValue, "Must get the value for the key removed from Hashtable");
				assertEquals(6, foundValue, "Value of key Apina does not match");
				assertEquals(count, simpleHashtable.size(), "Size did not shrink after removing.");
				assertDoesNotThrow(() -> array = simpleHashtable.toArray());
				System.out.println("------\nHashtable with Apina removed:");
				printPairArray(array);
			}
			keys[9] = null; // remove "Jakkihärkä"
			foundValue = null;
			count--;
			foundValue = simpleHashtable.remove("Jakkihärkä");
			if (foundValue != null) {
				assertNotNull(foundValue, "Must get the value for the key removed from Hashtable");
				assertEquals(10, foundValue, "Value of key Jakkihärkä does not match");
				assertEquals(count, simpleHashtable.size(), "Size did not shrink after removing.");
				assertDoesNotThrow(() -> array = simpleHashtable.toArray());
				System.out.println("------\nHashtable with Jakkihärkä removed:");
				printPairArray(array);
			}
			counter = 1;
			for (int index = 0; index < keys.length; index++) {
				if (keys[index] == null) {
					counter++;
					continue;
				}
				final int getIndex = index;
				foundValue = null;
				assertDoesNotThrow(() -> foundValue = simpleHashtable.get(keys[getIndex]), "Hashtable.get must not throw");
				assertNotNull(foundValue, "Must get the value for the key in Hashtable");
				assertEquals(counter++, foundValue, "Value with key does not match");
			}
            assertDoesNotThrow(() -> simpleHashtable.add("Kissa", 42), "Adding something removed earlier must succeed");
            if (removeAppearsToBeImplemented) {
                count++;
                assertEquals(count, simpleHashtable.size(), "Size must increase when adding new content (previously removed)");
            }
            assertDoesNotThrow(() -> foundValue = simpleHashtable.get("Kissa"), "get must not throw");
            assertEquals(42, foundValue, "Readded Kissa must have (new) associated value 42");
		} catch (NotYetImplementedException e) {
			System.out.println("Remove not implemented, and that is OK");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Tested code threw exception " + e.getMessage());
		}
	}

	private static void printPairArray(Pair<String,Integer> [] array) {
		for (int index = 0; index < array.length; index++) {
			System.out.format("%3d. key: %s value: %d%n", index+1, array[index].getKey(), array[index].getValue());
		}
	}

}
