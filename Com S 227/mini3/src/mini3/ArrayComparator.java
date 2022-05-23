package mini3;

import java.util.Comparator;

/**
 * A comparator that compares two int arrays starting from index 0 
 * to the end of the arrays. If at a given index two arrays differ, 
 * the one with a smaller value is ordered before the other. if no difference
 * is found upon reaching to the end of the shorter array, the shorter
 * array is ordered before the longer array.
 * <p>
 * See the pdf for explanation and sample usage.
 */
public class ArrayComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for(int i=0; i<len; ++i) {
            if(a[i]<b[i])
                return -1;
            else if(a[i]>b[i])
                return 1;
        }
        return a.length-b.length;
            
            
    }
}