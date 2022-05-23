/*
 * adc.h
 *
 *  Created on: Feb 25, 2020
 *      Author: ogbondah
 */

#ifndef ADC_H_
#define ADC_H_

#include <inc/tm4c123gh6pm.h>

void adc_init();
int adc_read();
double adc_distanceMM(int value);

#endif /* ADC_H_ */
