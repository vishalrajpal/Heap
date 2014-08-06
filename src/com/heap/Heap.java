package com.heap;

import java.security.acl.LastOwnerException;
import java.util.Comparator;

/** 
 * Heap Operations
 * 1) DeleteMax/ExtractRoot: ------ Running Time: O(logn)
 * 2) Query Top: ------- Running Time: O(1)
 * 3) InsertElement: ----- Running Time: O(logn)
 * 4) Heapify: ----- Running Time: O(logn)
 * 5) Build Heap: ------ Running Time: O(nlogn)
 * 6) Find K Ordered Top Elements: ------ Running Time: O(klogn)
 * 
 * @param <AnyType>: The type of values Heap will store and based on which other methods will operate
 *  
 */

public class Heap<AnyType>{

	/** Data Definition:
	 * 
	 * Node: A Node is an object of generic Node Class. A Heap consists of number of objects of 
	 * class Node
	 * 
	 * Interpretation:
	 * 		value: The value of a Node and can be of any Object Type
	 * 
	 * Data Definition End
	 */
	
	Node<AnyType>[] heapArray;
	Comparator<Node<AnyType>> comparator=null;
	int willBeNewElementIndex;	
	
	/** Constructor 1
	 * 
	 * Contract: Comparator<Node<AnyType>> -> void
	 * @param c: A Comparator of Node Type based on which values will be compared
	 * 
	 * Effect: Builds a Heap having the given Comparator. In future values will be compared based
	 * on the given comparator
	 */
	Heap(Comparator<Node<AnyType>> comp)
	{
		this.comparator=comp;
	}
	
	/**
	 * buildHeap: AnyType[] -> void
	 * @param arr: An array of AnyType, values of which will be used to reate of Node<AnyType>
	 * 
	 * Effect: Creates a Heap with the given array
	 */
	void buildHeap(AnyType[] arr)
	{
		willBeNewElementIndex=0;
		int array_len=arr.length;
		heapArray=new Node[array_len];
		for(int i=0;i<array_len;i++)
		{
			insertElementInHeap(arr[i]);
		}
	}
	
	/**
	 * insertElementInHeap: Integer -> void
	 * @param elem: A value of AnyType
	 * 
	 * Effect: Inserts the given integer in the Heap by creating an equivalent object of Node for
	 * it
	 */
	void insertElementInHeap(AnyType elem)
	{
		Node<AnyType> n = new Node<AnyType>();
		n.value=elem;
		insertNodeInHeap(n);
		willBeNewElementIndex+=1;
	}
	
	/**
	 * insertNodeInHeap: Node -> void
	 * @param n: An Object of Node<AnyType> class
	 * 
	 * Effect: Inserts the given Node Heap. If the Heap is full, re-constructs the
	 * 'heapArray' with twice its length.
	 */
	private void insertNodeInHeap(Node<AnyType> n)
	{
		int heap_len=heapArray.length;
		if(heap_len==willBeNewElementIndex)
		{
			Node<AnyType>[] temp_heap=new Node[heap_len*2];
			System.arraycopy(heapArray, 0, temp_heap, 0, heap_len);
			heapArray=temp_heap;
		}
		heapArray[willBeNewElementIndex]=n;
		if(willBeNewElementIndex>0)
			heapify(willBeNewElementIndex);
	}
	
	/**
	 * heapify: int -> void
	 * @param index: An index of an element in Heap
	 * 
	 * Effect: Heapifies a Node at element 'index' bottom-up or top-down
	 */
	private void heapify(int index)
	{
		int heap_len=willBeNewElementIndex;
		int indexToSwap=index;
		
		Node<AnyType> nodeToHeapify=heapArray[index];
		
		int nodeParentIndex=getParentNodeIndex(index);
		int leftChildIndex=getLeftChildIndex(index);
		int rightChildIndex=getRightChildIndex(index);
		
		Node<AnyType> leftChild,rightChild,parent;
		leftChild=rightChild=parent=null;

		if(leftChildIndex<heap_len)
			leftChild=heapArray[leftChildIndex];
		if(rightChildIndex<heap_len)
			rightChild=heapArray[rightChildIndex];
		if(nodeParentIndex>=0)
			parent=heapArray[nodeParentIndex];
		
		if(parent!=null && comparator.compare(parent, nodeToHeapify)>0)
			indexToSwap=nodeParentIndex;
		else
		{
			if(leftChild!=null && comparator.compare(leftChild, heapArray[indexToSwap])<0)
				indexToSwap=leftChildIndex;
			if(rightChild!=null && comparator.compare(rightChild, heapArray[indexToSwap])<0)
				indexToSwap=rightChildIndex;
		}

		if(indexToSwap!=index)
		{
			swapNodes(indexToSwap, index);
			heapify(indexToSwap);
		}
	}
	
