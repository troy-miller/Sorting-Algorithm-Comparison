
public class AlgorithmRunner {
	
	//Main method runs our algorithms
	public static void main(String[] args) {
		Sorter sorter = new Sorter();
		
		
		//We will run all three algorithms on these various array sizes
		int[] arraySizes = new int[] {
				1000, 5000, 10000, 15000, 20000, 25000, 30000
		};
		
		
		for (int arraySize : arraySizes) {
			
			//Generate our array to be sorted
			double[] inputArray = sorter.generateArray(arraySize);
			
			//We need to count our comparisons as an array so that we can return
			//	our comparison number as well as the sorted array as an array of arrays
			double[] comparisons = new double[] {
					0
			};
			
			
			//This block of code sorts our input array using bubbleSort while keeping
			//	track of runtime and number of comparisons required for the sort
			long preTime = System.currentTimeMillis();
			double[][] bubbleResults = sorter.bubbleSort(inputArray, comparisons);
			long postTime = System.currentTimeMillis();
			double[] bubbleSortedArray = bubbleResults[0];
			int bubbleComparisons = (int) bubbleResults[1][0];
			long bubbleTime = postTime - preTime;
			
			//We have to reset our comparisons to 0 before the next sort because it was
			//	changed during the last one
			comparisons[0] = 0;
			
			//This block of code sorts our input array using mergeSort while keeping
			//	track of runtime and number of comparisons required for the sort
			preTime = System.currentTimeMillis();
			double[][] mergeResults = sorter.mergeSort(inputArray, comparisons);
			postTime = System.currentTimeMillis();
			double[] mergeSortedArray = mergeResults[0];
			int mergeComparisons = (int) mergeResults[1][0];
			long mergeTime = postTime - preTime;
			
			comparisons[0] = 0;
			
			//This block of code sorts our input array using quickSort while keeping
			//	track of runtime and number of comparisons required for the sort
			preTime = System.currentTimeMillis();
			double[][] quickResults = sorter.quickSort(inputArray, 0, arraySize - 1, comparisons);
			postTime = System.currentTimeMillis();
			double[] quickSortedArray = quickResults[0];
			int quickComparisons = (int) quickResults[1][0];
			long quickTime = postTime - preTime;

			
			//Check if our final sorted arrays are equal
			boolean equal = true;
			for (int i = 0; i < inputArray.length; i++) {
				if (bubbleSortedArray[i] != mergeSortedArray[i] ||
					bubbleSortedArray[i] != quickSortedArray[i] ||
					mergeSortedArray[i]  != quickSortedArray[i]) {
					
					equal = false;
				}
			}
			
			
			//OUTPUT MESSAGES
			System.out.println("\n---------------------" + 
							   "\nARRAY SIZE " + arraySize + 
							   "\n---------------------");
			
			System.out.println("\nSorted arrays equal?\n" + equal);
			
			System.out.println("\nFirst 20 elements of sorted array:");
			for (int i = 0; i < 20; i++) {
				System.out.println(bubbleSortedArray[i]);
			}
			

			System.out.println("\nBUBBLE SORT:"
								+ "\n- Number of comparisons: " + bubbleComparisons
								+ "\n- Running time: " + bubbleTime + " milliseconds");
			
			System.out.println("\nMERGE SORT:"
								+ "\n- Number of comparisons: " + mergeComparisons
								+ "\n- Running time: " + mergeTime + " milliseconds");
			
			System.out.println("\nQUICK SORT:"
								+ "\n- Number of comparisons: " + quickComparisons
								+ "\n- Running time: " + quickTime + " milliseconds\n");
			}
		}
}
