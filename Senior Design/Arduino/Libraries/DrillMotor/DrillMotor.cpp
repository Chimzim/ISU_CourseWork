#include <DrillMotor.h>
#include <Arduino.h>
#include <Adafruit_MotorShield.h>

//Constructor for the Drill Motor class
DrillMotor::DrillMotor(int ENA1, int IN1, int IN2) {
  this->ENA1 = ENA1;
  this->IN1 = IN1;
  this->IN2 = IN2;
  this->HIGH1 = 1;
  this->LOW1 = 0;
  //Configuring DrillMotor to work with DC motor controller
  pinMode(ENA1, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
}

//This Function start the Drill Motor forward at full speed
void DrillMotor::DrillForward() {
  digitalWrite(IN1, HIGH1);
  digitalWrite(IN2, LOW1);
  analogWrite(ENA1, 100);
  direction = 1;
}
  
//This function turns off the drill Motor by slowing the 
void DrillMotor::DrillMotorOff() {
  digitalWrite(IN1, HIGH1);
  digitalWrite(IN2, LOW1);
  analogWrite(ENA1, 75);
  delay(1000);
  analogWrite(ENA1, 50);
  delay(1000);
  analogWrite(ENA1, 25);
  delay(1000);
  analogWrite(ENA1, 0);
  delay(1000);
}
//This Function start the Drill Motor reverse at full speed
void DrillMotor::DrillReverse(){
  digitalWrite(IN1, LOW1);
  digitalWrite(IN2, HIGH1);
  analogWrite(ENA1, 100); 
  direction = 0;
}

//This Function slows down the Drill motor at full breaking power
void DrillMotor::DrillBrake() {
  digitalWrite(IN1, LOW1);
  digitalWrite(IN2, LOW1);
  analogWrite(ENA1, 100);
}

//This function is used to test the drill moving into the ground
void DrillMotor::testDrillAtSpeed(int speed) {
  if(speed < 0) {
    digitalWrite(IN1, LOW1);
    digitalWrite(IN2, HIGH1);
    analogWrite(ENA1, speed*-1); 
    currentDrillingSpeed = speed * -1;
    direction = 0;
  }else {
    digitalWrite(IN1, HIGH1);
    digitalWrite(IN2, LOW1);
    analogWrite(ENA1, speed);
    currentDrillingSpeed = speed;
    direction = 1;
  }
}

//this function is used to test the drill slowing down based on the currentSpeed
void DrillMotor::testDrillMotorOff() {
  while(currentDrillingSpeed >= 0) {
    if(direction == 1) {
      digitalWrite(IN1, LOW1);
      digitalWrite(IN2, HIGH1);
      analogWrite(ENA1, currentDrillingSpeed-10); 
      currentDrillingSpeed = currentDrillingSpeed - 10;
      if(currentDrillingSpeed < 0) {
        analogWrite(ENA1, 0); 
        currentDrillingSpeed = 0;
      }
    }else {
      digitalWrite(IN1, HIGH1);
      digitalWrite(IN2, LOW1);
      analogWrite(ENA1, currentDrillingSpeed-10);
      currentDrillingSpeed = currentDrillingSpeed - 10;
      if(currentDrillingSpeed < 0) {
        analogWrite(ENA1, 0);
        currentDrillingSpeed = 0; 
      }
    }
  }
}
