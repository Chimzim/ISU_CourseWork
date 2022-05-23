/*-----------------------------------------------------------------------------
-                             SE 185 Lab 05
-             Developed for 185-Rursch by T.Tran and K.Wang
-----------------------------------------------------------------------------*/
#include <time.h>
#include <stdio.h> //added in so scanf() could be used
/* This is a simple program that takes a user inputs and prints out a message based on that input */

void hoo();
void print_face(int opt); // defined the function above 
int main() {
    // srand(time(NULL)); causing for an error and not being used in code
    int option = 0;

    printf("Enter 1 for happy, 2 for sad, 3 for neutral, any other integer for random: ");
    scanf("%d", &option);

    if (option < 1 || option > 3) {

       option = rand() % 4;
    }
    print_face(option);

    return 0;
}

void print_face(int opt) {
    if (opt == 1) {
        printf("Have a nice day! :) \n");
    }
    else if (opt == 2) {
        printf(":(\n");
    }
    else if (opt ==3) {
        printf("meh :\\ \n");
    }
    else {
        hoo();
    }
}

void hoo() {
    printf(" *___*\n {O,O}\n/)___)\n_\"__\"_\n");
}