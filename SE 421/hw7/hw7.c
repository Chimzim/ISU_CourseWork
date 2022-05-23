#include <stdio.h>
#include <stdlib.h>
#include <bool.h>
int main(bool c1, bool c2, bool c3) {
 int a1, a2, x, d;
 int *p1,*p2,**q1,**q2;
 a1 = 3;
 a2 = 5;
 p1 = &a2;
 p2 = &a1;
 q2 = &p1;
 	if(c1){
 	  *p1 = 5;
 	} else{
    *p2 = 7;
   }
q1 = &p2;
  if(c2){
    x = *p1;
    if(c3){
      a2 = **q1;
    } else {
       d = x - **q2;
    }
  } else {
     d = d + 1;
  }
	z = x/d;
//  printf(a1+z);
}
