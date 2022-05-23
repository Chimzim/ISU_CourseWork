#include <StepperMotor.h>

#include <DrillMotor.h>

StepperMotor lift(200, 2);
DrillMotor drill(3, 8, 9);
void setup() {
  // put your setup code here, to run once:
}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.begin(9600);
  Serial.println("-----Start of simple Drilling Aparatus Test case-----");

  lift.initStepperMotor();
  drill.DrillReverse();
  lift.moveLiftUp(100);

  drill.DrillMotorOff();

  drill.DrillForward();
  lift.moveLiftDown(100);

  drill.DrillMotorOff();

  delay(1000);

  lift.moveLiftToPosition(5000);
  delay(1000);

  lift.moveLiftToMiddle();
  
  Serial.println("-----End of simple Drilling Aparatus Test case-----");
}
