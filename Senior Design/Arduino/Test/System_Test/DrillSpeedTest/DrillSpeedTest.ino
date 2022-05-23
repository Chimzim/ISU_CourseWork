#include <StepperMotor.h>

#include <DrillMotor.h>

StepperMotor lift(200, 2);
DrillMotor drill(3, 8, 9);
void setup() {
  // put your setup code here, to run once:
}

void loop() {
  // put your main code here, to run repeatedly:
  delay(2000);
  Serial.begin(9600);
  Serial.println("-----Start of simple Drilling Aparatus Speed Test case-----");

  lift.initStepperMotor();
  drill.testDrillAtSpeed(40);
  lift.moveLiftDown(100);
  Serial.println("-----Turning Drill Off-----");
  drill.DrillBrake();
  delay(4500);

  Serial.println("-----Turning Drill in reverse and moving drill up-----");
  drill.testDrillAtSpeed(-40);
  lift.moveLiftUp(100);
  drill.DrillBrake();
  delay(8000);

/*  Serial.println("-----Testing 10 precent speed-----");
  lift.initStepperMotor();
  drill.testDrillAtSpeed(10);
  lift.moveLiftDown(100);
  Serial.println("-----Turning Drill Off-----");
  drill.testDrillMotorOff();
  delay(4500);

  Serial.println("-----Turning Drill in reverse and moving drill up-----");
  drill.testDrillAtSpeed(-10);
  lift.moveLiftUp(100);
  Serial.println("-----Turning Drill Off-----");
  drill.testDrillMotorOff();
  delay(8000);

  Serial.println("-----Testing 5 precent speed-----");
  lift.initStepperMotor();
  drill.testDrillAtSpeed(5);
  lift.moveLiftDown(100);
  Serial.println("-----Turning Drill Off-----");
  drill.testDrillMotorOff();
  delay(4500);

  Serial.println("-----Turning Drill in reverse and moving drill up-----");
  drill.testDrillAtSpeed(-5);
  lift.moveLiftUp(100);
  drill.testDrillMotorOff();
  delay(8000);
  
  Serial.println("-----End of simple Drilling Aparatus Speed Test case-----"); */
}
