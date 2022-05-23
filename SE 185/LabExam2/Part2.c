#include <stdio.h>
int lastB(char BBB[]);

int main() {
	int counterB;
	char inp_string[20]; //where the users string will be stored
	printf("please enter a string of with no spaces, that is less than 20 characters\n"); //telling the user to print a string and the constraints with it
	scanf("%s", inp_string); //where the user will enter the string 
	
	
	printf("Count of B's after the first c = %d", counterB); //doesn't do anything in this part 
	printf("Position = %d", lastB(inp_string)); //prints the position of the string 
	
	
}

int lastB(char BBB[]) {
	int i; //variable that will be looped through the for loop
	int idx; //will store where the first C is found
	int lastIdxB; // will update to the last b in the string
	int counterB = 0; //will count the number of B's after the first C
	
	for(i=0; i< 20; ++i) { //loops 20 times 
		if(BBB[i] == 'c') {
			idx = i; //stores the index of the first C
		}
	}
	for(i = idx; i < 20; ++i) { //loops through everything after the first string is found
		if(BBB[i] == 'b' || BBB[i] == 'B') {
			counterB = counterB + 1; //will keep track of how many B's after the first C
			lastIdxB = i; //keep updating until the last string, finally storing the i or position of the last B //
		}
	}
	return lastIdxB; // will return the value of B
}