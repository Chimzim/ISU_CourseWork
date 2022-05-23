#include <stdio.h>
int main () {
int dividend = 8;
float divisor = 3;

printf("Enter in a number for the divisor: ");
scanf("%lf", &divisor);

printf("reminder is %lf\n", dividend % divisor);

}