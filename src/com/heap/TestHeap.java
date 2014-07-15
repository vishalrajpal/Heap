package com.heap;

public class TestHeap {

	public static void main(String[] args)
	{
		IntComparator intComp=new IntComparator();
		int[] test_array={1,2,3,4,5};
		Heap h = new Heap(test_array, intComp);
	    
		
//		1) DeleteMax/ExtractMax: ------ Running Time: O(logn)
//	 	2) Query Max/Min: ------- Running Time: O(1)
//	 	3) InsertElement: ----- Running Time: O(logn)
//	 	4) Heapify: ----- Running Time: O(logn)
//	 	5) Build Heap: ------ Running Time: O(nlogn)
//	 	6) Find K Min/Max Elements: ------ Running Time: O(klogn)
		
		System.out.println("Extract Max Operation:"+h.ExtractMaxOrMin());	// Expected 5
		h.printHeap();	// Expected 5 deleted from the Heap
		
		System.out.println("Maximum Element:"+h.getMaxOrMin());	//Expected 4
		
		h.insertElementInHeap(50);
		
		System.out.println("Maximum Element:"+h.getMaxOrMin());	//Expected 50
		
		h.printArray(h.findKMaxOrMinElements(8));	//Expected 50, 4, 3
	}
	
	
}
