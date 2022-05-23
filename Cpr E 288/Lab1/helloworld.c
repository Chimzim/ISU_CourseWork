/// Simple 'Hello, world' program
/**
 * This program prints "Hello, world" to the LCD screen
 * @author Chad Nelson
 * @date 06/26/2012
 *
 * updated: phjones 9/3/2019
 * Description: Added timer_init call, and including Timer.h
 */

#include "Timer.h"
#include "lcd.h"


int main (void) {

	timer_init(); // Initialize Timer, needed before any LCD screen functions can be called
	              // and enables time functions (e.g. timer_waitMillis)

	lcd_init();   // Initialize the LCD screen.  This also clears the screen.

	char str[21] = {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
	char str1[] = "lab Section: 1";
	int j = 0, i= 0, c =0, loop = 1, screenLen = 0;

	while(loop == 1) {
	    for(i = 0; i < 20; i++) {
	        if(i+1 < 20) {
	            str[i] = str[i+1];
	        }
	    }
	    str[19] = str1[j];
	    lcd_printf("%s", str);
	    j++;
	    timer_waitMillis(300);

	    while(screenLen < 20 && j == strlen(str1)) {
	        if(screenLen == 19) {
	            j = 0;
	            screenLen = 0;
	        }
	        for(c = 0; c < 20; c++) {
	            if(c+1 < 20) {
	                str[c] = str[c+1];
	            }
	        }
	        str[19] = ' ';
	        lcd_printf("%s", str);
	        screenLen += 1;
	        timer_waitMillis(300);
	    }
	}

	// Print "Hello, world" on the LCD
	//lcd_printf("Hello, world");

	// lcd_puts("Hello, world"); // Replace lcd_printf with lcd_puts
        // step through in debug mode and explain to TA how it works
    
	// NOTE: It is recommended that you use only lcd_init(), lcd_printf(), lcd_putc, and lcd_puts from lcd.h.
       // NOTE: For time functions, see Timer.h

	return 0;
}
