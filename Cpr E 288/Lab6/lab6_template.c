/**
 * lab5_template.c
 *
 * Template file for CprE 288 Lab 5
 *
 * @author Diane Rover, 2/15/2020
 *
 */

#include "Timer.h"
#include "lcd.h"
#include "cyBot_Scan.h"  // For scan sensors
#include "uart.h"
#include "adc.h"

// Uncomment or add any include directives that are needed
// #include "open_interface.h"
// #include "movement.h"
// #include "button.h"


#warning "Possible unimplemented functions"
#define REPLACEME 0


int main(void) {
	timer_init(); // Must be called before lcd_init(), which uses timer functions
	lcd_init();
	adc_init();
  // cyBOT_init_Scan();

	// YOUR CODE HERE
//	int i = 0;
//	char str[21];
//	while(1)
//	{
//	   str[0] = '\0';
//	   for(i = 0; i < 20; i++) {
//           char letter = uart_receive();
//	       if(letter != '\r') {
//	           str[i] = letter;
//	       }
//	       else {
//	           str[i] = '\n';
//	           str[i+1] = '\0';
//	           break;
//	       }
//	   }
//	   lcd_printf(str);
//	   uart_sendStr(str);
//      // YOUR CODE HERE
//
    //lcd_printf("test");
while(1) {

	timer_waitMillis(3000);
	int number = adc_read();
	double distance = adc_distanceMM(number);
//    int distance = adc_distanceMM(number);
    lcd_printf("%lf", distance);

	}



	}
