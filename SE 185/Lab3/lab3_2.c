/*-----------------------------------------------------------------------------
-					          SE 185 Lab 03
-             Developed for 185-Rursch by T.Tran and K.Wang
-	Name: Chimzim Ogbondah
- 	Section: SE 185
-	NetID: ogbondah
-	Date: Septermber 17, 2018
-----------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------
-	                            Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>


/*-----------------------------------------------------------------------------                      
------------------

-----------------------------------------------------------*/
#define TRUE 1


/*-----------------------------------------------------------------------------
-	                            Prototypes
-----------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------
-							  Implementation
-----------------------------------------------------------------------------*/
int main(void) {
int TRIANGLE; /* Defining Triangle to int value */
int SQUARE; /* Defining SQUARE to int value */
int CIRCLE; /* Defining CIRCLE to int value */
int X; /* Defining X to int value */
    while (TRUE) {
		scanf("%d, %d, %d, %d", &TRIANGLE, &SQUARE, &CIRCLE, &X); /* Scanning in vaules for the TRIANGLE, CIRCLE, X, SQUARE through the PS4 controller */
		
		printf("Buttons being pressed: %d\r\n", buttonsPressed(totalButtonsPressed)); /* statement will print the number of buttons being pressed by calling the function below */
		fflush(stdout); /* TA said put this in */

    }

  return 0;
}

/*Function for summing up the number of buttons being pressed on the PS4 controller */
int buttonsPressed(int TRIANGLE, int SQUARE, int CIRCLE, int X) {
	totalButtonsPressed = TRIANGLE + SQUARE + CIRCLE + X; /* Equation to calculate the sum of buttons being pressed */
}