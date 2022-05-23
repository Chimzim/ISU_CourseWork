#include <stdio.h>
int main () {
int dividend = 8; //sets the dividend equal to 8
int divisor = 3; //sets the divisor equal to 3

printf("Enter in a number for the divisor: "); //prompts the user to enter in a value for divisor 
scanf("%d", &divisor);

printf("reminder is %d\n", (dividend%divisor) ); // prints the reminder of the dividend by the divisor
return 0;
}