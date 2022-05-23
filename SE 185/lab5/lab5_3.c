/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h> //included the this library so it understands rand and srand
#include <time.h>
/* Prototypes */ // added a star to complete the comment 
char AskToPlay(int playedBefore); //changed the name to line up with the function 
int SelectRandNum(); //defined the function prototype above 
void RunGame (int computerNum);

int main(){
    char prompt = '-';
    int played = 0, computerGuess = 0;

    prompt = AskToPlay(played);
    played = 1;

    while(prompt == 'y') {     /* This line does not contain an error */
    
        computerGuess = SelectRandNum(); //deleted one equal sign so it isn't saying computer guesses is slecetrandNum
        RunGame(computerGuess);
        prompt = AskToPlay(played);
    }
    printf("\n\nThank you for playing.\n"); //added a semicolon 
    return 0;
}


char AskToPlay(int playedBefore){
    char yesNo;
    if (playedBefore == 0) {
        printf("Do you want to play a game?\n  ->");
        scanf(" %c", &yesNo); //added & infornt of the variable 
    }
    else{
		printf("Do you want to play again?\n  ->");
        scanf(" %c", &yesNo); 
    }
    return yesNo;
}

int SelectRandNum() {
    // int c*mpGuess = 0; changed the * to an o 
	int compGuess = 0;
    srand((int)time(0));
    compGuess = ((rand() % 100) + 1);
    return compGuess;
}


void RunGame(int computerNum){
    int number = 0, correct = 0;


    printf("\nYou are guessing a number.  The options are 1 through 100.\n\n");
    printf("What is your guess on what number I will select?\n  ->");
    scanf("%d", &number);

    while ((number <1) || (number >100)) {    /* This line does not contain an error */
        printf("\nYour number is not within the correct range of numbers.  Guess again\n  ->");
        scanf("%d", &number);
    }


    while (correct == 0){    /* This line does not contain an error */
    

        if (number == computerNum){
            printf("\nYou guessed the number correctly!\n");
            printf("The number was %c\n\n", computerNum);
            correct = 1;
        }
        else if (number < computerNum) { //removed a semicolon because those don't belong in these statements
            printf("\nYou guessed too low.  Enter another guess.\n  ->");
            scanf("%d", &number);
        }
        else {
            printf("\nYou guessed too high.  Enter another guess.\n  ->");
            scanf("%d", &number);
        }
    }
}



