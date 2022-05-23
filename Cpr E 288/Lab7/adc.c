/*
 * adc.c
 *
 *  Created on: Feb 25, 2020
 *      Author: ogbondah
 */
#include "adc.h"
#include <stdint.h>
#include <math.h>
#include <inc/tm4c123gh6pm.h>

void adc_init() {

    // enable clock for ADC
    SYSCTL_RCGCGPIO_R |= 0x02;

    while((SYSCTL_RCGCGPIO_R & 0x2) == 0) { }

    //enable bit 4 to be an alternate
    GPIO_PORTB_AFSEL_R |=  0x10;

    GPIO_PORTB_DIR_R &= 0xEF;

    GPIO_PORTB_AMSEL_R |= 0x10;

    GPIO_PORTB_DEN_R &= 0xEF;



    // enable ADC0 module portB
       SYSCTL_RCGCADC_R = 0x1;

    while((SYSCTL_PRADC_R & 0x1) == 0) { }

    //SYSCTL_RCGCO_R |= 0x00010000;
    // disable ss
    ADC0_ACTSS_R &= 0xD;

    ADC0_EMUX_R &= 0xFF0F;

    //clear SS1 fields
    ADC0_SSMUX1_R = 0x0000;

    // use AN10 portB
    ADC0_SSMUX1_R = 0x0A;

    ADC0_SSCTL1_R = 0x6666;

    // enable
    ADC0_ACTSS_R |= 0x2;
}

//Display sensor data on the lcd screen
int adc_read() {
    int result = 0;
    ADC0_PSSI_R = 0x2;

    while((ADC0_RIS_R & 0x2) == 0x0) { }
    result = ADC0_SSFIFO1_R & 0xFFF;

    ADC0_ISC_R = 0x2;

    return result;
}

double adc_distanceMM(int value) {
    double distance;
    distance = 3000000 * pow(value, -1.585);
    return distance;
}

