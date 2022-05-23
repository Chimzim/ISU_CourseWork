/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>
/* This program takes two inputs acceleration
    and mass, and ouputs the force = mass*acceleration */

void force(double m, double accel);

int main() {
    double mass, accel; //accel variable was not defined 

    printf("Enter an acceleration: ");
    scanf("%lf", &accel); // changed %d to lf since variable is a double value

    printf("Enter the mass of the object: ");
    scanf("%lf", &mass); //changed %d to lf since variable is a double value 

    force(mass, accel);

    printf("You entered %lf m/s^2\n", accel);
    printf("You entered %lf kg\n", mass);

    return 0;
}

void force(double m, double accel) {
    double mass = m / 1000; //mass in the function wasn't defined so I defined as double

    printf("The force is %lf milliNewtons\n", mass * accel);

    accel = accel*1000;

    printf("The force is %lf Newtons\n\n", mass * accel);
}
