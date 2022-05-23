
/*-----------------------------------------------------------------------------
-         SE 185 Lab 08
-             Developed for 185-Rursch by T.Tran and K.Wang
- Name: CHimzim Ogbondah
-  Section: D
- NetID: ogbondah
- Date: 11.13.2018
-----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
-        Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>
#include <ncurses/ncurses.h>
#include <unistd.h>
#include <stdlib.h>
/*-----------------------------------------------------------------------------
-        Defines
-----------------------------------------------------------------------------*/
/* Mathmatical constants */
#define PI 3.14159
/*  Screen geometry
    Use ROWS and COLUMNS for the screen height and width (set by system)
    MAXIMUMS */
#define COLUMNS 100
#define ROWS 80
/*  Character definitions taken from the ASCII table */
#define AVATAR 'A'
#define WALL '*'
#define EMPTY_SPACE ' '
/* Number of samples taken to form an moving average for the gyroscope data
    Feel free to tweak this. */
#define NUM_SAMPLES 10

/*-----------------------------------------------------------------------------
-        Static Data
-----------------------------------------------------------------------------*/
/* 2D character array which the maze is mapped into */
char MAZE[COLUMNS][ROWS]; //use to update maze

/*-----------------------------------------------------------------------------
-        Prototypes
-----------------------------------------------------------------------------*/
/* POST: Generates a random maze structure into MAZE[][]
    You will want to use the rand() function and maybe use the output %100.
    You will have to use the argument to the command line to determine how
    difficult the maze is (how many maze characters are on the screen). */
void generate_maze(int difficulty);
/* PRE: MAZE[][] has been initialized by generate_maze()
    POST: Draws the maze to the screen */
void draw_maze(void);
/* PRE: 0 < x < COLUMNS, 0 < y < ROWS, 0 < use < 255
    POST: Draws character use to the screen and position x,y */
void draw_character(int x, int y, char use);
/* PRE: -1.0 < mag < 1.0
    POST: Returns tilt magnitude scaled to -1.0 -> 1.0
    You may want to reuse the roll function written in previous labs. */
double calc_roll(double mag);
/*  Updates the buffer with the new_item and returns the computed
    moving average of the updated buffer */
double m_avg(double buffer[], int avg_size, double new_item);

/*-----------------------------------------------------------------------------
-        Implementation
-----------------------------------------------------------------------------*/
/* Main - Run with './ds4rd.exe -t -g -b' piped into STDIN */
void main(int argc, char* argv[]) {
	int skillLevel;
    /*  Setup screen for Ncurses
        The initscr functionis used to setup the Ncurses environment
        The refreash function needs to be called to refresh the outputs
        to the screen */
    initscr();
    refresh();
 if (argc != 2 ) {
    printw("You must enter the difficulty level on the command line.");
    refresh();
    return;
 }
 else {
    /*  Setup screen for Ncurses
    The initscr functionis used to setup the Ncurses environment
    The refreash function needs to be called to refresh the outputs
    to the screen */
    initscr();
    refresh();
    sscanf(argv[1], "%d", &skillLevel);
    while (skillLevel < 0 || skillLevel > 100)
    {
        printw("The difficulty level must be between 0 and 100");
        refresh();
        sscanf(argv[1], "%d", &skillLevel);
    }
    /* WEEK 2 Generate the Maze */
    /* Read gyroscope data and fill the buffer before continuing */
	
	
	
 generate_maze(skillLevel);
 int time, i; 
 double g_z, g_x, g_y, state[NUM_SAMPLES];
 for(i = 0; i < NUM_SAMPLES; i++) {
  scanf("%d, %lf, %lf, %lf", &time, &g_x, &g_y, &g_z);
  state[i] = g_x;
 }
  int playerPosX =40;
  int playerPosY = 0;
  
  
  draw_character(playerPosX, playerPosY, AVATAR);
    /* Event loop */
    do {
	scanf("%d, %lf, %lf, %lf", &time, &g_x, &g_y, &g_z);
  double movingAvg = m_avg(state, NUM_SAMPLES, g_x);
  
        /* Read data, update average */
        /* Is it time to move?  if so, then move avatar */
  if(time % 200 <= 1) {
   draw_character(playerPosX, playerPosY, EMPTY_SPACE);
   if(MAZE[playerPosX+1][playerPosY] != WALL && calc_roll(movingAvg) > 0) { //moves to the right
    draw_character(playerPosX, playerPosY, EMPTY_SPACE); //makes the current postion a space
    playerPosX++; //increments the x by one
    draw_character(playerPosX, playerPosY, AVATAR); //updates new position with the Avatar
   }
   else if(MAZE[playerPosX-1][playerPosY] != WALL && calc_roll(movingAvg) < 0) { //moves to the left
    draw_character(playerPosX, playerPosY, EMPTY_SPACE); //current position becomes space
    playerPosX--; //decrements x by one
    draw_character(playerPosX, playerPosY, AVATAR); //updates the new position with the Avatar
   }
   else if(playerPosX + 1 == WALL && playerPosX - 1 == WALL && playerPosY + 1 == WALL) { //if all spaces around avatar are walls then end the game
    break;
   }
   if(MAZE[playerPosX][playerPosY+1] != WALL) { //if the space below is empty then move avatar down one
    draw_character(playerPosX, playerPosY, EMPTY_SPACE);
    playerPosY++;
    draw_character(playerPosX, playerPosY, AVATAR);
   }
   
   
    } 
}while(playerPosY != 81);
	if(playerPosY != 81) {
	printf("YOU LOSE\n");
	}
	else {
  printf("YOU WIN!\n");
 }
 
    /* This function is used to cleanup the Ncurses environment.
    Without it, the characters printed to the screen will persist
    even after the progam terminates */
    endwin();
 
 
 }
 }

double m_avg(double buffer[], int avg_size, double new_item)
{
    int k;
    for (k = 0; k < avg_size - 1; k++)
        buffer[k] = buffer[k+1];
    buffer[k] = new_item;

    double sum = 0.0;
    for (k = 0; k < avg_size; k++)
        sum += buffer[k];

    return sum/avg_size;
}

/*  PRE: 0 < x < COLUMNS, 0 < y < ROWS, 0 < use < 255
    POST: Draws character use to the screen and position x,y
    THIS CODE FUNCTIONS FOR PLACING THE AVATAR AS PROVIDED.
    DO NOT NEED TO CHANGE THIS FUNCTION. */
void draw_character(int x, int y, char use)
{
    mvaddch(y,x,use);
    refresh();
}
double calc_roll(double mag) { 
 if(mag > 1.0) { // sets the input to 1 if it is greater than 1
  mag = 1;
 }
 else if(mag < -1.0) { //sets the input to negative one if it is less than -1
  return mag = -1;
 }
 else if (mag > 0.0 && mag < 1.0) { //returns rad times 39  for positive numbers
  return mag;
 }
 else if(mag < 0.0 && mag > -1.0) { //returns rad for negative numbers 
  return mag;
 }
}

void generate_maze(int difficulty) {
	int i, j;
	for(i = 0; i <COLUMNS; ++i) { //loops throught the column
		for(j = 0; j < ROWS; ++j) { //loops through every space under the current column until done
			if(rand() % 100 < difficulty) {
				draw_character(j, i, WALL); //if the random number is less than the difficulty then a wall is added (draw to screen with function)
			}
			else {
				draw_character(j, i, EMPTY_SPACE); //otherwise a space is given 
			}
		}
	}
}