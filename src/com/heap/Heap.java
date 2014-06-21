package com.heap;

/* Heap Operations
 	
 	1) DeleteMax/ExtractMax: ------ Running Time: O(logn)
 	2) Query Max/Min: ------- Running Time: O(1)
 	3) InsertElement: ----- Running Time: O(logn)
 	4) Heapify: ----- Running Time: O(logn)
 	5) Build Heap: ------ Running Time: O(nlogn)
 	6) Find K Min/Max Elements: ------ Running Time: O(klogn)
*/
public class Heap{

	/*-----------------------------------------------------------------------------------------------------------------*/
	// Data Definition:
	// 
	// Node: A Node is an object of Node Class. A Heap consists of number of objects of class Node
	//
	// Interpretation:
	//
	// 	value: The value of a Node
	// 	leftChild: The left child of this Node in the logical representation of the Heap, null if there is none.
	// 	rightChild: The right child of this Node in the logical representation of the Heap, null if there is none.
	// 	parent: The parent of this Node in the logical representation of the Heap, null if there is none.
	// 	num_elements: The number of elements UNDER this node including itself in the logical representation of the Heap
	//
	// Data Definition End
	/*-----------------------------------------------------------------------------------------------------------------*/

	Node[] heap_array=new Node[0];
	boolean isMaxHeap=true;
		
	// Constructor 1
	// Contract: int[] -> void
	// Given: An integer array
	// Effect: Builds a Max Heap from the given integer array 'int_array'
	
	Heap(int[] int_array)
	{
		buildHeap(int_array);
	}
	
	// Constructor 2
	// Contract: Node -> void
	// Given: An object of class Node
	// Effect: Builds a Max Heap with the given Node as the root of the Heap.
	
	Heap(Node n)
	{
		this.heap_array[0]=n;
	}
	
	// Constructor 3
	// Contract: Node[] -> void
	// Given: An array of objects of class Node
	// Effect: Treats the given array of objects of Node as a Max Heap
	
	Heap(Node[] n_array)
	{
		this.heap_array=n_array;
	}
	
	// Constructor 4
	// Contract: Boolean -> void
	// Given: A boolean representing if the Heap should be a MaxHeap or MinHeap
	// Effect: Creates a Min Heap or Max Heap depending upon the boolean value given, if true creates a MaxHeap else a MinHeap
	
	Heap(boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
	}
	
	// Constructor 5
	// Contract: Node, Boolean -> void
	// Given: An object of class Node and a boolean value
	// Effect: Creates a Max Heap or Min Heap based on the given boolean value with the given Node as the root element
	
	Heap(Node n, boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
		this.heap_array[0]=n;
	}
	
	// Constructor 5
	// Contract: Node[], Boolean: void
	// Given: An array of objects of class Node, and a Boolean value representing if a Max-Heap needs to be created or a Min-Heap.
	// Effect: Creates a Max-Heap or Min-Heap with the given array of objects of Node class
	
	Heap(Node[] n_array, boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
		this.heap_array=n_array;
	}
	
	// Constructor 6
	// Contract: int[], Boolean: void
	// Given: An array of integers and a boolean value if a Max-Heap needs to be created or a Min-Heap.
	// Effect: Builds a ax-Heap or Min-Heap based on the the boolena value with the given array of integers
	
	Heap(int[] int_array, boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
		buildHeap(int_array);
	}
	
	// buildHeap: int[] -> void
	// Given: An array of integers
	// Effect: Creates a Heap with the given array of integers
	
	private void buildHeap(int[] int_array)
	{
		int array_len=int_array.length;
		for(int i=0;i<array_len;i++)
		{
			insertElementInHeap(int_array[i]);
		}
		printHeap();
	}
	
	// insertElementInHeap: Integer -> void
	// Given: An Integer
	// Effect: Inserts the given integer in the Heap by creating an equivalent object of Node for it
	
