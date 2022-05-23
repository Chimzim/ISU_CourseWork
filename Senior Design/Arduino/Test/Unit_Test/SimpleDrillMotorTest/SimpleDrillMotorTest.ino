#include <DrillMotor.h>

//initalize Drill Motor Class on ports ENA1=3 IN1=8 IN2=9
DrillMotor myDrillMotor(3, 8, 9);
void setup() {
  // put your setup code here, to run once:

}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.begin(9600);
  Serial.println("----- Simple Drill Motor Test -----");
  
  Serial.println("Start Drill Motor forward for 10 Seconds");
  myDrillMotor.DrillForward();
  delay(10000);

  Serial.println("Stop the motor with breaking functionality");
  myDrillMotor.DrillBrake();
  delay(500);

  Serial.println("Start the Drill Motor in reverse for 10 seconds");
  myDrillMotor.DrillReverse();
  delay(10000);

  Serial.println("Stop the motor with breaking functionality");
  myDrillMotor.DrillBrake();
  delay(500);

  Serial.println("----- End Simple Drill Motor Test -----");
}
