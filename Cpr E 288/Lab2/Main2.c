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
#include "movement.h"
#include "open_interface.h"



int main (void) {

	oi_t *sensor_data = oi_alloc();
	oi_init(sensor_data);
	double sum = 0;
	while(sum < 2000) {
	sum = move_forward(sensor_data, 1000);
	    if(sensor_data->bumpLeft == 1){
	        move_backwards(sensor_data, -200);
	            turn_right(sensor_data, 80);
	        }
	        if(sensor_data->bumpRight == 1){
	            move_backwards(sensor_data, -200);
	            turn_left(sensor_data, -80);
	        }

	   sum = move_forward(sensor_data, 1000);
	}

	oi_free(sensor_data);



	return 0;
}
