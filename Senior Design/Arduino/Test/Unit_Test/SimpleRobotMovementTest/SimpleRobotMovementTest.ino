#include <RobotMovement.h>

RobotMovement robot = RobotMovement();
void setup() {
  // put your setup code here, to run once:
}

void loop() {
  // put your main code here, to run repeatedly:
  int ticks = 1000;
  Serial.begin(115200);
  //delay(5000);
  Serial.println("-----Start of simple robot movement Test case-----");

  Serial.println("Drivig forward for 1000 ticks");
  robot.driveForward(ticks);
  delay(1000);

  Serial.println("Drivig backwards for 1000 ticks");
  robot.driveReverse(ticks);
  delay(1000);

  Serial.println("turing right for 500 ticks");
  robot.turnRight();
  delay(1000);


  Serial.println("turing left for 500 ticks");
  robot.turnLeft();
  delay(1000);


  Serial.println("turing right for 45 ticks");
  robot.turn(45);
  delay(1000);

  
  Serial.println("-----End of simple robot movement Test case-----");
}
