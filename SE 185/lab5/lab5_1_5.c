/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>

/* This progam calculates the sum of 1 to x, where x is a user input */

int sum_funct(int n);

int sum_main();

void main() { //moved the curly bracket up 

    int input;

    printf("Please input a number from to sum up to: ");

    scanf("%d", &input);

    printf("The sum of 1 to %d is %d\n", input, sum_funct(input));

}

int sum_main(){ //renamed function to sum name to avoid conflicting call
	printf("Sum is 32!\n"); //moved the print statement inside of the fucntion
	}

int sum_funct(int n){ //moved the curly bracket infront of sum_fraction

    return (n*(n+1))/2;
}
