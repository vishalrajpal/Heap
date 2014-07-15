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
public class Heap<AnyType>{

	/*-----------------------------------------------------------------------------------------------------------------*/
	// Data Definition:
	// 
	// Node: A Node is an object of generic Node Class. A Heap consists of number of objects of class Node
	//
	// Interpretation:
	//
	// 	value: The value of a Node and can be of any Object Type
	//
	// Data Definition End
	/*-----------------------------------------------------------------------------------------------------------------*/

	Node<AnyType>[] heap_array;
	Comparator<Node<AnyType>> comparator=null;
	boolean isMaxHeap=true;
	int lastElementIndex=0;	
	
	
	// Constructor 1
	// Contract: AnyType[], Comparator<Node<AnyType>> -> void
	// Given: An array of any Object Type and a Comparator of Node type based on which values will be compared
	// Effect: Builds a Max Heap from the given AnyType array 'arr'
	
	Heap(AnyType[] arr, Comparator<Node<AnyType>> c)
	{
		comparator=c;
		buildHeap(arr);
	}

	// Constructor 2
	// Contract: Boolean, Comparator<Node<AnyType>> -> void
	// Given: A boolean representing if the Heap should be a MaxHeap or MinHeap and a Comparator based on which values will be compared
	// Effect: Creates a Min Heap or Max Heap depending upon the boolean value given, if true creates a MaxHeap else a MinHeap
	
	Heap(boolean isMaxHeap, Comparator<Node<AnyType>> c)
	{
		comparator=c;
		this.isMaxHeap=isMaxHeap;
	}
	
	// Constructor 3
	// Contract: AnyType[], Boolean, Comparator<Node<AnyType>>: void
	// Given: An array of AnyType and a boolean value if a Max-Heap needs to be created or a Min-Heap 
	// and a Comparator based on which values will be compared.
	// Effect: Builds a Max-Heap or Min-Heap based on the the boolena value with the given array 'arr'
	
	Heap(AnyType[] arr, boolean isMaxHeap, Comparator<Node<AnyType>> c)
	{
		comparator=c;
		this.isMaxHeap=isMaxHeap;
		buildHeap(arr);
	}
	
	// buildHeap: AnyType[] -> void
	// Given: An array containing one of the Object types
	// Effect: Creates a Heap with the given array
	
	private void buildHeap(AnyType[] arr)
	{
		int array_len=arr.length;
		heap_array=new Node[array_len];
		for(int i=0;i<array_len;i++)
		{
			insertElementInHeap(arr[i]);
		}
		printHeap();
	}
	
	// insertElementInHeap: Integer -> void
	// Given: An Integer
	// Effect: Inserts the given integer in the Heap by creating an equivalent object of Node for it
	
	void insertElementInHeap(AnyType elem)
	{
		Node<AnyType> n = new Node<AnyType>();
		n.value=elem;
		insertNodeInHeap(n);
		lastElementIndex+=1;
	}
	
	// insertNodeInHeap: Node -> void
	// Given: An object of Node class
	// Effect: Inserts the given Node in the Max-Heap or Min-Heap
	
	private void insertNodeInHeap(Node<AnyType> n)
	{
		int heap_len=heap_array.length;
		if(heap_len==lastElementIndex)
		{
			Node<AnyType>[] temp_heap=new Node[heap_len*2];
			System.arraycopy(heap_array, 0, temp_heap, 0, heap_len);
			heap_array=temp_heap;
		}
		heap_array[lastElementIndex]=n;
		if(lastElementIndex>0)
			heapify(lastElementIndex);
	}
	
	// heapify: int -> void
	// Given: An index of an element in Heap
	// Effect: Heapifies a Node at element 'index' bottom-up or top-down
	
