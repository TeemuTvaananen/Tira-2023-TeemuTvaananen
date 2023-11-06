package oy.interact.tira.factories;

import oy.interact.tira.student.StackImplementation;
import oy.interact.tira.util.StackInterface;

public class StackFactory {
	private StackFactory() {
		// Empty
	}

	public static StackInterface<Integer> createIntegerStack() {
		return new StackImplementation<Integer>();
	}

	public static StackInterface<Integer> createIntegerStack(int capacity) {
		return new StackImplementation<Integer>(capacity);

	}

	public static StackInterface<Character> createCharacterStack() {
		return new StackImplementation<Character>();
	}

	public static StackInterface<Character> createCharacterStack(int capacity) {
		return new StackImplementation<Character>(capacity);

	}

}
