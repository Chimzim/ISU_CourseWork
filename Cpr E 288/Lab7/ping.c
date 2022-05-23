/*
 * ping.c
 *
 *  Created on: Mar 10, 2020
 *      Author: ogbondah
 */

#include "ping.h"
#include "timer.h"
#include <stdint.h>
#include <math.h>
#include <inc/tm4c123gh6pm.h>

//initialize the timer
void ping_init() {
    //to help with interrupts
    ping = 0;
    //to keep track of time
    start = 0;
    stop = 0;

    //GPIO set up
    SYSCTL_RCGCGPIO_R |= 0x02;

     while((SYSCTL_RCGCGPIO_R & 0x2) == 0) { }

     //enable bit 3 to be an alternate
     GPIO_PORTB_AFSEL_R |=  0x8;

     GPIO_PORTB_DIR_R &= 0xF7;

    // GPIO_PORTB_AMSEL_R |= 0x08;

     GPIO_PORTB_DEN_R |= 0x8;
     //sets the alternate function to a timer
     GPIO_PORTB_PCTL_R = (GPIO_PORTB_PCTL_R & 0x0FFF) | 0x7000;

     //PING set up
     SYSCTL_RCGCTIMER_R |= 0x08;

     while((SYSCTL_PRTIMER_R & 0x8) == 0) { }

     //disable the timer
     TIMER3_CTL_R &= 0xEFF;

     TIMER3_CFG_R &= 0x00000000;

     TIMER3_CFG_R |= 0x00000004;
     //sets it to a down counter

     TIMER3_TBMR_R &= 0xEF;
     TIMER3_TBMR_R |= 0x7;
     //sets the timer to a negative edge
     TIMER3_CTL_R |= 0xC00;
     //sets count to the maximum number
     TIMER3_TBPR_R &= 0xFF;
     //loads the value of the timer
     TIMER3_TBILR_R &= 0xFFFF;
     //clears interrupts
     TIMER3_ICR_R |= 0x400;
     //enables interrupts
     TIMER3_IMR_R |= 0x400;

     //sets the priority
     NVIC_PRI9_R &= 0x1F;
     NVIC_PRI9_R |= 0x20;

     //enables the intterupt
     NVIC_EN1_R |= 0x10;

     IntRegister(INT_TIMER3B, handler);
     IntMasterEnable();
     //Re enable the timer
     TIMER3_CTL_R |= 0x100;

}

//activate the ping sensor
void ping_trigger() {
    //disable the timer
    TIMER3_CTL_R &= 0xEFF;
    //enable alternate function PB3
    GPIO_PORTB_AFSEL_R &= 0x7;
    //sets PB3 to an output
    GPIO_PORTB_DIR_R |= 0x08;
    //sets PB3 to 1
    GPIO_PORTB_DATA_R |= 0x08;
    //wait 6 seconds
    timer_waitMicros(6);
    //sets PB3 back to 0
    GPIO_PORTB_DATA_R &= 0xF7;
    //set PB3 back to an input
    GPIO_PORTB_DIR_R &= 0xF7;

    TIMER3_ICR_R |= 0x400;
    ping_flag = 0;

    //enables alternate function for pin 3
    GPIO_PORTB_AFSEL_R |= 0x8;
    //enable the timer again
    TIMER3_CTL_R |= 0x100;

}

//calculate the distance
float ping_getDistance() {
    timer_waitMillis(300);
    ping_trigger();
    while(flag != 2) {}

    float cycles = start - stop;

    if(cycles < 0) {
        float startZero = start;
        float stopAtTop = 0xFFFFFF - stop;
        cycles = startZero + stopAtTop;
    }

    return cycles;
}

void handler() {
    int32_t myData = 0;

    if(TIMER3_MIS_R & 0x400) {
        TIMER3_ICR_R |= ~(0xBFF);

        myData = TIMER3_TBR_R & 0xFFFFFF;

        if(flag == 0) {
            start = myData;
            ping_flag = 1;
        }
        else if(ping == 1) {
            stop = myData;
            flag = 2;
            TIMER3_CTL_R &= 0xEFF;
        }
        else {
            start = myData;
            stop = 0;
            flag = 1;
        }
    }

}
