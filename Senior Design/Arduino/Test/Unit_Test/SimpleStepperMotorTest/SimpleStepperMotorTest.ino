#include <StepperMotor.h>

//initalize Drill Motor Class on ports ENA1=3 IN1=8 IN2=9
StepperMotor myStepperMotor(200, 2);
void setup() {
  // put your setup code here, to run once:
  myStepperMotor.initStepperMotor();
}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.begin(9600);
  Serial.println("----- Simple Stepper Motor Test -----");
  
  Serial.println("Move lift down via stepper motor");
  myStepperMotor.moveLiftDown(100);

  Serial.println("Move lift up via stepper motor");
  myStepperMotor.moveLiftUp(100);

  Serial.println("----- End Simple Stepper Motor Test -----");
}
