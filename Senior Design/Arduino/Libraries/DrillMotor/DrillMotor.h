#ifndef DRILLMOTOR_H
#define DRILLMOTOR_H
#define DRILLSPEED 0
#include <Arduino.h>
#include <Adafruit_MotorShield.h>

class DrillMotor {

  private:
  int HIGH1;
  int LOW1;
  int ENA1;
  int IN1;
  int IN2;
  int currentDrillingSpeed = 0;
  int direction = 0; //this function is used to determine if the drill is moving forward=1 or backwards=0 

  public:
  DrillMotor(int ENA1, int IN1, int IN2);
  void DrillForward();
  void DrillReverse();
  void DrillMotorOff();
  void DrillBrake();
  void testDrillAtSpeed(int speed);
  void testDrillMotorOff();
};
#endif
