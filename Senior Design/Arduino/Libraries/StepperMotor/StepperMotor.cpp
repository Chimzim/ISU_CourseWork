#include <Adafruit_MotorShield.h>

#include <DrillMotor.h>
#include <StepperMotor.h>

//Constructor for the Stepper Motor class
StepperMotor::StepperMotor(int revSteps, int portNum) {
  this->steps = revSteps;
  this->portNum = portNum;
  shield = Adafruit_MotorShield();
}

void StepperMotor::initStepperMotor() {
  //Configuring Stepper Motor to work with stepper motor controller
  myStepperMotor = shield.getStepper(steps, portNum);
  if (!shield.begin()) {         // create with the default frequency 1.6KHz
    Serial.println("Could not find Motor Shield. Check wiring.");
    while (1);
  }
  myStepperMotor->setSpeed(10); //10 rpm
}

//This Function start the Stepper Motor and moves the lift up
void StepperMotor::moveLiftUp(int steps) {
  while(currentPosition <= MAX_HEIGHT) {
    myStepperMotor->step(steps, FORWARD, DOUBLE);
    currentPosition += 100;
  }
}
  
//This Function start the Stepper Motor and moves the lift down
void StepperMotor::moveLiftDown(int steps){
  while(currentPosition >= 0) {
    myStepperMotor->step(steps, BACKWARD, DOUBLE);
    currentPosition -= 100;
  }
}

//This function starts the stepper motor and move the lift either up or down based on the current position
void StepperMotor::moveLiftToPosition(int Position) {
  while(currentPosition != Position) {
    if(currentPosition > Position) {
      myStepperMotor->step(100, BACKWARD, DOUBLE);
      currentPosition -= 100;
    }else {
      myStepperMotor->step(100, FORWARD, DOUBLE);
      currentPosition += 100;
    }
  }
}

//This function starts the stepper motor and move the lift either up or down based on the current position
void StepperMotor::moveLiftToMiddle() {
  while(currentPosition != MIDDLE_HEIGHT) {
    if(currentPosition > MIDDLE_HEIGHT) {
      myStepperMotor->step(100, BACKWARD, DOUBLE);
      currentPosition -= 100;
    }else {
      myStepperMotor->step(100, FORWARD, DOUBLE);
      currentPosition += 100;
    }
  }
}