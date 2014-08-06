package com.heap;

public class TestHeap {

	public static void main(String[] args)
	{
		Integer[] test_array={1,2,3,4,5};
		MinIntegerComparator intComp=new MinIntegerComparator();
		Heap<Integer> integerHeap=new Heap<Integer>(intComp); // Creates a New Empty Heap
		integerHeapTests(integerHeap,test_array);
		
		String[] testStringArray={"Algorithms", "Algorithm", "Poland", "Polish"};
		StringComparator strComp=new StringComparator();
		Heap<String> stringHeap=new Heap<String>(strComp);	    
		stringHeapTests(stringHeap,testStringArray);
	}
	
	public static void integerHeapTests(Heap<Integer> intHeap, Integer[] test_array)
	{
		UnitTests<Integer> intHeapTest=new UnitTests<Integer>(intHeap);
		
		/* Unit Tests
		 * Tests to check Integer Heap
		 */
		
		/*
		 * Test 1: isEmpty()
		 * Expected: As the Heap has no elements, this should return true
		 */
		intHeapTest.executeIsEmptyTest(true);	

		// Builds a Heap with the given Array.
		intHeap.buildHeap(test_array);
		
		/*
		 * Test 2: isEmpty()
		 * Expected: As the Heap has been built, this should be false
		 */
		intHeapTest.executeIsEmptyTest(false);
		
		/*
		 * Test 3: getMaxOrMin()
		 * Expected: As the Comparator is of a Min Heap, this should be 1
		 * 			(The minimum element in the passed Array is 1)
		 */
		intHeapTest.executeGetMaxOrMinTest(1);
		
		/*
		 * Test 4: getHeapSize()
		 * Expected: As the array was of length 5 this should return 5
		 */
		intHeapTest.executeGetHeapSizeTest(5);
		
		intHeap.insertElementInHeap(0);
		
		/*
		 * Test 5: getHeapSize()
		 * Expected: As a new element is inserted in Heap this should return 6
		 */
		intHeapTest.executeGetHeapSizeTest(6);
		
		/*
		 * Test 6: getMaxOrMin()
		 * Expected: As the Comparator is of a Min Heap, this should be 0
		 * 			(New element is inserted above)
		 */
		intHeapTest.executeGetMaxOrMinTest(0);
		
		/*
		 * Test 7: extractMaxOrMin()
		 * Expected: As the Comparator is of a Min Heap, this should be 0
		 * 			(Minimum element in the Heap)
		 */
		intHeapTest.executeExtractMaxOrMinTest(0);
		
		/*
		 * Test 8: extractMaxOrMin()
		 * Expected: As the Comparator is of a Min Heap, this should be 1
		 * 			as the previous operation should have deleted 0
		 * 			(Prev Minimum element in the Heap)
		 */
		intHeapTest.executeExtractMaxOrMinTest(1);
		
		/*
		 * Test 9: getHeapSize()
		 * Expected: As Extract operation has been executed twice this should return 4
		 */
		intHeapTest.executeGetHeapSizeTest(4);
		
		/*
		 * Test 10: findKMaxOrMinElements()
		 * Expected: As the Comparator is of a Min Heap this should return
		 * {2,3,4]
		 */
		Integer[] expectedKMinMax=new Integer[]{2,3,4};
		intHeapTest.executeFindKMaxOrMinElementsTest(expectedKMinMax,3);
	}
	
	public static void stringHeapTests(Heap<String> stringHeap, String[] test_array)
	{
		UnitTests<String> stringHeapTest=new UnitTests<String>(stringHeap);
		
		/* Unit Tests
		 * Tests to check Integer Heap
		 */
		
		/*
		 * Test 1: isEmpty()
		 * Expected: As the Heap has no elements, this should return true
		 */
		stringHeapTest.executeIsEmptyTest(true);	

		// Builds a Heap with the given Array.
		stringHeap.buildHeap(test_array);

		/*
		 * Test 2: isEmpty()
		 * Expected: As the Heap has been built, this should be false
		 */
		stringHeapTest.executeIsEmptyTest(false);
		
		/*
		 * Test 3: getMaxOrMin()
		 * Expected: As the Comparator is of a Lexicographically Strings this should be
		 * "Algorithm"
		 */
		stringHeapTest.executeGetMaxOrMinTest("Algorithm");
		
		/*
		 * Test 4: getHeapSize()
		 * Expected: As the array was of length 4 this should return 4
		 */
		stringHeapTest.executeGetHeapSizeTest(4);
		
		stringHeap.insertElementInHeap("Abate");
		
		/*
		 * Test 5: getHeapSize()
		 * Expected: As a new element is inserted in Heap this should return 5
		 */
		stringHeapTest.executeGetHeapSizeTest(5);
		
		/*
		 * Test 6: getMaxOrMin()
		 * Expected: As the Comparator is of a Lexicographically arranged Heap.
		 * 			this should be "Abate" (New element is inserted above)
		 */
		stringHeapTest.executeGetMaxOrMinTest("Abate");
		
		/*
		 * Test 7: extractMaxOrMin()
		 * Expected: As the Comparator is of a Lexicographically arranged Heap.
		 * 			this should be "Abates"
		 */
		stringHeapTest.executeExtractMaxOrMinTest("Abate");
		
		/*
		 * Test 8: extractMaxOrMin()
		 * Expected: As the Comparator is of a Lexicographically arranged Heap.
		 * 			this should be "Algorithm"
		 * 			(Prev Minimum element in the Heap)
		 */
		stringHeapTest.executeExtractMaxOrMinTest("Algorithm");
		
		/*
		 * Test 9: getHeapSize()
		 * Expected: As Extract operation has been executed twice this should return 3
		 */
		stringHeapTest.executeGetHeapSizeTest(3);
		
		/*
		 * Test 10: findKMaxOrMinElements(2)
		 * Expected: As the Comparator is of a Min Heap this should return
		 * {"Algorithms","Poland"]
		 */
		String[] expectedKMinMax=new String[]{"Algorithms","Poland"};
		stringHeapTest.executeFindKMaxOrMinElementsTest(expectedKMinMax,2);
	}
}
