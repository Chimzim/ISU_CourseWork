/*-----------------------------------------------------------------------------
-                             SE 185 Lab 04
-             Developed for 185-Rursch by T.Tran and K.Wang
-   Name:
-   Section:
-   NetID:
-   Date:
-----------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------
-                               Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>


/*-----------------------------------------------------------------------------
-                               Defines
-----------------------------------------------------------------------------*/
#define TRUE 1
#define FALSE 0
#define Up 2
#define BOTTOM 3
#define RIGHT 4
#define DOWN 5
#define TOP 6
#define LEFT 7 

/*-----------------------------------------------------------------------------
-                               Prototypes
-----------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------
-                             Implementation
-----------------------------------------------------------------------------*/
int close_to(double tolerance, double point, double value); /* defining the functions for later use under the main function */
double mag(double x, double y, double z); /* defining the functions for later use under the main function */
int buttonExt(b1); /* defining the functions for later use under the main function */
int main(void) {
    int t b1, b2, b3, b4;
	int orientation = 0; // setting the orientation to an integer vaule as all of them correspond with a number
    double ax, ay, az, gx, gy, gz; 



    while (TRUE) {
        scanf("%d, %lf, %lf, %lf, %lf, %lf, %lf, %d, %d, %d, %d", &t, &ax, &ay, &az, &gx, &gy, &gz, &b1, &b2, &b3, &b4);

        /* printf for observing values scanned in from ds4rd.exe, be sure to comment or remove in final program */
        /*printf("Echoing output: %d, %lf, %lf, %lf, %lf, %lf, %lf, %d, %d, %d, %d \n", t, ax, ay, az, gx, gy, gz, b1, b2, b3, b4); */

        

        //printf("At %d ms, the acceleration's magnitude was: %f\n", t, mag(ax, ay, az));
		// allows the user to exit the code if triangle is pressed 
		if buttonExt(b1) {
			break;
		}
		if close_to(0.05, 0 ,mag(ax, ay, az) ) { 
		    if ( close_to_neg(0.32, -1.0, gz)  && orientation != 2 {
			printf("UP/n");
			orientation = 2; // makes the orientation the current(2) so it cannot print twice in a rown
			}
			else if ( close_to_neg(0.35, -1.0, gy)  && (orientation != 3) ) {
			printf("BOTTOM\n");
			oreintation = 3; // makes the orientation the current(3) so it cannot print twice in a rown
			}
			else if (close_to_neg(0.35, -1.0, gx) ) && (orientation !=4) )
			{
			printf("RIGHT/n");
			oreintation = 4; // makes the orientation the current(4) so it cannot print twice in a rown
			}
			else if (close_to(0.35, 1.0, gz) && (oreintation != 5) )(5) {
			pritnf("DOWN/n");
			oreintation = 5; // makes the orientation the current(5) so it cannot print twice in a rown
			}
			else if (close_to(0.35, 1.0, gy) (orientation != 6 ) {
			printf("TOP\n");
				oreintation = 6; // makes the orientation the current(6) so it cannot print twice in a rown
			}
			else if(close_to(0.35, 1.0, gz) && (oreintation != 7) ) {
			printf("LEFT\n");
			oreintation = 7; // makes the orientation the current(7) so it cannot print twice in a rown
			}
		
			
		
		}

    return 0;
}

/*CLose to function which checks to make sure the the point is within the tollerance for each point*/
int close_to(double toloerance, double point, double value) {
	double lowerTol = point - value; // assigns the max the point can be 
	double UpperTol = point + value; //assigns the minimum that the point can be 
	if ( (LowerTol <= point) && (point <= UpperTol) ) {
		//if else checks to see if it is within or not
		return TRUE;
	}
		else {
			return FALSE;
    }
/*Function to calculate the accelerations' magnitude */
double mag(double x, double y, double z) {
    //Step 8, uncomment and modify the next line
    return (double)(sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2)));
}
/* Function that will allow the use to exit the program  at will*/
int buttonExt(int b1) {
	if (b1 == 1) {
		return TRUE; // if the TRIANGLe BUTTON is pressed the user can exit the program
	}
	else {
		return FALSE;
	}
