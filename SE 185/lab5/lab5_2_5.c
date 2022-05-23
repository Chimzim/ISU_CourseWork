/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>

/* This program takes in a number from the user and checks if it is
 * a whole number. It also should print if the number is a
 * postive, negative, or zero number.
 * Ex.
 *      input:  num = 5
 *      output: 5 is a postive and 5 is non-negative and 5 is non-zero and 5 is a whole number.*/

// int isPositive(int n); changed all to double call since num is used in all
double isPositive(double n);
// int isNegative(int n); changed all to double call since num is used in all
double isNegative(double n);
// int isZero(int  n); changed all to double call since num is used in all and needs to be zero for last function to work right 
double isZero(double n);
int main()
{
    // int num; changed num to a double value 
	double num;
    printf("Please type a number between -1000 and 1000: ");
    // scanf("%d", &num); changed %d to %lf for double callo
	scanf("%lf", &num);

    // if(num > 1000 | num < -1000) added another | so the or statement was complete
    if(num > 1000 || num < -1000) {
		printf("Number is out of range!\n");
        return -1;
    }

    // if( ( isPositive(num) & !isNegative(num) ) | isZero(num) ) added another & to complete the and statement and another | 
    if( (isPositive(num) && !isNegative(num) ) || isZero(num) ) {
		printf("%lf is a whole number.\n", num); //changed %d to %lf
    }
    else
    {
        printf("%lf is non-whole number.\n", num); //changed %d to %lf
    }

    return 0;
}

// int isPositive(int n) changed to match with first prototype call 
double isPositive(double n){
    if(n>0)
    {
        printf("%lf is postive and ", n); //changed %d to %lf
        return 1;
    }
    else
    {
        printf("%lf is non-postive and ", n); //changed %d to %lf
        return 0;
    }
}

// int isNegative(int n)changed to match first call
double isNegative(double n){
    if(n<0)
    {
        printf("%lf is negative and ", n); //changed %d to %lf
        return 1;
    }
    else
    {
        printf("%lf is non-negative and ", n); //changed %d to %lf
        return 0;
    }
}

// int isZero(int n) changed to match first call 
double isZero(double n){
    if(n==0)
    {
        printf("%lf is zero and ", n); //changed %d to %lf
        return 1;
    }
    else
    {
        printf("%lf is non-zero and ", n); //changed %d to %lf26
        return 0;
    }
}
