package mergeSort;

import java.util.Arrays;

public class mergeSort {
	private static int [] merge(int []a, int []b) {
		int [] r = new int [a.length + b.length];
		int i = 0, j = 0;
		
		while(i < a.length && j < b.length) {
			if(a[i] < b[j]) {
				r[i+j] = a[i];
				i += 1;
			}
			else {
				r[i+j] = b[j];
				j += 1;
			}
		}
		while(i+j < r.length) {
			if(i == a.length) {
				r[i+j] = b[j];
				j += 1;
			}
			else {
				r[i+j] = a[i];
				i += 1;
			}
		}
		return r;
	}
	
	private static void sort(int [] a) {
		if(a.length <= 1) {
			return; 
		}
		int [] frontHalf = Arrays.copyOf(a,  a.length/2);
		int [] backHalf = Arrays.copyOfRange(a, a.length/ 2, a.length);
		
		sort(frontHalf);
		sort(backHalf);
		
		int [] r = merge(frontHalf, backHalf);
		
		for(int i = 0; i < r.length; i++) {
			a[i] = r[i];
		}
		
	}
}

