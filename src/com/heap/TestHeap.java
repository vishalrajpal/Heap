package com.heap;

public class TestHeap {

	public static void main(String[] args)
	{
		IntComparator intComp=new IntComparator();
		Integer[] test_array={1,2,3,4,5};
	    
		Heap<Integer> integerHeap=new Heap<Integer>(test_array, intComp);
		
	    System.out.println("Extract Max Operation:"+integerHeap.ExtractMaxOrMin());	// Expected 5
	    integerHeap.printHeap();	// Expected 5 deleted from the Heap
		System.out.println("Maximum Element:"+integerHeap.getMaxOrMin());	//Expected 4
		integerHeap.insertElementInHeap(50);
		System.out.println("Maximum Element:"+integerHeap.getMaxOrMin());	//Expected 50
		integerHeap.printArray(integerHeap.findKMaxOrMinElements(8));	//Expected 50, 4, 3
		
		
	    StringComparator strComp=new StringComparator();
	    String[] test_string={"Banana", "Orange", "Apple", "Mango"};
	    
	    Heap<String> stringHeap=new Heap<String>(test_string, false, strComp);	    
	    
	    System.out.println("Extract Min Operation:"+stringHeap.ExtractMaxOrMin());	// Expected "Apple"
	    stringHeap.printHeap();	// Expected "Apple" deleted from the Heap
		
		System.out.println("Minimum Element:"+stringHeap.getMaxOrMin());	//Expected "Banana"
		stringHeap.insertElementInHeap("Algorithms");
		System.out.println("Maximum Element:"+stringHeap.getMaxOrMin());	//Expected "Algorithms"
		stringHeap.printArray(stringHeap.findKMaxOrMinElements(3));	//Expected "Algorithms", "Banana", "Mango"
	}
}
