#include <SoftwareSerial.h>
#include <Kangaroo.h>
#include <Arduino.h>

#ifndef ROBOTMOVEMENT_H
#define ROBOTMOVEMENT_H
#define TX_PIN 11      //connects to the Kangaroo S1 input 
#define RX_PIN 10      //connects to the Kangaroo S2 input 
#define Degree_90 500

class RobotMovement {

    private:
    long currentSpeed = 0;
    long MAX_SPEED = 0;
    long MIN_SPEED = 0;
    long currentPosition = 0; //used for tracking which way the robot is facing



    public:
    RobotMovement();
    void driveForward(int distance); //this function drives the robot forward given the distance
    void driveReverse(int distance); //this function drives the robot in reverse given the distance
    void turn(int angle); //this function turns the robot based of the given angle
    void turnRight(); //this function turns the robot to the right 90 degrees
    void turnLeft();  //this function turns the robot to the left 90 degress
    void setSpeed(long speed); //this function sets the speed of the robot
    long getSpeed(); //this returns the current speed the robot is or will be driving at 
    void slowDown(long newSpeed);
    void speedUp(long newSpeed);
    void stop();


};
#endif