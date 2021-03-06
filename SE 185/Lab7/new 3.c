/*-----------------------------------------------------------------------------
-					          SE 185 Lab 07
-             Developed for 185-Rursch by T.Tran and K.Wang
-	Name: Chimzim Ogbondah
- 	Section: D
-	NetID: ogbondah
-	Date: 9.23.2018
-
-   This file provides the outline for your program
-   Please implement the functions given by the prototypes below and
-   complete the main function to make the program complete.
-   You must implement the functions which are prototyped below exactly
-   as they are requested.
-----------------------------------------------------------------------------*/ // -39 < x < 39

/*-----------------------------------------------------------------------------
-								Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>


/*-----------------------------------------------------------------------------
-								Defines
-----------------------------------------------------------------------------*/
#define PI 3.141592653589
/* NO GLOBAL VARIABLES ALLOWED */


/*-----------------------------------------------------------------------------
-								Prototypes
-----------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------
    PRE: Arguments must point to double variables or int variables as appropriate
    This function scans a line of DS4 data, and returns
    True when left button is pressed
    False Otherwise
    POST: it modifies its arguments to return values read from the input line.
------------------------------------------------------------------------------------*/
int read_input( int* time,
                double* g_x, double* g_y, double* g_z,
                int* button_T, int* button_C, int* button_X, int* button_S,
                int* l_joy_x, int* l_joy_y, int* r_joy_x, int* r_joy_y );

/*-----------------------------------------------------------------------------
    PRE: ~(-1.0) <= mag <= ~(1.0)
    This function scales the roll/pitch value to fit on the screen.
    Input should be capped at either -1.0 or 1.0 before the rest of your
    conversion.
    POST: -39 <= return value <= 39
-----------------------------------------------------------------------------*/
int scaleMagForScreen(double rad);

/*-----------------------------------------------------------------------------
    PRE: -128 <= mag <= 127
    This function scales the joystick value to fit on the screen.
    POST: -39 <= return value <= 39
-----------------------------------------------------------------------------*/
int scaleJoyForScreen(int rad);

/*----------------------------------------------------------------------------
    PRE: -39 <= number <= 39
    Uses print_chars to graph a number from -39 to 39 on the screen.
    You may assume that the screen is 80 characters wide.
----------------------------------------------------------------------------*/
void graph_line(int number);

/*-----------------------------------------------------------------------------
    PRE: num >= 0
    This function prints the character "use" to the screen "num" times
    This function is the ONLY place printf is allowed to be used
    POST: nothing is returned, but "use" has been printed "num" times
-----------------------------------------------------------------------------*/
void print_chars(int num, char use);


/*----------------------------------------------------------------------------- //for loop 39 spaces and then however many rs for right 
-								Implementation
-----------------------------------------------------------------------------*/
int main()
{
    double x, y, z;                     /* Values of x, y, and z axis*/
    int t;                              /* Variable to hold the time value */
    int b_Up, b_Down, b_Left, b_Right;  /* Variables to hold the button statuses */
    int j_LX, j_LY, j_RX, j_RY;         /* Variables to hold the joystick statuses */
    int scaled_pitch, scaled_roll; 	    /* Value of the roll/pitch adjusted to fit screen display */
    int scaled_joy;                     /* Value of joystick adjusted to fit screen display */
	
    /* Put pre-loop preparation code here */
    do
    {
        /* Scan a line of input */
		read_input(&t, &x, &y, &z, &b_Up, &b_Down, &b_Left, &b_Right, &j_LX, &j_LY, &j_RX, &j_RY);
        /* Calculate and scale for pitch AND roll AND joystick */
		scaled_roll = scaleMagForScreen(x); //calculates number for the roll based off of function calls with the scanned in values from the controller
		scaled_pitch = scaleMagForScreen(z); //calculates number for the pitch based off of function calls with the scanned in values from the controller
		scaled_joy = scaleJoyForScreen(j_LX); //calculates number for the joystick based off of function calls with the scanned in values from the controller
        /* Switch between roll, pitch, and joystick with the up, down, and right button, respectively */
		if (b_Left == 1) { // switches to pitch when the square button is pressed
			graph_line(scaled_pitch);
			}
		else if(b_Right == 1) { //switches to pitch when the circle button is pressed
			graph_line(scaled_roll);
		}
		else if(b_Up == 1) { //switches to joystick when the triangle button is pressed
			graph_line(scaled_joy);
		}
		if(b_Down == 1) { //end the program when the X button is pressed 
			break;
		}
		
       // Output your graph line 


        fflush(stdout);

    } while (1); /* Modify to stop when left button is pressed */

    return 0; 

}

int read_input( int* time, double* g_x, double* g_y, double* g_z, int* button_T, int* button_C, int* button_X, int* button_S, int* l_joy_x, int* l_joy_y, int* r_joy_x, int* r_joy_y ) {
	scanf("%d, %lf, %lf, %lf, %d, %d, %d, %d, %d, %d, %d, %d", time, g_x, g_y, g_z, button_T, button_C, button_X, button_S, l_joy_x, l_joy_y, r_joy_x, r_joy_y); //returns the memory address that points tha values initialized inside the main function
		
	if(*button_S == 1) {
		return 1; //returns true if the square button is pressed 
	}
	else {
		return 0; //returns false if any other button is pressed 
	}
}	

int scaleMagForScreen(double rad) {
	double radx = 0;
	if(rad > 1.0) { // sets the input to 1 if it is greater than 1
		rad = 1;
		return 39;
	}
	else if(rad < -1.0) { //sets the input to negative one if it is less than -1
		rad = -1;
		return -39; //set
	}
	else if (rad > 0.0 && rad < 1.0) { //returns rad times 39  for positive numbers
		return rad * 39;
	}
	else if(rad < 0.0 && rad > -1.0) { //returns rad for negative numbers 
		return rad * 39;
	}
}

void graph_line(int number) {
	if (number < 0) { //checks to see if the number inputed into the graph line is less than 0
		print_chars(39 + number, ' ');
		print_chars(-number, 'l'); // then  it prints l for the left orientation 
		print_chars(1, '\n');
	}
	else if(number > 0) { //if the number is greater than zero it loops 40 times printing spaces 
		print_chars(39, ' ');
		print_chars(number, 'r'); //then prints rs
		print_chars(1, '\n');
	}
	else if (number == 0) { //if the number is equal to 0 then it loops 40 times printing spaces and then prints a zero 
		print_chars(39, ' ');
		print_chars(1, '0'); //prints the zero after for loop 
		print_chars(1, '\n');
	}
	fflush(stdout);
}

void print_chars(int num, char use) {
	int i;
	for (i = 0; i < fabs(num); ++i) { //loops to print the character based on the abs value of the number 
		printf("%c", use);
		fflush(stdout);
	}
}
			
int scaleJoyForScreen(int rad) {
	return (rad * (39.0 / 127.0) ); //scaled the joystick to the screen 
}

