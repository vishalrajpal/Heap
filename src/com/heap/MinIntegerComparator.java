package com.heap;

import java.util.Comparator;

public class MinIntegerComparator implements Comparator<Node<Integer>>{

	@Override
	public int compare(Node<Integer> nodeOne, Node<Integer> nodeTwo) {
		return nodeOne.value-nodeTwo.value;
	}

}
