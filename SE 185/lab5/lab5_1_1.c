/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>

/* This program outputs if a integer will divide into another integer with no remainder*/

int main() {
    int i, j;

    printf("Enter an integer: "); //there was a missing semicolon
    scanf("%d", &i);

    printf("Enter another integer: "); //added a closing " mark
    scanf("%d", &j); //there was a missing semi colon

    if (j % i == 0) {
        printf("%d divides %d\n", i, j);
	} //there was a missing end bracket

    else { //missing opening bracket 
        printf("%d does not divide %d\n", i, j); //added a n to correctly spell printf
        printf("%d %% %d is %d\n", j, i, (j % i) );
    }

    return 0;
}