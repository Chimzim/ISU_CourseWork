package lab9;
import lab9.ArraySum;
public class Pyramid {
	public static int getPyramidCount(int levels) {
		int sumBalls = 0;
		if(levels == 1) {
			return 1;
		}
		else {
			return sumBalls += getPyramidCount(levels -1) + levels * levels;
		}
		
	}
	
	
	public static void main(String [] args) {
		System.out.println(getPyramidCount(7));
	}
	
}
