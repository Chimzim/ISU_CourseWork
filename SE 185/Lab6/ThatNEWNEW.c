

/*-----------------------------------------------------------------------------
-					          SE 185 Lab 06
-             Developed for 185-Rursch by T.Tran and K.Wang
-	Name:
- 	Section:
-	NetID:
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
int buttonPress(int T, int C, int x, int S);
int time_check(int currentTime);
int printButton(int values);
/*-----------------------------------------------------------------------------
-							  Implementation
-----------------------------------------------------------------------------*/
int main() {
	int TRIANGLE, SQUARE, CIRCLE, CROSS;
	int Triangle1, Circle2, Cross3, SQUARE4;
	int t, accpetedOrNot;
	int randmod = rand() % 4 + 1;
	int currentTime, prev_Time; 
	int wait = 0;
	int buttonsToPress[4] = {1, 2, 3, 4}; // Triange is 1 Circle is 2 Cross is 3 Square is 4
	printf("Please press the Circle button to begin the game\n");
		
	scanf("%d, %d, %d, %d, %d", &t, &TRIANGLE, &CIRCLE, &CROSS, &SQUARE);
	while (start_menu(CIRCLE) == 0) {
		wait = wait + t;
	}
	
	while (start_menu(CIRCLE) == 1) {
		int game = 1;
		if (game == 1 && CIRCLE == 0) {
			break;
		}
		else {
			return 1;
		}
	}
		srand(time(NULL));
	while (game == 1) {
		int round = 0; //outside
		buttonsToPress[randmod];
		PrintButton( (ButtonstoPress[randmod]) ); //programs prints the button to press to the user 
		if (round == 0)	{
			time_check(0);
		}
			
		if( ( (buttonPress(TRIANGLE, CIRCLE, CROSS, SQUARE)  && time_check(t)) && (round > 0) ) ) {
			round = round + 1;
		}
		else if ( (buttonPress(TRIANGLE, CIRCLE, CROSS, SQUARE) == 1) && (time_check(t) == 0) ){
			printf("Sorry you ran out of time! ");
			printf("You made it %d rounds\n", round);
			break;
		}
		else if( (buttonPress(TRIANGLE, CIRCLE, CROSS, SQUARE) == 0) && (time_check(t) == 1) ) {
			printf("Sorry you answered incorrectly, you made it %d rounds\n", round);
			break;
		}
		else if (round >= 25) {
			printf("Congrats you won the game!\n");
		}
		else {
			break;
		}
	
		
		return 0;
				
		
	
	}

}

int start_menu(int start) {
	if(start == 1) {
		return TRUE;
	}
	else {
		return FALSE;
	}
}
 
int time_check(int currentTime) {
	int scoreTime = 2200;
	int prev_Time = 0;
	int accpetedOrNot;
		if((currentTime + prev_Time ) < scoreTime && (currentTime + prev_Time) > 0) {
			accpetedOrNot = scoreTime - (currentTime + prev_Time);
			printf(" You have %d miliseconds left to answer!", accpetedOrNot);
			return TRUE;
			
		}
		else if(currentTime + prev_Time) >= scoreTime) {
			
			return FALSE;
		}
		else if(currentTime == 0){
			printf("You have %d miliseconds left to answer!", scoreTime);
		}
}
		


int buttonPress(int T, int C, int x, int S) {
	
	if(T == 1) {
		int Triangle1 = 1;
		if (buttonToPress[randmod] == Triangle1) {
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	else if(C == 1) {
		int Circle2 = 2;
		if (buttonToPress[randmod] == Circle2) {
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	else if (x == 1) {
		int Cross3 = 3;
		if (buttonToPress[randmod] == CROSS) {
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	else if(S == 1) {
		int SQUARE4 = 4;

		if (buttonToPress[randmod] == SQUARE4) {
			return TRUE;
		}
		else {
			return FALSE;
		}
	}
	}

int printButton( int values) {
	int Triangle 1;
	int Circle2 =2;
	int Cross3 = 3;
	int SQUARE4 = 4;
	
	if (values == Triangle1) {
		printf("Press the Triangle button!\n");
		}
	else if (values == Circle2) {
		printf("Press the Circle button!\n");
			
	}
	else if (values == Cross3) {
		printf("Press the X button!\n");
	}
	else if(values == SQUARE4) {
		printf("Press the Square button!\n"); 
		}
	}
	