/*
 * movement.h
 *
 *  Created on: Jan 28, 2020
 *      Author: ogbondah
 */

#ifndef MOVEMENT_H_
#define MOVEMENT_H_


#include "open_interface.h"

double move_forward(oi_t *sensor_data, double distance_mm);
double move_backwards(oi_t *sensor_data, double distance_mm);
void turn_left(oi_t *sensor_data, double degrees);
void turn_right(oi_t *sensor_data, double degrees);

#endif /* MOVEMENT_H_ */
