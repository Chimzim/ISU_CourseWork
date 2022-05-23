

/*-----------------------------------------------------------------------------
-					          SE 185 Lab 06
-             Developed for 185-Rursch by T.Tran and K.Wang
-	Name: CHimzim Ogbondah
- 	Section: D
-	NetID: ogbondah
-	Date:
-----------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------
-	                            Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>

/*-----------------------------------------------------------------------------
-	                             Defines
-----------------------------------------------------------------------------*/
#define TRUE 1
#define FALSE 0

/*-----------------------------------------------------------------------------
-	                            Prototypes
-----------------------------------------------------------------------------*/
int start_menu(int start);
int buttonPress(int T, int C, int x, int S, int answer);
int time_check(int currentTime);
int printButton(int values);
/*-----------------------------------------------------------------------------
-							  Implementation
-----------------------------------------------------------------------------*/
int main() {
	int round = 0;
	int TRIANGLE, SQUARE, CIRCLE, CROSS; //defining the variables that the controller will later scan in 
	int Triangle1, Circle2, Cross3, SQUARE4; //initilizing the variables that will equate to the corresponding values inside of the int array
	int t, accpetedOrNot; //defining t so it can be scanned in by the contrller and accpetedOrNot for my time_check function 
	int randmod = rand() % 4 + 1; //this will allow me to randomize my array without having to do the calculations over and over 
	int currentTime, prev_Time; //defining currentTime and prev_Time to be used as well in my time_check function.
	int wait = 0; //meaning less vairable just meant to loop before game starts 
	int buttonsToPress[4] = {1, 2, 3, 4}; // Triange is 1 Circle is 2 Cross is 3 Square is 4
	printf("Please press the Circle button to begin the game\n"); //tells the user to press the circle button to begin the game
		
	scanf("%d, %d, %d, %d, %d", &t, &TRIANGLE, &CIRCLE, &CROSS, &SQUARE); //time and buttons for the controller are scanned in 
	while (start_menu(CIRCLE) == 0) { //loops until the user presses the circle button making it false 
		wait = wait + t;
	}
	
	while (start_menu(CIRCLE) == 1) { //starts the game 
		int game = 1; //sets game as 1 so it can be used once this while loop breaks 
		if (game == 1 && CIRCLE == 0) { // waits for the circle button to be unpressed before exiting the loop
			break;
		}
		else {
			return 1; //if circle button still being pressed it loops again
		}
	}
		srand(time(NULL));
	while (game == 1) { //The actaul game while loop 
		while (TRIANGLE == 1 || CIRCLE == 1 || CROSS == 1 || SQUARE ==1) {
			scanf("%d, %d, %d, %d, %d", &t, &TRIANGLE, &CIRCLE, &CROSS, &SQUARE);
		}
			
		do {
			scanf("%d, %d, %d, %d, %d", &t, &TRIANGLE, &CIRCLE, &CROSS, &SQUARE);
		}while( TRIANGLE == 0 && CIRCLE == 0 && CROSS == 0 && SQUARE == 0);//sets the random number inside of the int array 
		PrintButton( (ButtonstoPress[randmod]) );		//programs prints the button to press to the user 
		int answer = PrintButton[randmod];
		if (round == 0)	{ //if it is the first round then it prints out the time at zero 
			time_check(0);
		}
			
		if( ( (buttonPress(TRIANGLE, CIRCLE, CROSS, SQUARE)  && time_check(t)) && (round > 0) ) ) { //another if statement allows this to iterate as well checking to see if the right button was pressed 
			round = round + 1; //if corrected 1 is added to the round
		}
		else if ( (buttonPress(TRIANGLE, CIRCLE, CROSS, SQUARE) == 1) && (time_check(t) == 0) ){ //if you answer correct but the time ran out 
			printf("Sorry you ran out of time! "); // it pritns that you ran out of time and how many rounds you made it to 
			printf("You made it %d rounds\n", round);
			break; //exits the statement 
		}
		else if( (buttonPress(TRIANGLE, CIRCLE, CROSS, SQUARE) == 0) && (time_check(t) == 1) ) { //if you answered wrong 
			printf("Sorry you answered incorrectly, you made it %d rounds\n", round);  //prints out that you answered incorrectly and how many rounds you made it to 
			break; //exits the statement
		}
		else if (round >= 25) { // if you make it to round 25 it announces to the user that they won the game 
			printf("Congrats you won the game!\n");
			break; //exits the statement
		}
		else {
			printf("Sorry you answered incorrectly, you made it %d rounds", round); //prints how may rounds the user made it to
			break;
		}
	
		
		return 0; //returns zero if the user wins the game or lose which exits the game loop
				
		
	
	}

}

int start_menu(int start) { //start menu function 
	if(start == 1) { // returns true if the circle button on the controller was pressed 
		return TRUE;
	}
	else {
		return FALSE; //if circle was not pressed it returns false 
	}
}
 
int time_check(int currentTime) { //time_check function 
	int scoreTime = 2200; //sets the game time equal to 2200 miliseconds 
	int prev_Time = 0; //sets the previous time to zero 
	int accpetedOrNot; //variable that will print how much time is left
		if((currentTime + prev_Time ) < scoreTime && (currentTime + prev_Time) > 0) { //if the current time and the previous time are less than the score time it returns true
			accpetedOrNot = scoreTime - (currentTime + prev_Time); //variable is equal to how much time is remaining based off of how long the user took to answer 
			printf(" You have %d miliseconds left to answer!", accpetedOrNot); //prints how much time is left for the user to answer 
			return TRUE;
			
		}
		else if(currentTime + prev_Time) >= scoreTime) { //returns false if greater than the value
			
			return FALSE;
		}
		else if(currentTime == 0){
			printf("You have %d miliseconds left to answer!", scoreTime);
		}
}
		


int buttonPress(int T, int C, int x, int S, int answer) {
	
	if(T == 1) {
		int Triangle1 = 1; //if Triangle is pressed and is equal to the random button returns true
		if (buttonToPress[answer] == Triangle1) {
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	else if(C == 1) {
		int Circle2 = 2;
		if (buttonToPress[answer] == Circle2) { //if circle is pressed and is equal to the random button returns true
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	else if (x == 1) {
		int Cross3 = 3;
		if (buttonToPress[answer] == CROSS) { //if cross is pressed and is equal to the random button returns true
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	else if(S == 1) { //if square is pressed and is equal to the random button returns true
		int SQUARE4 = 4;

		if (buttonToPress[answer] == SQUARE4) {
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	}

int printButton( int values) {
	int Triangle = 1; // set triangle to 1 to line up with the int array
	int Circle2 = 2; // set circle to 2 to line up with the int array
	int Cross3 = 3; // set cross to 3 to line up with the int array
	int SQUARE4 = 4; // set square to 4 to line up with the int array
	
	if (values == Triangle1) {
		printf("Press the Triangle button!\n"); //if the random button is equal to the value of triangle1 it prints press triagnle
		}
	else if (values == Circle2) {
		printf("Press the Circle button!\n"); //if the random button is equal to the value of Circle2 it prints press circle
			
	}
	else if (values == Cross3) {
		printf("Press the X button!\n"); //if the random button is equal to the value of Cross3 it prints press cross
	}
	else if(values == SQUARE4) {
		printf("Press the Square button!\n"); //if the random button is equal to the value of Square4 it prints press square
		}
	}
	