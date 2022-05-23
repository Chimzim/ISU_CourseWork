#include <stdio.h>

int main() {
	int matrix[3][3]; //the double array where the numbers will be stored
	int sum; //variable that will store the sum
	int i, j; //variables that will loop through and allow user to input numbers in both columns and rows
	for(i = 0; i < 3; ++i) { //for loop to take the users inputs
		for(j = 0; j < 3; ++j) {
			scanf("%d", &matrix[i][j]); //storing the input in the respective placement
		}
	}
	for(i = 0; i < 3; ++i) { //for loop that checks will sum up the numbers in the matrix
		for(j = 0; j < 3; ++j) {
			sum = sum + matrix[i][j]; //keeps adding each number in the array into sum 
		}
	}
	printf("the sum of all elements in the matrix is: %d", sum); //prints out the final sum of all numbers
			
	
}