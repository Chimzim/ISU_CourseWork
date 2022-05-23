#include <stdio.h>

int main() {
	int i; //variable that will be looped through the for loop
	int idx; //will store where the first C is found
	int counterB = 0; //will count the number of B's after the first C
	char inp_string[20]; //where the users string will be stored
	printf("please enter a string of with no spaces, that is less than 20 characters"); //telling the user to print a string and the constraints with it
	scanf("%c", &inp_string); //where the user will enter the string 
	
	for(i=0; i< 20; ++i) {
		if(inp_string[i] == 'c') { //checks to see if the string is a c
			idx = i; //will store as the index of the first string 
		}
	}
	for(i = idx; i < 20; ++i) {
		if(inp_string[i] == 'b' || inp_string == 'B') { //looks for B's and then 
			counterB = counterB + 1; //keeps track of how many times b has come after the first C
		}
	}
	
	printf("Count of B's after the first c = %d", counterB); //Finally prints out the count of B's
}