	private void heapify(int index)
	{
		int heap_len=lastElementIndex;
		int indexToSwap=index;
		
		Node<AnyType> nodeToHeapify=heap_array[index];
		
		int nodeParentIndex=getParentNodeIndex(index);
		int leftChildIndex=getLeftChildIndex(index);
		int rightChildIndex=getRightChildIndex(index);
		
		Node<AnyType> leftChild,rightChild,parent;
		leftChild=rightChild=parent=null;

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
				if(leftChild!=null && comparator.compare(leftChild, heap_array[indexToSwap])>0)
					indexToSwap=leftChildIndex;
				if(rightChild!=null && comparator.compare(rightChild, heap_array[indexToSwap])>0)
					indexToSwap=rightChildIndex;
			}
		}
		else
		{
			if(parent!=null && comparator.compare(parent, nodeToHeapify)>0)
				indexToSwap=nodeParentIndex;
			else
			{
				if(leftChild!=null && comparator.compare(leftChild, heap_array[indexToSwap])<0)
					indexToSwap=leftChildIndex;
				if(rightChild!=null && comparator.compare(rightChild, heap_array[indexToSwap])<0)
					indexToSwap=rightChildIndex;
			}
		}
		
		if(indexToSwap!=index)
		{
			swapNodeValues(indexToSwap, index);
			heapify(indexToSwap);
		}
	}
	
	// ExtractMaxOrMin: -> Integer
	// Returns: Returns & Deletes the Maximum element if it is a Max-Heap, Minimum element if it is a Min-Heap,
	// and then Heapifies the heap.
	
	AnyType ExtractMaxOrMin()
	{
		AnyType maxOrMinValue=getMaxOrMin();
		int lastNodeIndex=lastElementIndex-1;
		swapNodeValues(0,lastNodeIndex);
		heap_array[lastNodeIndex]=null;
		lastElementIndex-=1;
		heapify(0);
		return maxOrMinValue;
	}
	
	// getMaxOrMin: -> Integer
	// Returns: The Maximum element if it is a Max-Heap, Minimum element if it is a Min-Heap.
	
	AnyType getMaxOrMin()
	{
		try
		{
			return (AnyType) heap_array[0].value;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	// findKMaxOrMinElements: Integer -> Integer[]
	// Given: An Integer representing the number of Maximum or Minimum elements needed from the Max-Heap Or Min-Heap respectively
	// Returns: An array of Maximum Or Minimum 'k' elements from the Max-Heap Or Min-Heap respectively.
	
	AnyType[] findKMaxOrMinElements(int k)
	{
		if(k>lastElementIndex)
			k=lastElementIndex;
		Node<AnyType>[] tempArray=heap_array;
		AnyType[] resultArray=(AnyType[]) new Object[k];
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
	
	// getLeftChildIndex: int -> int
	// Given: An index of the 'heap_array'
	// Returns: The index of the left child of the Node at the given index
	
	private int getLeftChildIndex(int index)
	{
		return (2*index)+1;
	}
	
	// getLeftChildIndex: int -> int
	// Given: An index of the 'heap_array'
	// Returns: The index of the right child of the Node at the given index
	
	private int getRightChildIndex(int index)
	{
		return (2*index)+2;
	}
	
	// swapNodeValues: Integer, Integer -> void
	// Given: Two integers representing the indexes of the heap_array
	// Effect: Swap the values of the two Nodes at the given Indexes.
	
	private void swapNodeValues(int i, int j)
	{
		AnyType temp_node_val=(AnyType) heap_array[i].value;
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
			Node<AnyType> printNode=heap_array[i];
			int leftChildIndex=2*i+1;
			int rightChildIndex=2*i+2;
			Object leftChildVal=-1;
			Object rightChildVal=-1;
			if(leftChildIndex<lastElementIndex && heap_array[leftChildIndex]!=null)
				leftChildVal=heap_array[leftChildIndex].value;
			if(rightChildIndex<lastElementIndex && heap_array[rightChildIndex]!=null)
				rightChildVal=heap_array[rightChildIndex].value;;
			System.out.println("Element:"+printNode.value+", Left Child:"+leftChildVal+" ,Right Child:"+rightChildVal);
		}
	}
	
	void printArray(Object[] arr)
	{
		for(int arCount=0;arCount<arr.length;arCount++)
		{
			System.out.println("Elem "+arCount+"="+arr[arCount]);
		}
	}
}