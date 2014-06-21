package com.heap;

public class Heap {

	Node[] heap_array=new Node[0];
	boolean isMaxHeap=true;
	
	Heap(int[] int_array)
	{
		buildHeap(int_array);
	}
	
	Heap(Node n)
	{
		this.heap_array[0]=n;
	}
	
	Heap(Node[] n_array)
	{
		this.heap_array=n_array;
	}
	
	Heap(boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
	}
	
	Heap(Node n, boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
		this.heap_array[0]=n;
	}
	
	Heap(Node[] n_array, boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
		this.heap_array=n_array;
	}
	
	Heap(int[] int_array, boolean isMaxHeap)
	{
		this.isMaxHeap=isMaxHeap;
		buildHeap(int_array);
	}
	
	private void buildHeap(int[] int_array)
	{
		if(this.isMaxHeap)
		{
			buildMaxHeap(int_array);
		}
		else
		{
			buildMinHeap(int_array);
		}
		
	}
	
	private void buildMaxHeap(int[] int_array)
	{
		int array_len=int_array.length;
		for(int i=0;i<array_len;i++)
		{
			insertElementInMaxHeap(int_array[i]);
		}
		printHeap();
	}
	
	void insertElementInMaxHeap(int elem)
	{
		Node n = new Node();
		n.value=elem;
		insertNodeInMaxHeap(n);
	}
	
	void insertNodeInMaxHeap(Node n)
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
			MaxHeapifyBottomUp(nodeIndex);
		}
	}
	
	private void MaxHeapifyBottomUp(int index)
	{
		Node nodeToHeapify=heap_array[index];
		int nodeParentIndex=getParentNodeIndex(index);
		if(index>0 && nodeParentIndex>=0)
		{
			Node lastNodeParent=heap_array[nodeParentIndex];
			if(lastNodeParent.value<nodeToHeapify.value)
			{
				lastNodeParent.num_elements+=1;
				swapNodeValues(nodeParentIndex, index);
				MaxHeapifyBottomUp(nodeParentIndex);
			}
			else
			{
				increaseNumElements(nodeParentIndex);
			}
		}
	}
	
	private void increaseNumElements(int parentNodeIndex)
	{
		while(parentNodeIndex>=0)
		{
			heap_array[parentNodeIndex].num_elements+=1;
			parentNodeIndex=getParentNodeIndex(parentNodeIndex);
		}
	}
	
	private int getParentNodeIndex(float index)
	{
		return (int)Math.floor((index-1)/2);
	}
	
	int ExtractMax()
	{
		int heap_len=heap_array.length;
		int maxValue=heap_array[0].value;
		int lastNodeIndex=heap_len-1;
		swapNodeValues(0,lastNodeIndex);
		decreaseNumElements(lastNodeIndex);
		
		Node[] temp_array=new Node[heap_len-1];
		System.arraycopy(heap_array, 0, temp_array, 0, heap_len-1);
		heap_array=temp_array;
		MaxHeapifyTopDown(0);
		printHeap();
		return maxValue;
	}
	
	private void decreaseNumElements(int index)
	{
		int parentNodeIndex=getParentNodeIndex(index);
		if(heap_array[index].parent.leftChild==heap_array[index])
		{
			heap_array[index].parent.leftChild=null;
		}else
		{
			heap_array[index].parent.rightChild=null;
		}
		while(parentNodeIndex>=0)
		{
			heap_array[parentNodeIndex].num_elements-=1;
			parentNodeIndex=getParentNodeIndex(parentNodeIndex);
		}
	}
	
	private void MaxHeapifyTopDown(int index)
	{
		int heap_len=heap_array.length;
		int leftChildIndex=(2*index)+1;
		int rightChildIndex=(2*index)+2;
		int maxIndex=index;
		Node leftChild=null;
		Node rightChild=null;
		
		if(leftChildIndex<heap_len)
			leftChild=heap_array[leftChildIndex];
		
		if(rightChildIndex<heap_len)
			rightChild=heap_array[rightChildIndex];
		
		if(leftChild!=null && leftChild.value>heap_array[maxIndex].value)
			maxIndex=leftChildIndex;
		if(rightChild!=null && rightChild.value>heap_array[maxIndex].value)
			maxIndex=rightChildIndex;
		
		if(maxIndex!=index)
		{
			swapNodeValues(index, maxIndex);
			MaxHeapifyTopDown(maxIndex);
		}
	}
	

	private void buildMinHeap(int[] int_array)
	{
		int array_len=int_array.length;
		for(int i=0;i<array_len;i++)
		{
			Node n = new Node();
			n.value=int_array[i];
			insertInMinHeap(n);
		}
	}
	
	void swapNodeValues(int i, int j)
	{
		int temp_node_val=heap_array[i].value;
		heap_array[i].value=heap_array[j].value;
		heap_array[j].value=temp_node_val;
	}
		
	void insertInMinHeap(Node n)
	{
		
	}
	
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
}
