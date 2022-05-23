/*
 * movement.c
 *
 *  Created on: Jan 28, 2020
 *      Author: ogbondah
 */
#include "movement.h"

double move_forward(oi_t *sensor_data, double distance_mm) {
    double sum = 0;
    oi_setWheels(500,500);
    while(sum < distance_mm)
    {
        oi_update(sensor_data);
        sum += sensor_data -> distance;
        if(sensor_data->bumpLeft == 1 || sensor_data->bumpRight == 1) {
            return sum;
        }

    }
    oi_setWheels(0,0);

    return sum;
}

double move_backwards(oi_t *sensor_data, double distance_mm) {
    double sum = 0;
    oi_setWheels(-200,-200);
    while(sum > distance_mm)
    {
        oi_update(sensor_data);
        sum += sensor_data -> distance;
    }
    oi_setWheels(0,0);

    return sum;
}

void turn_left(oi_t *sensor_data, double degrees) {
    double sum = 0;
    oi_setWheels(200,-200);
    while(sum > degrees) {
        oi_update(sensor_data);
        sum -= sensor_data -> angle;
    }
    oi_setWheels(0,0);
}

void turn_right(oi_t *sensor_data, double degrees) {
        double sum = 0;
        oi_setWheels(-200,200);
        while(sum < degrees) {
            oi_update(sensor_data);
            sum -= sensor_data -> angle;
        }
        oi_setWheels(0,0);
}
