package com.heap;

public class TestHeap {

	public static void main(String[] args)
	{
		IntegerComparator intComp=new IntegerComparator();
		Integer[] test_array={1,2,3,4,5};
	    
		Heap<Integer> integerHeap=new Heap<Integer>(test_array, intComp);
		
	    System.out.println("Extract Max Operation:"+integerHeap.ExtractMaxOrMin());	// Expected 5
	    integerHeap.printHeap();	// Expected 5 deleted from the Heap
		System.out.println("Maximum Element:"+integerHeap.getMaxOrMin());	//Expected 4
		integerHeap.insertElementInHeap(50);
		integerHeap.printHeap();
		System.out.println("Maximum Element:"+integerHeap.getMaxOrMin());	//Expected 50
		integerHeap.printArray(integerHeap.findKMaxOrMinElements(3));	//Expected 50, 4, 3
		
		
	    StringComparator strComp=new StringComparator();
	    String[] test_string={"e", "d", "c", "b", "a"};
	    
	    Heap<String> stringHeap=new Heap<String>(test_string, false, strComp);	    
	    
	    System.out.println("Extract Min Operation:"+stringHeap.ExtractMaxOrMin());	// Expected "a"
	    stringHeap.printHeap();	// Expected "a" deleted from the Heap
		
		System.out.println("Minimum Element:"+stringHeap.getMaxOrMin());	//Expected "b"
		stringHeap.insertElementInHeap("Algorithms");
		System.out.println("Minimum Element:"+stringHeap.getMaxOrMin());	//Expected "Algorithms"
		stringHeap.printArray(stringHeap.findKMaxOrMinElements(3));	//Expected "Algorithms", "b", "c"
	}
}
