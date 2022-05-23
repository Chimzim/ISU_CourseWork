package edu.iastate.cs228.hw3;

public class TestNumber2 {
	
	public static int[] reverse(int[] arr) {
		int[] test = new int[arr.length];
		int leftHalf = arr.length/2;
		int rightHalf = (arr.length - leftHalf);
		int j = 0;
		int i = 0;
		int iterations = rightHalf/2;
		while(i < arr.length) {
			if(i < leftHalf/2) {
				int temp = arr[i];
				arr[i] = arr[leftHalf-1-i];
				arr[leftHalf-1-i] = temp;
			}
			if(i >= leftHalf && iterations >= 0) {
				int temp = arr[i];
				arr[i] = arr[arr.length-j-1];
				arr[arr.length-j-1] = temp;
				iterations--;
				j++;
				}
			i++;
		}
		for(int c = 0; c < test.length; c++) {
			test[c] = arr[c];
		}
		return test;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	int[] myArray = new int[10];
	for(int i = 0; i < myArray.length; i++) {
		myArray[i] = i;
	}
	for(int i = 0; i < myArray.length; i++) {
		System.out.print(myArray[i] + ",");
	}
	System.out.println(reverse(myArray));
	System.out.println();
//	System.out.println("***********************");
	//for(int i = 0; i < myArray.length; i++) {
		//System.out.print(myArray[i] + ",");
	//}
	}

}
