// Lab 10 DS4Talker Skeleton Code

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#define MAXWORDS 100
#define WORDLEN 11
#define DEBUG 1   // set to 0 to disable debug output

// reads words from the file
// into wl and trims the whitespace off of the end
// DO NOT MODIFY THIS FUNCTION
int readWords(char* wl[MAXWORDS], char* filename); 

//modifies s to trim white space off the right side
// DO NOT MODIFY THIS FUNCTION
void trimws(char* s);

void draw_character(int x, int y, char use)
{
    mvaddch(y,x,use);
    refresh();
}

int main(int argc, char* argv[]) {
	char* wordlist[MAXWORDS];
	int wordCount;
	int i,j,x;
	int prevX, prevY;
	wordCount = readWords(wordlist, argv[1]);

	if (DEBUG) {
		printf("Read %d words from %s \n",wordCount, argv[1]);
		for (i = 0; i< wordCount; i++) {
			printf("%s,", wordlist[i]);

			}
		printf("\n");
		}

// most of your code goes here. Don't forget to include the ncurses library 
	for(i= 0; i < 75; ++i) {
		for(j = 0; j < 15; ++j) {
			if(x < 100) {
				mvprintw(i, j, "%15s" wordlist[x]);
			}
			x++;
		}
	}
	int joystick, SQUARE, CROSS, TRIANGLE;
	int currentRows = MAXWORDS / 5;
	int presentY=0, presentX=0;
	do {
	scanf("%d, %lf, %lf, %lf, %d, %d, %d, %d, %d, %d, %d, %d", time, g_x, g_y, g_z, button_T, button_C, button_X, button_S, l_joy_x, l_joy_y, r_joy_x, r_joy_y);
	
	draw_character(prevX, prevY, ' ');
	
	if(joystick_down && currentRows - 1) {
		presentY++;
	}
	else if(joystick_up && presentY > 0) {
		presentY--;
	}
	
	if(joystick_right && presentX =< 60) {
		presentX = presentX + 15;
	}
	else if(joystick_left && presentX > 0) {
		presentX = presentX - 15;
	}
	
	prevX = presentX;
	prevY = presentY;
	
	draw_character(presentX-2, presentY, '*');
	int word_length;
	int pencil[100];
	int c = 0;
	int playerIdx;
	int sentenceLength;
	if(TRIANGLE == 1) {
		playerIdx = (presentY*5)+ (presentX/15);
		word_length = strlen(wordlist[playerIdx]+1);
		pencil[c] = word_length;
		c++;
		sentenceLength = sentenceLength + word_length;
		mvprintw(currentRows+2, sentenceLength, " %s", wordlist[playerIdx]);
	}
	else if(SQUARE == 1) {
		playerIdx = (presentY*5)+ (presentX/15);
		word_length = strlen(wordlist[playerIdx]+1);
		pencil[c] = word_length;
		c++;
		sentenceLength = sentenceLength + word_length;
		mvprintw(currentRows+2, sentenceLength, "%s", wordlist[playerIdx]);
	}
	else if(CROSS == 1) {
		if(c >= 0) {
		mvprintw(currentRows+2, sentenceLength - pencil[c], "        ");
		c--;
		}
	else if(l_JX == 1) {
		mvprintw(currentRows+2, sentenceLength - sentenceLength, "%c", \0);
	}
	//for the first letter to be capitilized it would just be pencil[0] toUpper? and then strcpy and add to the sentence ?
	} while(1);	
	
		
			
			
			
    return 0;
	}

// DO NOT MODIFY THIS FUNCTION
void trimws(char* s) {
        int len = strlen(s) ;
        int x;
        if (len ==0) return;
        x = len-1;
        while (isspace(s[x]) && (x>=0)) {
                s[x] = '\0';
                x--;
                }
        }


// DO NOT MODIFY THIS FUNCTION
int readWords(char* wl[MAXWORDS], char* filename) {
	int numread =0;
	char line[WORDLEN];
	char *p;
	FILE* fp = fopen(filename,"r");
	while (!feof(fp)) {
		p  =fgets(line, WORDLEN, fp);
		if (!feof(fp) && p !=NULL) {
			trimws(line);
			wl[numread] = (char *)  malloc(strlen(line)+1);
			strcpy(wl[numread], line);
			numread++;
			}
		} 
	fclose(fp);
	return numread;	
	}
	