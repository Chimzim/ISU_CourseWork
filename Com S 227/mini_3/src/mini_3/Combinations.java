package mini_3;

public class Combinations {
	public static int[][] getCombinations(int[] choices) {
		if(choices.length == 0) {
			return null;
		}
		else if(choices.length==1) {
			int elmo = choices[0];
			int[][] sandwhich = new int[elmo][1];
			for(int i = 0; i < elmo; i++) {
				sandwhich[i][0] = i;
			}
			return sandwhich;
		}
		else {
			int bigBird = choices.length;
			int[] burger = new int[bigBird-1];
			for(int i = 1; i < bigBird; i++) {
				burger[i-1] = choices[i];
			}
			int sandwhich[][] = getCombinations(burger);
			int size = sandwhich.length;
			int sandwhichArr[][] = new int[size*choices[0]][bigBird];
			
			int k = 0;
			for(int i = 0; i < choices[0]; i++) {
				for(int[] row: sandwhich) {
					sandwhichArr[k][0] = i;
					for(int j = 1; j < bigBird; j++) {
						sandwhichArr[k][j] = row[j-1];
					}
					k++;
				}
			}
			return sandwhichArr;
		}
	}
}
