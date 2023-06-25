package gad.radix;

public class BinaryBucket {

	private int[] bucket;
	private int leftPointer;
	private int rightPointer;


	public BinaryBucket(int size) {
		bucket = new int[size];
		leftPointer = 0;
		rightPointer = bucket.length - 1;
	}

	public void insertLeft(int number) {
		if (leftPointer < bucket.length) {
			bucket[leftPointer] = number;
			leftPointer++;
		}
	}

	public void insertRight(int number) {
		if (rightPointer >= 0) {
			bucket[rightPointer] = number;
			rightPointer--;
		}

	}

	public int getMid() {
		return leftPointer;
	}

	public void logArray(Result result) {
		result.logArray(bucket);
	}

	public int getSize() {
		return bucket.length;
	}

	public int getValue(int index) {
		if (index >= 0 && index < bucket.length) {
			return bucket[index];
		} else {
			return bucket.length;
		}
	}

	public void setValue(int value, int index) {
		bucket[index] = value;
	}

	public int[] getBucket() {
		return bucket;
	}

	public void setBucket(int[] bucket) {
		this.bucket = bucket;
	}

	public void reverseSubarray(int index) {
		int start = index;
		int end = bucket.length - 1;

		while (start < end) {
			int temp = bucket[start];
			bucket[start] = bucket[end];
			bucket[end] = temp;

			start++;
			end--;
		}
	}

}
