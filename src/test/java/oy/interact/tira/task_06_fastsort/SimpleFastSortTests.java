package oy.interact.tira.task_06_fastsort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import oy.interact.tira.student.Algorithms;

@DisplayName("Very simple Algorithms.fastSort tests with Strings")
class SimpleFastSortTests {
	
	@Test
	@DisplayName("Testing Algorithms.fastSort with String arrays to natural order")
	void testSimpleStringArraySortAsc() {
		System.out.println("Sorting String array to natural order...");
		String [] array = {"8", "4", "1", "9", "3", "7", "2", "5", "6", "10"};
		final String [] expected = {"1", "10", "2", "3", "4", "5", "6", "7", "8", "9"};
      Algorithms.fastSort(array);
		assertEquals(expected.length, array.length, "After sort, array lengths must match");
		Set<String> testSet = new HashSet<>();
		for (final String item : array) {
			assertNotNull(item, "Array must not contain null values");
			assertTrue(testSet.add(item), "Test data has no duplicates but sorted array does, so data is messed up in sorting.");
		}
		System.out.format("Expected array: %s%n", Arrays.toString(expected));
		System.out.format("Sorted array  : %s%n", Arrays.toString(array));
		assertTrue(Arrays.equals(array, expected), "Algorithms.fastSort does not sort correctly");
	}

	@Test
	@DisplayName("Testing Algorithms.fastSort with String arrays to reverse order")
	void testSimpleStringArraySortDesc() {
		System.out.println("Sorting String array to reverse order...");
		String [] array = {"8", "4", "1", "9", "3", "7", "2", "5", "6", "10"};
		final String [] expected = {"9", "8", "7", "6", "5", "4", "3", "2", "10", "1"};
      Algorithms.fastSort(array, Comparator.reverseOrder());
		assertEquals(expected.length, array.length, "After sort, array lenghts must match");
		Set<String> testSet = new HashSet<>();
		for (final String item : array) {
			assertNotNull(item, "Array must not contain null values");
			assertTrue(testSet.add(item), "Test data has no duplicates but sorted array does, so data is messed up in sorting.");
		}
		System.out.format("Expected array: %s%n", Arrays.toString(expected));
		System.out.format("Sorted array  : %s%n", Arrays.toString(array));
		assertTrue(Arrays.equals(array, expected), "Algorithms.fastSort does not sort correctly");
	}
}
