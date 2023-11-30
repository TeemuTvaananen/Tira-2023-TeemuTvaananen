package oy.interact.tira.task_07_bst;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.factories.BSTFactory;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Find tests for BST")
class RemoveFromBSTTests {

	private static int counter = 0;
	private static Integer foundValue = null;
	private static Pair<String, Integer>[] array = null;

	@Test
	@Order(1)
	// @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	void testRemovingFromSimpleBST() {
		try {
			Comparator<String> strAscComparator = new Comparator<>() {
				@Override
				public int compare(String first, String second) {
					return first.compareTo(second);
				}
			};
			TIRAKeyedOrderedContainer<String, Integer> simpleBST = BSTFactory.createBST(strAscComparator);
			assertNotNull(simpleBST,
					() -> "BSTFactory.createBST() returns null, not yet implemented?");
			String[] keys = { "K", "I", "L", "H", "M", "A", "Ö", "Z", "B", "J" };
			// 0 1 2 3 4 5 6 7 8 9
			int count = keys.length;
			counter = 1;
			for (String key : keys) {
				assertDoesNotThrow(() -> simpleBST.add(key, counter++), "");
			}
			assertDoesNotThrow(() -> array = simpleBST.toArray());
			System.out.println("BST filled:");
			printPairArray(array);
			assertEquals(keys.length, simpleBST.size(), "Not all test data in BST, size differs");
			counter = 1;
			for (String key : keys) {
				assertDoesNotThrow(() -> foundValue = simpleBST.get(key), "BST.get must not throw");
				assertNotNull(foundValue, "Must get the value for the key in BST, got null");
				assertEquals(counter++, foundValue, "Value with key does not match");
			}
			// Now we know BST is correct, try removing from it.
			System.out.print("Removing B from BST...");
			foundValue = null;
			keys[8] = null; // remove "B"
			foundValue = null;
			count--;
			foundValue = simpleBST.remove("B");
			if (foundValue != null) {
				assertNotNull(foundValue, "Must get the value for the key removed from BST");
				assertEquals(9, foundValue, "Value of key B does not match");
				assertEquals(count, simpleBST.size(), "Size did not shrink after removing.");
				assertDoesNotThrow(() -> array = simpleBST.toArray());
				System.out.println("------\nBST with B removed:");
				printPairArray(array);
			}
			
			keys[0] = null; // remove "K"
			foundValue = null;
			count--;
			foundValue = simpleBST.remove("K");
			if (foundValue != null) {
				assertNotNull(foundValue, "Must get the value for the key removed from BST");
				assertEquals(1, foundValue, "Value of key K does not match");
				assertEquals(count, simpleBST.size(), "Size did not shrink after removing.");
				assertDoesNotThrow(() -> array = simpleBST.toArray());
				System.out.println("------\nBST with K removed:");
				printPairArray(array);
			}

			keys[5] = null; // remove "A"
			foundValue = null;
			count--;
			foundValue = simpleBST.remove("A");
			if (foundValue != null) {
				assertNotNull(foundValue, "Must get the value for the key removed from BST");
				assertEquals(6, foundValue, "Value of key A does not match");
				assertEquals(count, simpleBST.size(), "Size did not shrink after removing.");
				assertDoesNotThrow(() -> array = simpleBST.toArray());
				System.out.println("------\nBST with A removed:");
				printPairArray(array);
			}
			keys[9] = null; // remove "J"
			foundValue = null;
			count--;
			foundValue = simpleBST.remove("J");
			if (foundValue != null) {
				assertNotNull(foundValue, "Must get the value for the key removed from BST");
				assertEquals(10, foundValue, "Value of key J does not match");
				assertEquals(count, simpleBST.size(), "Size did not shrink after removing.");
				assertDoesNotThrow(() -> array = simpleBST.toArray());
				System.out.println("------\nBST with J removed:");
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
				assertDoesNotThrow(() -> foundValue = simpleBST.get(keys[getIndex]), "BST.get must not throw");
				assertNotNull(foundValue, "Must get the value for the key in BST");
				assertEquals(counter++, foundValue, "Value with key does not match");
			}
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
