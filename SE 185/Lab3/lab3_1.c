/*-----------------------------------------------------------------------------
-					          SE 185 Lab 03
-             Developed for 185-Rursch by T.Tran and K.Wang
-	Name: Chimzim Ogbondah
- 	Section:SE 185
-	NetID: ogbondah
-	Date: September 17, 2018
-----------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------
-	                            Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>


/*-----------------------------------------------------------------------------
-	                            Defines
-----------------------------------------------------------------------------*/
#define TRUE 1


/*-----------------------------------------------------------------------------
-	                            Prototypes
-----------------------------------------------------------------------------*/
/* Put your function prototypes here */
double mag(double x, double y, double z);
int minutes(int t); /* Defining minutes function to an integer value */
int seconds(int t); /* Defining seconds function to an integer value */
int millis(int t); /* Defining milliseconds function to an integer value */

/*-----------------------------------------------------------------------------
-							Implementation
-----------------------------------------------------------------------------*/
int main(void) {
    /* DO NOT MODIFY THESE VARIABLE DECLARATIONS */
    int t;
    double  ax, ay, az;


    while (TRUE) {
        scanf("%d,%lf,%lf,%lf", &t, &ax, &ay, &az);

/* CODE SECTION 0 */
        printf("Echoing output: 8.3%d, 7.4%lf, 7.4%lf, 7.4%lf\n", t/1000, ax, ay, az); /* divided t by 1000 to turn milliseconds into seconds */

/* 	CODE SECTION 1 */
        printf("At %d ms, the acceleration's magnitude was: %lf\n", t, mag(ax, ay, az));

/* 	CODE SECTION 2 */
        
    printf("At %d minutes, %d seconds, and %d milliseconds it was: %lf\n",
        minutes(t), seconds(t), millis(t), mag(ax,ay,az)); /* calls to the functions below (minutes, seconds, millseconds, and mag. then prints out their values based on the function output */
    

    }

  return 0;
}

/*Fuction the calculate the acceleration's magnitude*/
double mag(double x, double y, double z) {
    //Step 8, uncomment and modify the next line
    return sqrt(pow(ax, 2) + pow(ay, 2) + pow(az, 2)); /* eqaution to measure the accelerations magnitude */
}
/* function to calculate minutes column  */
int minutes(int t) {
	return t / 60000; /* Equation for minutes column */
}
/* Function to calculate seconds column */
int seconds(int t) {
	return (t % 60000) / 1000; /* Equation for the seconds column */
}
/* Function to calculate the milliseconds column */
int millis(int t) {
	return (t % 60000) % 1000; /* Equation for the milliseconds column */
}