	void insertElementInHeap(int elem)
	{
		Node n = new Node();
		n.value=elem;
		insertNodeInHeap(n);
	}
	
	// insertNodeInHeap: Node -> void
	// Given: An object of Node class
	// Effect: Inserts the given Node in the Max-Heap or Min-Heap
	
	private void insertNodeInHeap(Node n)
	{
		int heap_len=heap_array.length;
		Node[] temp_heap=new Node[heap_len+1];
		System.arraycopy(heap_array, 0, temp_heap, 0, heap_len);
		temp_heap[temp_heap.length-1]=n;
		heap_array=temp_heap;
		
		heap_len=heap_array.length;
		if(heap_len>1)
		{
			int nodeIndex=heap_len-1;
			int nodeParentIndex=getParentNodeIndex(nodeIndex);
			Node parentNode=heap_array[nodeParentIndex];
			Node childNode=heap_array[nodeIndex];
			childNode.parent=parentNode;
			
			if(parentNode.leftChild==null)
			{
				parentNode.leftChild=childNode;
			}
			else
			{
				parentNode.rightChild=childNode;
			}
			HeapifyBottomUp(nodeIndex);
		}
	}
	
	// HeapifyBottomUp: Integer -> void
	// Given: An Integer representing the index of the 'heap_array'
	// Effect: Max-Heapifies or Min-Heapifies the Heap from the given index upward towards the root of the Heap
	
	private void HeapifyBottomUp(int index)
	{
		Node nodeToHeapify=heap_array[index];
		int nodeParentIndex=getParentNodeIndex(index);
		if(index>0 && nodeParentIndex>=0)
		{
			Node parentNode=heap_array[nodeParentIndex];
			if((this.isMaxHeap && parentNode.value<nodeToHeapify.value) || (!this.isMaxHeap && parentNode.value>nodeToHeapify.value))
			{
				parentNode.num_elements+=1;
				swapNodeValues(nodeParentIndex, index);
				HeapifyBottomUp(nodeParentIndex);
			}
			else
			{
				increaseNumElements(nodeParentIndex);
			}
		}
	}
		
	// HeapofyTopDown: Integer -> void
	// Given: An Integer representing the index of the 'heap_array'
	// Effect: Max-Heapifies or Min-Heapifies the Heap from the given index downward towards the leaf nodes
	
	private void HeapifyTopDown(int index)
	{
		int heap_len=heap_array.length;
		int leftChildIndex=(2*index)+1;
		int rightChildIndex=(2*index)+2;
		int maxMinIndex=index;
		Node leftChild=null;
		Node rightChild=null;
		
		if(leftChildIndex<heap_len)
			leftChild=heap_array[leftChildIndex];
		
		if(rightChildIndex<heap_len)
			rightChild=heap_array[rightChildIndex];
		
		if(leftChild!=null && 
				((this.isMaxHeap && leftChild.value>heap_array[maxMinIndex].value) || 
						(!this.isMaxHeap && leftChild.value<heap_array[maxMinIndex].value)))
			maxMinIndex=leftChildIndex;
		if(rightChild!=null && 
				((this.isMaxHeap && rightChild.value>heap_array[maxMinIndex].value) || 
						(!this.isMaxHeap && rightChild.value<heap_array[maxMinIndex].value)))
			maxMinIndex=rightChildIndex;
		
		if(maxMinIndex!=index)
		{
			swapNodeValues(index, maxMinIndex);
			HeapifyTopDown(maxMinIndex);
		}
	}
	
	// ExtractMaxOrMin: -> Integer
	// Returns: Returns & Deletes the Maximum element if it is a Max-Heap, Minimum element if it is a Min-Heap,
	// and then Heapifies the heap.
	
	int ExtractMaxOrMin()
	{
		int heap_len=heap_array.length;
		int maxOrMinValue=getMaxOrMin();
		int lastNodeIndex=heap_len-1;
		swapNodeValues(0,lastNodeIndex);
		decreaseNumElements(lastNodeIndex);
		
		Node[] temp_array=new Node[heap_len-1];
		System.arraycopy(heap_array, 0, temp_array, 0, heap_len-1);
		heap_array=temp_array;
		HeapifyTopDown(0);
		printHeap();
		return maxOrMinValue;
	}
	
