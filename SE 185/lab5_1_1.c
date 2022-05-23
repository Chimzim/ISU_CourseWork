/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <stdio.h>

/* This program outputs if a integer will divide into another integer with no remainder*/

int main() {
    int i, j;

    printf("Enter an integer: ")
    scanf("%d", &i);

    printf("Enter another integer: );
    scanf("%d", &j)

    if (j % i == 0) {
        printf("%d divides %d\n", i, j);

    else
        pritf("%d does not divide %d\n", i, j);
        printf("%d %% %d is %d\n", j, i, (j % i) );
    }

    return 0;
}