package com.heap;

import java.util.Comparator;

public class StringComparator implements Comparator<Node<String>>{

	@Override
	public int compare(Node<String> nodeOne, Node<String> nodeTwo) {
		return nodeOne.value.compareTo(nodeTwo.value);
	}

}
