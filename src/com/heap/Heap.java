package com.heap;

import java.util.Comparator;

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

	Node[] heap_array;
	boolean isMaxHeap=true;
	int lastElementIndex=0;	
	Comparator<Node> comparator=null;
	
	// Constructor 1
	// Contract: int[], Comparator -> void
	// Given: An integer array and a Comparator based on which values will be compared
	// Effect: Builds a Max Heap from the given integer array 'int_array'
	
	Heap(int[] int_array, Comparator c)
	{
		comparator=c;
		buildHeap(int_array);
		
	}
	
	// Constructor 2
	// Contract: Boolean, Comparator -> void
	// Given: A boolean representing if the Heap should be a MaxHeap or MinHeap and a Comparator based on which values will be compared
	// Effect: Creates a Min Heap or Max Heap depending upon the boolean value given, if true creates a MaxHeap else a MinHeap
	
	Heap(boolean isMaxHeap, Comparator c)
	{
		comparator=c;
		this.isMaxHeap=isMaxHeap;
	}
	
	// Constructor 3
	// Contract: int[], Boolean, Comparator: void
	// Given: An array of integers and a boolean value if a Max-Heap needs to be created or a Min-Heap 
	// and a Comparator based on which values will be compared.
	// Effect: Builds a ax-Heap or Min-Heap based on the the boolena value with the given array of integers
	
	Heap(int[] int_array, boolean isMaxHeap, Comparator c)
	{
		comparator=c;
		this.isMaxHeap=isMaxHeap;
		buildHeap(int_array);
	}
	
	// buildHeap: int[] -> void
	// Given: An array of integers
	// Effect: Creates a Heap with the given array of integers
	
	private void buildHeap(int[] int_array)
	{
		int array_len=int_array.length;
		heap_array=new Node[array_len];
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
		lastElementIndex+=1;
	}
	
	// insertNodeInHeap: Node -> void
	// Given: An object of Node class
	// Effect: Inserts the given Node in the Max-Heap or Min-Heap
	
	private void insertNodeInHeap(Node n)
	{
		int heap_len=heap_array.length;
		if(heap_len==lastElementIndex)
		{
			Node[] temp_heap=new Node[heap_len*2];
			System.arraycopy(heap_array, 0, temp_heap, 0, heap_len);
			heap_array=temp_heap;
		}
		heap_array[lastElementIndex]=n;
		
		if(lastElementIndex>0)
		{
			heapify(lastElementIndex);
		}
	}
	
	private void heapify(int index)
	{
		int heap_len=heap_array.length;
		Node nodeToHeapify=heap_array[index];
		int nodeParentIndex=getParentNodeIndex(index);
		int leftChildIndex=(2*index)+1;
		int rightChildIndex=(2*index)+2;
		Node leftChild,rightChild,parent;
		leftChild=rightChild=parent=null;
		int indexToSwap=-1;
		int maxMinIndex=index;
		if(leftChildIndex<heap_len)
			leftChild=heap_array[leftChildIndex];
		
		if(rightChildIndex<heap_len)
			rightChild=heap_array[rightChildIndex];
		
		if(nodeParentIndex>=0)
			parent=heap_array[nodeParentIndex];
		
		if(this.isMaxHeap)
		{
			if(parent!=null && comparator.compare(parent, nodeToHeapify)<0)
				indexToSwap=nodeParentIndex;
			else 
			{
				if(leftChild!=null && comparator.compare(leftChild, heap_array[maxMinIndex])>0)
				{
					indexToSwap=leftChildIndex;
					maxMinIndex=leftChildIndex;
				}			
				if(rightChild!=null && comparator.compare(rightChild, heap_array[maxMinIndex])>0)
				{
					indexToSwap=leftChildIndex;
					maxMinIndex=leftChildIndex;
				}
			}
		}
		else
		{
			if(parent!=null && comparator.compare(parent, nodeToHeapify)>0)
				indexToSwap=nodeParentIndex;
			else
			{
				if(leftChild!=null && comparator.compare(leftChild, heap_array[maxMinIndex])<0)
				{
					indexToSwap=leftChildIndex;
					maxMinIndex=leftChildIndex;
				}
				if(rightChild!=null && comparator.compare(rightChild, heap_array[maxMinIndex])<0)
				{
					indexToSwap=leftChildIndex;
					maxMinIndex=leftChildIndex;
				}
			}
			
		}
		
		if(indexToSwap!=-1)
		{
			swapNodeValues(indexToSwap, index);
			heapify(indexToSwap);
		}
	}
	// ExtractMaxOrMin: -> Integer
	// Returns: Returns & Deletes the Maximum element if it is a Max-Heap, Minimum element if it is a Min-Heap,
	// and then Heapifies the heap.
	
	int ExtractMaxOrMin()
	{
		int maxOrMinValue=getMaxOrMin();
		int lastNodeIndex=lastElementIndex-1;
		swapNodeValues(0,lastNodeIndex);
		heap_array[lastNodeIndex]=null;
		lastElementIndex-=1;
		heapify(0);
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
		if(k>lastElementIndex)
			k=lastElementIndex;
		Node[] tempArray=heap_array;
		int[] resultArray=new int[k];
		for(int resCount=0;resCount<k;resCount++)
		{
			resultArray[resCount]=ExtractMaxOrMin();
		}
		heap_array=tempArray;
		return resultArray;
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
		//int heap_length=heap_array.length;
		for(int i=0;i<lastElementIndex;i++)
		{
			Node printNode=heap_array[i];
			int leftChildIndex=2*i+1;
			int rightChildIndex=2*i+2;
			int leftChildVal=-1;
			int rightChildVal=-1;
			if(leftChildIndex<lastElementIndex && heap_array[leftChildIndex]!=null)
				leftChildVal=heap_array[leftChildIndex].value;
			if(rightChildIndex<lastElementIndex && heap_array[rightChildIndex]!=null)
				rightChildVal=heap_array[rightChildIndex].value;;
			System.out.println("Element:"+printNode.value+", Left Child:"+leftChildVal+" ,Right Child:"+rightChildVal);
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
