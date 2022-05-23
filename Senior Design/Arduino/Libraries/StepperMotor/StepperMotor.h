#include <Adafruit_MotorShield.h>

#ifndef STEPPERMOTOR_H
#define STEPPERMOTOR_H
#define MAX_HEIGHT 6000
#define MIDDLE_HEIGHT 3000
#include <Arduino.h>

class StepperMotor {

  private:
  int steps;
  int portNum;
  Adafruit_MotorShield shield;
  Adafruit_StepperMotor *myStepperMotor;
  //tells where the lift is currently at 0 being the bottom at 6200 at the top
  int currentPosition = 6000;
  
  public:
  StepperMotor(int revSteps, int portNum);
  void  initStepperMotor();
  void moveLiftUp(int steps);
  void moveLiftDown(int steps);
  void moveLiftToPosition(int Position);
  void moveLiftToMiddle();
};
#endif
