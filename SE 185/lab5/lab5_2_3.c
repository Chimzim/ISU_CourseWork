/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>

/* This program accepts two integers as user input and swaps their values
 * using two different methods */

void var_swp(int i, int j);

void math_swp(int i, int j);

int main()
{
    int first = 0, second = 0;
    printf("Please input two integers separated by a space: ");

    // scanf("%lf %lf", &first, &second); frist and second were defined as int and so changed %lf to %d in scanf statement 
	scanf("%d %d", &first, &second);

    printf("\n");
    var_swp(first, second);

    printf("\n");
    math_swp(first, second);

    return 0;
}

void var_swp(int i, int j)
{
    printf("Now doing a swap using an extra variable: \n");
    printf("Before Swap: First: %d, Second: %d\n", i, j);

    int k = i;
    i = j;
    j = k;

    printf("After Swap: First: %d, Second: %d\n", i, j);
}

void math_swp(int i, int j)
{
    printf("Now doing a swap using addition and subtraction: \n");
    printf("Before Swap: First: %d, Second: %d\n", i, j);

    i = i + j;
    j = i - j;
    i = i - j;

    printf("After Swap: First: %d, Second: %d\n", i, j);
}

