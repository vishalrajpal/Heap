package com.heap;

import java.util.Comparator;

public class StringComparator implements Comparator<Node<String>>{

	@Override
	public int compare(Node<String> nodeOne, Node<String> nodeTwo) {
		int nodeOneVal=nodeOne.value.charAt(0);
		int nodeTwoVal=nodeTwo.value.charAt(0);
		return nodeOneVal-nodeTwoVal;
	}

}