	/**
	 * ExtractMaxOrMin: -> Integer
	 * @return: Returns & Deletes the Root Element and Heapifies the Heap.
	 */
	AnyType extractMaxOrMin()
	{
		AnyType maxOrMinValue=getMaxOrMin();
		int lastNodeIndex=willBeNewElementIndex-1;
		swapNodes(0,lastNodeIndex);
		heapArray[lastNodeIndex]=null;
		willBeNewElementIndex-=1;
		heapify(0);
		return maxOrMinValue;
	}
	
	/**
	 * getMaxOrMin: -> Integer
	 * @return: The Root element of the Heap.
	 */
	AnyType getMaxOrMin()
	{
		try
		{
			return (AnyType) heapArray[0].value;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * findKMaxOrMinElements: Integer -> Integer[]
	 * @param k: An Integer representing the number of ordered elements needed from the Heap
	 * 
	 * @return: An array of ordered top 'k' elements from the Heap.
	 */
	AnyType[] findKMaxOrMinElements(int k)
	{
		int tempLastElementIndex=willBeNewElementIndex;
		if(k>willBeNewElementIndex)
			k=willBeNewElementIndex;
		Node<AnyType>[] tempArray=new Node[heapArray.length];
		System.arraycopy(heapArray, 0, tempArray, 0, heapArray.length);
		AnyType[] resultArray=(AnyType[]) new Object[k];
		for(int resCount=0;resCount<k;resCount++)
		{
			resultArray[resCount]=extractMaxOrMin();
		}
		heapArray=tempArray;
		willBeNewElementIndex=tempLastElementIndex;
		return resultArray;
	}
	
	/**
	 * isEmpty: -> boolean
	 * @return: true if the Heap is empty else false.
	 */
	boolean isEmpty()
	{
		return willBeNewElementIndex==0;
	}
	
	/**
	 * getHeapSize: -< int
	 * @return: the size of the Heap
	 */
	int getHeapSize()
	{
		return willBeNewElementIndex;
	}
	
	/**
	 * getParentNodeIndex: Float -> Integer
	 * @param index: An index of the 'heapArray' as a float value 
	 * since need an exact floor value
	 * 
	 * @return: The index of the parent of the given index
	 */
	private int getParentNodeIndex(float index)
	{
		return (int)Math.floor((index-1)/2);
	}
	
	/**
	 * getLeftChildIndex: int -> int
	 * @param index: An index of the 'heapArray'
	 * @return: The index of the left child of the Node at the given index
	 */
	private int getLeftChildIndex(int index)
	{
		return (2*index)+1;
	}
	
	/**
	 * getRightChildIndex: int -> int
	 * @param index: An index of the 'heapArray'
	 * @return: The index of the right child of the Node at the given index
	 */
	private int getRightChildIndex(int index)
	{
		return (2*index)+2;
	}
	
	/**
	 * swapNodes: Integer, Integer -> void
	 * @param i: An index of the 'heapArray'
	 * @param j: An index of the 'heapArray'
	 * 
	 * Effect: Swaps the Objects at given indices in the heapArray.
	 */
	private void swapNodes(int i, int j)
	{
		Node<AnyType> tempNode=new Node();
		tempNode=heapArray[i];
		heapArray[i]=heapArray[j];
		heapArray[j]=tempNode;
	}
	
	/**
	 * printHeap: -> void
	 * 
	 * Effect: Prints the Heap for 'this', If a Node has no child represents its child as -1
	 */
	void printHeap()
	{
		for(int i=0;i<willBeNewElementIndex;i++)
		{
			Node<AnyType> printNode=heapArray[i];
			int leftChildIndex=2*i+1;
			int rightChildIndex=2*i+2;
			Object leftChildVal=-1;
			Object rightChildVal=-1;
			if(leftChildIndex<willBeNewElementIndex && heapArray[leftChildIndex]!=null)
				leftChildVal=heapArray[leftChildIndex].value;
			if(rightChildIndex<willBeNewElementIndex && heapArray[rightChildIndex]!=null)
				rightChildVal=heapArray[rightChildIndex].value;;
			System.out.println("Element:"+printNode.value+", Left Child:"+leftChildVal+" ,Right Child:"+rightChildVal);
		}
	}
	
	/**
	 * printArray: Object[] -> void
	 * @param arr: An array of Object type
	 * 
	 * Effect: Prints the given array
	 * 
	 * Note: Used for testing purpose...
	 */
	void printArray(Object[] arr)
	{
		for(int arCount=0;arCount<arr.length;arCount++)
		{
			System.out.println("Elem "+arCount+"="+arr[arCount]);
		}
	}
}