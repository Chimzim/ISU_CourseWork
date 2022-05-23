/*-----------------------------------------------------------------------------
-					          Cpr E 381 
-             
-	Name: Chimzim Ogbondah
- 	Section: Cpr E 381
-	NetID: ogbondah
-	
-----------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------
-	                            Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>


/*-----------------------------------------------------------------------------
-	                            Defines
-----------------------------------------------------------------------------*/
#define TRUE 1


/*-----------------------------------------------------------------------------
-	                            Prototypes
-----------------------------------------------------------------------------*/
/* Put your function prototypes here */
int strcmp(const char *str1, const char *str2);


/*-----------------------------------------------------------------------------
-							Implementation
-----------------------------------------------------------------------------*/
int main(void) {
    /* DO NOT MODIFY THESE VARIABLE DECLARATIONS */
    int t;
   

  return 0;
}

/*Fuction checks to see if two string are the same*/
int strcmp(char[] str1, char[] str2) {
	int toMove = 0;
	if(str1.length != str2.length) {
		return 0;
	}else{
		toMove = strcmp(str1, str2);
		if(toMove > 0) {
			return 1; //this means str1 is greater than str2
		}
		else if(toMove < 0) {
			return -1; //this means that str1 is less than str2 
		}
		else {
			return 0; //this means that the strings are equal
}

