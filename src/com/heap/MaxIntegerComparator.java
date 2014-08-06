package com.heap;

import java.util.Comparator;

public class MaxIntegerComparator implements Comparator<Node<Integer>>{

	@Override
	public int compare(Node<Integer> nodeOne, Node<Integer> nodeTwo) {
		return nodeTwo.value-nodeOne.value;
	}

}
