#include <RobotMovement.h>
    SoftwareSerial mySerial(RX_PIN, TX_PIN);
    KangarooSerial KSerial(mySerial);
    KangarooChannel Drive(KSerial, 'D');
    KangarooChannel Turn(KSerial, 'T');

RobotMovement::RobotMovement() {
    MAX_SPEED = Drive.getMax().value();
    MIN_SPEED = Drive.getMin().value();
    currentSpeed = MAX_SPEED / 2;

    Drive.start(); //Starts the Kangaroo Channel to allow driving functions
    Turn.start();  //Starts the Kangaroo Channel to allowing turning functions

    Drive.pi(0); //Sets the position increment for driving to 0
    Turn.pi(0);  //Sets the position increment for turning to 0
}

void RobotMovement::driveForward(int distance) {
    if(currentSpeed < 0) {
        currentSpeed = currentSpeed * -1;
    }
    Drive.s(currentSpeed).wait();
    delay(distance/currentSpeed);
    Drive.ssi(-currentSpeed);
}

void RobotMovement::driveReverse(int distance) {
    if(currentSpeed > 0) {
        currentSpeed = currentSpeed * -1;
    }
    Drive.s(currentSpeed).wait();
    delay(distance/currentSpeed);
    Drive.ssi(-currentSpeed);
}

void RobotMovement::turn(int angle) {
    Turn.pi(currentPosition + angle);
}

void RobotMovement::turnRight() {
    Turn.pi(Degree_90);
}

void RobotMovement::turnLeft() {
    Turn.pi(-Degree_90);
}

void RobotMovement::setSpeed(long speed) {
    if(abs(speed) > abs(MAX_SPEED)) {
        currentSpeed = MAX_SPEED;
    }
    else if(abs(speed) < abs(MIN_SPEED)) {
        currentSpeed = MIN_SPEED;
    }else {
        currentSpeed = speed;
    }   
}

long RobotMovement::getSpeed() {
    return currentSpeed;
}

void RobotMovement::slowDown(long newSpeed) {
    if(abs(newSpeed) < abs(MIN_SPEED)) {
        Drive.ssi(MIN_SPEED);
    }else {
        Drive.ssi(newSpeed);
    }
}

void RobotMovement::speedUp(long newSpeed) {
    if(abs(newSpeed) > abs(MAX_SPEED)) {
        Drive.ssi(MAX_SPEED);
    }else {
        Drive.ssi(newSpeed);
    }
}

void RobotMovement::stop() {
    slowDown(-(currentSpeed));
}