	// getMaxOrMin: -> Integer
	// Returns: The Maximum element if it is a Max-Heap, Minimum element if it is a Min-Heap.
	
	int getMaxOrMin()
	{
		try
		{
			return heap_array[0].value;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	// findKMaxOrMinElements: Integer -> Integer[]
	// Given: An Integer representing the number of Maximum or Minimum elements needed from the Max-Heap Or Min-Heap respectively
	// Returns: An array of Maximum Or Minimum 'k' elements from the Max-Heap Or Min-Heap respectively.
	
	int[] findKMaxOrMinElements(int k)
	{
		if(k>heap_array.length)
			k=heap_array.length;
		Node[] tempArray=heap_array;
		int[] resultArray=new int[k];
		for(int resCount=0;resCount<k;resCount++)
		{
			resultArray[resCount]=ExtractMaxOrMin();
		}
		heap_array=tempArray;
		return resultArray;
	}
	
	// increaseNumElements: Integer -> void
	// Given: An Integer representing the index of the parent Node of a Node
	// Effect: Increases the number of element/s 'num_elements' by 1 from the given Index upward towards the root

	private void increaseNumElements(int parentNodeIndex)
	{
		while(parentNodeIndex>=0)
		{
			heap_array[parentNodeIndex].num_elements+=1;
			parentNodeIndex=getParentNodeIndex(parentNodeIndex);
		}
	}
	
	// decreaseNumElements: Integer -> void
	// Given: An Integer representing the index of the 'heap_array'
	// Effect: Decreases the number of element/s 'num_elements' by 1 from the given Index upward towards the root
	
	private void decreaseNumElements(int index)
	{
		int parentNodeIndex=getParentNodeIndex(index);
		if(index>0)
		{
			if(heap_array[index].parent.leftChild==heap_array[index])
			{
				heap_array[index].parent.leftChild=null;
			}else
			{
				heap_array[index].parent.rightChild=null;
			}
		}
		while(parentNodeIndex>=0)
		{
			heap_array[parentNodeIndex].num_elements-=1;
			parentNodeIndex=getParentNodeIndex(parentNodeIndex);
		}
	}
	
	// getParentNodeIndex: Float -> Integer
	// Given: An index of the 'heap_array' as a float value since need an exact floor value
	// Returns: The index of the parent of the given index
	
	private int getParentNodeIndex(float index)
	{
		return (int)Math.floor((index-1)/2);
	}
	
	// swapNodeValues: Integer, Integer -> void
	// Given: Two integers representing the indexes of the heap_array
	// Effect: Swap the values of the two Nodes at the given Indexes.
	
	private void swapNodeValues(int i, int j)
	{
		int temp_node_val=heap_array[i].value;
		heap_array[i].value=heap_array[j].value;
		heap_array[j].value=temp_node_val;
	}
	
	// printHeap: -> void
	// Effect: Prints the Heap for 'this', If a Node has no child represents its child as -1
	
	void printHeap()
	{
		for(int i=0;i<heap_array.length;i++)
		{
			Node printNode=heap_array[i];
			int leftChildVal=-1;
			int rightChildVal=-1;
			if(printNode.leftChild!=null)
				leftChildVal=printNode.leftChild.value;
			if(printNode.rightChild!=null)
				rightChildVal=printNode.rightChild.value;
			System.out.println("Element:"+printNode.value+", Left Child:"+leftChildVal+" ,Right Child:"+rightChildVal+" ,Num Elements:"+printNode.num_elements);
		}
	}
	
	void printArray(int[] arr)
	{
		for(int arCount=0;arCount<arr.length;arCount++)
		{
			System.out.println("Elem "+arCount+"="+arr[arCount]);
		}
	}
}