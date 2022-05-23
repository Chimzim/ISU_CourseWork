

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
//#define TRUE 1
//#define FALSE 0
#define Triangle 1
#define Circle 2
#define Cross 3
#define Square 4
/*-----------------------------------------------------------------------------
-	                            Prototypes
-----------------------------------------------------------------------------*/
int start_menu(int CIRCLE);
int buttonPress(int T, int C, int x, int S);
int time_check(int currentTime);
/*-----------------------------------------------------------------------------
-							  Implementation
-----------------------------------------------------------------------------*/
int main() {
	int TRIANGLE, SQUARE, CIRCLE, CROSS;
	int t;
	int randmod = rand() % 5 + 1;
	int currentTime = t;
	int previousTime; 
	int seconds;
	
	printf("Please press the Circle button to begin the game");
	int buttonsToPress[4] = {Triangle, Circle, Cross, Square}; // numbers go 1-4
	scanf("%d, %d, %d, %d, %d", &t, &TRIANGLE, &CIRCLE, &CROSS, &SQUARE);
	while (start_menu(CIRCLE) == 0) {
		seconds = seconds + 1;
	}
	
	while (start_menu(CIRCLE) == 1) {
		int round = 0;
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
		buttonsToPress[randmod];
		printf("Press the %d button!\n", buttonsToPress[randmod]);
		int userInput = buttonPress(TRIANGLE, CIRCLE, CROSS, SQUARE);
			
		if(strcmp(buttonsToPress[randmod] && userInput) == 0  && (time_check(t))) {
			round = round + 1;
		}
		else {
			printf("Sorry you answered incorrectly, you made it %d rounds", round);
			return 0;
			}
				
		
	
	}

}

int start_menu(int CIRCLE) {
	if(CIRCLE == 1) {
		return TRUE;
	}
	else {
		return FALSE;
	}
}
// fix time_check 
int time_check(int currentTime) {
	int i;
	int scoreTime = 2200;
	int prev_Time = 0;{
		if((currentTime + prev_Time ) > scoreTime) {
			return TRUE;
			printf("%d miliseconds left to respond", scoreTime);
			scoreTime = scoreTime - 100;
			prev_Time = currentTime;
		}
	}
}

int buttonPress(int T, int C, int x, int S) {
	if(T == 1) {
		return 1;
	}
	else if(C == 1) {
		return 2;
	}
	else if (x == 1) {
		return 2;
	}
	else if(S == 1) {
		return 3;
	}
	else {
		return FALSE;
	}
}