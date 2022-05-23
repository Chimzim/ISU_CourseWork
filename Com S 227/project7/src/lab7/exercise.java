package lab7;
import java.util.Arrays;
import java.util.Random;
public class exercise {
	
	public static int[] getPositiveNumbers(int[] numbers) {
		int count = 0;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] > 0) {
				count++;
			}
		}
		int[] newNumber = new int[count];
		for(int j = 0, k = 0; j < numbers.length; j++) {
			if(numbers[j] > 0) {
				newNumber[k] = numbers[j];
				k++;
			}
		}
		return newNumber;
	}
	public static int[] randomExperiment(int max, int iters) {
		int[] counts = new int[iters];
		Random rand = new Random();
		for(int i = 0; i < counts.length; i++) {
			int userInput = rand.nextInt(max);
			counts[i] = userInput;
		}
		return counts;
	}
	public static void main(String[] args) {
		int[] a = {1, -12, -13, 4, -5, 6, 7, -9, 10, 100};
		//exercise g = new exercise();
		System.out.println(Arrays.toString(randomExperiment(10, 1000)));
		System.out.println(Arrays.toString(getPositiveNumbers(a)));
	}
}
