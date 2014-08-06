Heap(Priority Queue)
====

<b>Heap Operations</b>
 

Operations | Running Time
:---------:|:-----------:
 DeleteMax/ExtractRoot:| <b>O(logn)</b>
 Query Top(Max/Min):| <b>O(1)</b>
 InsertElement:|<b>O(logn)</b>
 Heapify:|<b>O(logn)</b>
 Build Heap:|<b>O(nlogn)</b>
 Find K Ordered Top(Min/Max) Elements:|<b>O(klogn)</b>
 
<b>File's Descriptions:</b>

<b>1) Heap / src / com / heap / Heap.java:</b><br/>
The library importing which you will be able to use all the Heap operations with the above mentioned Running Times

<b>2) Heap / src / com / heap / MaxIntegerComparator.java:</b><br/>
This Comparator can be used if a MaxHeap is needed.

<b>3) Heap / src / com / heap / MinHeapComparator.java:</b><br/>
This Comparator can be used if a MinHeap is needed. The TestHeap.java uses this Comparator to create a MinHeap and performs several Unit Tests.

<b>4) Heap / src / com / heap / Node.java:</b><br/>
The Heap basically contains the Objects of this class. Currently it has only value field, you can have several other instance variables as satellite data of your Node. The Heap will be managed based on the this.value variable.

<b>5) Heap / src / com / heap / StringComparator.java</b><br/>
This Comparator can be used to arrange the Strings lexicographically. The TestHeap.java uses this Comparator to manage a Heap of Strings and also performs several Unit Tests on it.

<b>6) Heap / src / com / heap / TestHeap.java</b><br/>
This class has the main method and creates two Heaps (Min-Heap and String-Heap). It also performs certain unit tests. This can be refered to implement the library.

<b>7) Heap / src / com / heap / UnitTests.java</b><br/>
This class has certain Tests and prints an error if the result is not as desired.

