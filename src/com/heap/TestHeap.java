package com.heap;

public class TestHeap {

	public static void main(String[] args)
	{
//		long startTime = System.currentTimeMillis();
//		int[] a = new int[100000];
//		for (int i = 0; i < a.length; i++) {
//		    a[i] = (int)(Math.random() * 100000);
//		}
		int[] test_array={1,2,3,4,5};
		//int[] test_array1={5,4,3,2,1,10,8,9,7,6};
		Heap h = new Heap(test_array);
		
		//long stopTime = System.currentTimeMillis();
	  //  long elapsedTime = stopTime - startTime;
	    System.out.println("");
	    System.out.println("");
	    //System.out.println(elapsedTime);
	    //System.out.println(h.ExtractMaxOrMin());
	    System.out.println(h.getMaxOrMin());
	    
	}
}
