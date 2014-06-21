package com.heap;

// Class Node
// Fields:
// 		value: The value of this Node
// 		leftChild: The leftChild of this Node represented as a Node
//		rightChild: The rightChild of this Node represented as a Node
//		parent: The parent of this Node represented as a Node
// 		num_elements: The number of elements under this Node including itself

final public class Node {
	int value;
	Node leftChild=null;
	Node rightChild=null;
	Node parent=null;
	int num_elements=1;
}
