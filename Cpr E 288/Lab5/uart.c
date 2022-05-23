/*
*
*   uart.c
*
*
*
*   @author
*   @date
*/

#include "uart.h"
#include <stdint.h>


void uart_init(void){
	//TODO
  //enable clock to GPIO port B
  SYSCTL_RCGCGPIO_R |= ???

  //enable clock to UART1
  SYSCTL_RCGCUART_R |= ???

  //wait for GPIOB and UART1 peripherals to be ready
  while ((SYSCTL_PRGPIO_R & ??? ) {};
  while ((SYSCTL_PRUART_R & ??? ) {};

  //enable alternate functions on port B pins
  GPIO_PORTB_AFSEL_R |= ???

  //enable digital functionality on port B pins
  GPIO_PORTB_DEN_R |= ???

  //enable UART1 Rx and Tx on port B pins
  GPIO_PORTB_PCTL_R = ???

  //calculate baud rate
  uint16_t iBRD = ???; //use equations
  uint16_t fBRD = ???; //use equations

  //turn off UART1 while setting it up
  UART1_CTL_R &= ???

  //set baud rate
  //note: to take effect, there must be a write to LCRH after these assignments
  UART1_IBRD_R = iBRD;
  UART1_FBRD_R = fBRD;

  //set frame, 8 data bits, 1 stop bit, no parity, no FIFO
  //note: this write to LCRH must be after the BRD assignments
  UART1_LCRH_R = ???

  //use system clock as source
  //note from the datasheet UARTCCC register description:
  //field is 0 (system clock) by default on reset
  //Good to be explicit in your code
  UART1_CC_R = 0x0;

  //re-enable UART1 and also enable RX, TX (three bits)
  //note from the datasheet UARTCTL register description:
  //RX and TX are enabled by default on reset
  //Good to be explicit in your code
  UART1_CTL_R = ???

}

void uart_sendChar(char data){
	//TODO
}

char uart_receive(void){
	//TODO
}

void uart_sendStr(const char *data){
	//TODO for reference see lcd_puts from lcd.c file
}
