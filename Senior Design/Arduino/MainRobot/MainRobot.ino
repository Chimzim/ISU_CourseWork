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
  drill.DrillForward();
  lift.moveLiftDown(100);

  delay(1500);
  drill.DrillBrake();
  delay(1500);

  drill.DrillReverse();
  lift.moveLiftUp(100);
  
  Serial.println("-----End of simple Drilling Aparatus Test case-----");
}
