#include <stdio.h>
#include <math.h>


void main() {
	double r, s, t;
printf("Please enter a positive or a negative value in for r:\n "); // asks the user to enter in a value for r 
scanf("%lf", &r);
printf("Please enter a positive or a negative value in for s:\n"); // asks the user to enter in a value for s
scanf("%lf", &s);
printf("Please enter a positive or a negative value in for t:\n"); // asks the user to enter in a value for t
scanf("%lf", &t);

double SD = sqrt( pow(r, 3) +  ( (8 * (s * s) ) / (1 + t) ) ); //function that will calculate the value of SD
printf("%lf", SD); // will print the value of SD to the terminal


}