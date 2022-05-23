
public class towersOfHanoi {
	public static void moreRecursion(int disk, int from, int using, int to) {
		if(disk >= 1) {
			//moves all but the bottom peg from the 'from' to 'using'
			moreRecursion(disk - 1, from, to, using);
			
			System.out.println();
			//moves all pegs from the using to to
			moreRecursion(disk -1, using, from, to);
			
		}
	}
	public static int binarySearchItr(int [] a, int key, int start, int end) {
		int midpoint;
		
		while(true) {
			if(start >= end) {
				return -1;
			}
			
			
			midpoint = (start + end) / 2;
		
			if(a[midpoint] == key) {
				return midpoint;
			}
			else if(key < a[midpoint]) {
				//binanrySearchRec(a, key, start, midpoint);
				end = midpoint;
			}
			else {
				//binarysearchRec(a, key, midpoint +1, end);
				start = midpoint +1;
			}
		}
	}

}
