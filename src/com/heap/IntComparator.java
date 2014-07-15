package com.heap;

import java.util.Comparator;

public class IntComparator implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return o1.value-o2.value;
	}

}
