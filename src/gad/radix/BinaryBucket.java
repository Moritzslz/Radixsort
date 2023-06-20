package gad.radix;

public class BinaryBucket {

	private int[] bucket;
	int leftPointer;
	int rightPointer;

	public BinaryBucket(int size) {
		bucket = new int[size];
		leftPointer = 0;
		rightPointer = bucket.length - 1;
	}

	public void insertLeft(int number) {
		if (leftPointer >= 0) {
			bucket[leftPointer] = number;
			leftPointer--;
		}
	}

	public void insertRight(int number) {
		if (rightPointer < bucket.length) {
			bucket[rightPointer] = number;
			rightPointer++;
		}

	}

	public int getMid() {
		return leftPointer;
	}

	public void logArray(Result result) {
		result.logArray(bucket);
	}
}
