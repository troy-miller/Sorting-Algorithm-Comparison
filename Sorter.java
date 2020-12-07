import java.util.Random;
import java.lang.Math;

public class Sorter {
	//---------------------
	//Instance Variables
	//---------------------
	Random random;
	
	//---------------------
	//Constructors
	//---------------------
	public Sorter() {
		random = new Random();
	}
	
	//---------------------
	//Class Methods
	//---------------------
	
	
	//Bubble sort works by cycling through an array and comparing 
	//	elements to their next and swapping if necessary
	public double[][] bubbleSort(double[] inputArray, double[] comparisons) {
		int inputSize = inputArray.length;
		
		for (int i = 0; i < inputSize - 1; i++) {
			for (int j = 0; j < inputSize - 1 - i; j++) {
				comparisons[0]++;
				if (inputArray[j + 1] < inputArray[j]) {
					double elementA = inputArray[j + 1];
					inputArray[j + 1] = inputArray[j];
					inputArray[j] = elementA;
				}
			}
		}
		
		
		//Combines our array and number of comparisons so we can return both
		double[][] outputArray = new double[][] {
				inputArray, comparisons
		};
		return outputArray;
	}
	
	
	
	//Merge sort sorts an array by recursively splitting it into
	//	halves and then merging them back together in order.
	//	This show cases the divide and conquer method of sorting
	public double[][] mergeSort(double[] inputArray, double[] comparisons) {
		double inputSize = inputArray.length;
		
		if (inputSize > 1) {
			//arrayA is the first half (rounded down) of our input array and
			//	arrayB is the second half
			double[] arrayA = new double[(int) Math.floor(inputSize/2)];
			double[] arrayB = new double[(int) Math.ceil(inputSize/2)];
			
			for (int i = 0; i < (int) Math.floor(inputSize/2); i++) {
				arrayA[i] = inputArray[i];
			}
			
			for (int i = (int) Math.floor(inputSize/2); i < inputSize; i++) {
				arrayB[i - (int) Math.floor(inputSize/2)] = inputArray[i];
			}
			
			double[][] newArrayA = mergeSort(arrayA, comparisons);
			double[][] newArrayB = mergeSort(arrayB, comparisons);
			
			double[][] newArray = merge(newArrayA[0], newArrayB[0], new double[] {newArrayA[1][0] + newArrayB[1][0]});
			
			
			//Combines our array and number of comparisons so we can return both
			double[][] outputArray = new double[][] {
				newArray[0], newArray[1]
			};
			return outputArray;
		}
		
		
		//Combines our array and number of comparisons so we can return both
		double[][] outputArray = new double[][] {
			inputArray, comparisons
		};
		return outputArray;
	}
	
	//Helper method, merge, puts arrays back together in order
	private double[][] merge(double[] arrayA, double[] arrayB, double[] comparisons) {
		int i = 0;
		int j = 0;
		int k = 0;
		
		int p = arrayA.length;
		int q = arrayB.length;
		
		double[] mergedArray = new double[p + q];
		
		
		//We add the smaller of either arrayA at index i or arrayB at index
		//	j to the merged array and increment whichever one was chosen
		while ((i < p) && (j < q)) {
			comparisons[0]++;
			if (arrayA[i] <= arrayB[j]) {
				mergedArray[k] = arrayA[i];
				i++;
			}
			else {
				mergedArray[k] = arrayB[j];
				j++;
			}
			k++;
		}
		
		
		//If all of arrayA has been added into the merged array,
		//	then we simply add the rest of arrayB into the merged array
		//	(and vice versa)
		if (i == p) {
			for (int index = j; index < q; index++) {
				mergedArray[k + index - j] = arrayB[index];
			}
		}
		else {
			for (int index = i; index < p; index++) {
				mergedArray[k + index - i] = arrayA[index];
			}
		}
		
		
		//Combines our array and number of comparisons so we can return both
		double[][] outputArray = new double[][] {
			mergedArray, comparisons
		};
		return outputArray;
	}
	
	
	
	//Quick sort also uses divide and conquer, repeatedly partitioning the array
	//	and splitting based on the split position given by partition method
	public double[][] quickSort(double[] inputArray, int l, int r, double[] comparisons) {
		if (l < r) {
			double[][] partitionResults = partition(inputArray, l, r, comparisons);
			
			int s = (int)partitionResults[0][0];
			comparisons = partitionResults[1];
			
			quickSort(inputArray, l, s, comparisons);
			quickSort(inputArray, s + 1, r, comparisons);
		}

		
		//Combines our array and number of comparisons so we can return both
		double[][] outputArray = new double[][] {
			inputArray, comparisons
		};
		return outputArray;
	}
	
	//Helper method, partition, partitions a sub array by using its first
	//	element as a pivot, and returning the position to split the sub array
	public double[][] partition(double[] inputArray, int l, int r, double[] comparisons) {
		int pivotPos = random.nextInt(r - l) + l;
		
		double p = inputArray[pivotPos];
		int i = l;
		int j = r + 1;

		
		//Find position i where array at i is greater than p and position j
		//	where array at j is less than p, then swap elements at i and j;
		//	keep doing this until i and j cross over
		while (i < j) {
			comparisons[0] += 2;
			do {
				i++;
			} while (inputArray[i] < p);
			do {
				j--;
			} while (inputArray[j] > p);
			
			double elementA = inputArray[i];
			inputArray[i] = inputArray[j];
			inputArray[j] = elementA;
		}
		
		//Undo the last swap where i and j were crossed over
		double elementA = inputArray[i];
		inputArray[i] = inputArray[j];
		inputArray[j] = elementA;
		
		double elementB = inputArray[pivotPos];
		inputArray[pivotPos] = inputArray[j];
		inputArray[j] = elementB;
		
		return new double[][] {new double[] {j}, comparisons};
	}
	
	
	//Generates array of random elements of desired size
	public double[] generateArray(int inputSize) {
		double[] newArray = new double[inputSize];
		
		for (int i = 0; i < inputSize; i++) {
			newArray[i] = random.nextDouble();
		}
		
		return newArray;
	}
}
