#include <stdio.h>
#include <math.h>

double SD_Fun(double r, double s, double t);

int main() {
	double r, s, t, SD;
	printf("Please enter a positive or a negative value in for r:\n "); // asks the user to enter in a value for r 
	scanf("%lf", &r);
	printf("Please enter a positive or a negative value in for s:\n"); // ask the user to enter in a value for s
	scanf("%lf", &s);
	printf("Please enter a positive or a negative value in for t:\n"); //asks the user to enter in a value for t
	scanf("%lf", &t);
	
	if((t + 1)==0) { // if else statement checks to see if bottom sum is not equal to zero
		return 0;
	}
	
	
	printf("%lf", SD_Fun(r, s, t));
	
	return 0;
}

double SD_Fun(double r, double s, double t) {
	double r_sum = pow(r, 3); //gives the sum of r to the power of 3
	double s_sum = pow(s, 2); //gives value of s to the power of 2
	double bottom_sum = 1 + t; //gives summation of 1 + the value of t
	double SD = sqrt( (r_sum + ( (8 * s_sum) / (bottom_sum) ) ) );
	return SD;

}