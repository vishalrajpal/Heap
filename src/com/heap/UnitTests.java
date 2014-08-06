package com.heap;

public class UnitTests<AnyType> {

	Heap<AnyType> heapToTest;
	UnitTests(Heap<AnyType> heapToTest)
	{
		this.heapToTest=heapToTest;
	}
	
	void executeIsEmptyTest(boolean shouldBeResult)
	{
		if(heapToTest.isEmpty()==shouldBeResult)
			System.out.println("Heap: isEmpty() Test Pass");
		else
			System.err.println("isEmpty() Test Failed: Value should be "+shouldBeResult);
	}
	
	void executeGetMaxOrMinTest(AnyType shouldBeResult)
	{
		if(heapToTest.getMaxOrMin().equals(shouldBeResult))
			System.out.println("Heap: getMaxOrMin() Test Pass");
		else
			System.err.println("getMaxOrMin() Test Failed: Value should be "+shouldBeResult);
	}
	
	void executeExtractMaxOrMinTest(AnyType shouldBeResult)
	{
		if(heapToTest.extractMaxOrMin().equals(shouldBeResult))
			System.out.println("Heap: extractMaxOrMin() Test Pass");
		else
			System.err.println("extractMaxOrMin() Test Failed: Value should be "+shouldBeResult);
	}
	
	void executeFindKMaxOrMinElementsTest(AnyType[] shouldBeResult, int elementsToFind)
	{
		AnyType[] resultArray=heapToTest.findKMaxOrMinElements(elementsToFind);
		int arrLen=resultArray.length;
		for(int i=0;i<arrLen;i++)
		{
			if(shouldBeResult[i].equals(resultArray[i]))
				continue;
			else
			{
				System.err.println("findKMaxOrMinElements() Test Failed: Value should be ");
				heapToTest.printArray(shouldBeResult);
				return;
			}
		}
		System.out.println("Heap: findKMaxMinElements() Test Pass");
	}
	
	void executeGetHeapSizeTest(int shouldBeResult)
	{
		if(heapToTest.getHeapSize()==shouldBeResult)
			System.out.println("Heap: getHeapSize() Test Pass");
		else
			System.err.println("getHeapSize() Test Failed: Value should be "+shouldBeResult);
	}
}
