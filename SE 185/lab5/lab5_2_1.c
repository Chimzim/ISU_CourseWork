/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>

/* This progam accepts a user input and determines if the integer is
 * an odd or an even number */

int isOdd(int i);

int isEven(int i);

int main()
{
    int input;
    //input == 0; removed one equal sign
	input = 0;
    printf("Please input an integer: ");
    scanf("%d", &input);

   // if(isOdd(input)=1) added a second equal sign 
    if(isOdd(input)==1) {
        printf("%d is an odd number!\n", input);
    }
    //if(isEven(input)=1) added a second equal sign 
	else if(isEven(input)==1){
        printf("%d is an even number!\n", input);
    }

    return 0;
}

int isOdd(int i) {
    if(i % 2)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

int isEven(int i) {
    if(!(i % 2))
    {
        return 1;
    }
    else
    {
        return 0;
    }
}
