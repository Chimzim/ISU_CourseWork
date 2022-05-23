#include <Adafruit_SCD30.h>
#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Arduino.h>


#ifndef SENSINGUNIT_H
#define SENSINGUNIT_H
#define TCAADDR 0x70
#define SCD30ADDR 0x61
#define numSensors 3


class SensingUnit{

    private:
    //Helper to setup the ports each NDIR sensor is connected to on the I2C Device
    void tcaSelect(uint8_t i);
    //Data structure to hold 20 readings for each NDIR Sensor 1-5
    float sensorStorage[100];
    //Port numbers the NDIR CO2 sensors are connects to the I2C device 
    int tcaPortNums[3];
    //NDIR sensor instances
    Adafruit_SCD30 sensors[3];
    //Mearsurement interval for the NDIR Sensors connecto the the I2C device
    int MeasurementInterval = -1;
    //Data Structure that will send 20 readings for each NDIR sensor 1-5 
    float storageSend[100];
    //Flag for collection samples
    bool collectSample = false;

    public:
    //Flag to alter Esp8266 that new data is ready to be sent
    bool dataReady = false;
    //Constructor for the sensing unit class
    SensingUnit(int assignedPorts[]);
    //sets the measurement interval for all the NDIR sensors connected to the I2C device
    bool setMeasurementInterval(uint16_t interval);
    //gets the measurement interval for all the NDIR sensors connected to the I2C device
    uint16_t* getMeasurementInterval();
    //returns the most recent readings in an Array from the all the sensors connected to the I2C device
    float* getCO2Readings();
    //Starts recording data from all NDIR sensors connected to the I2C device
    void beginSampleCollection();
    //Stops recording data from all NDIR sensors connected to the I2C device
    void endSampleCollection();
    //resets all sensors connected to the I2C device
    void resetSensors();
    //gets the collected data to be sent
    float* getCollectedData();
    //init sample collection
    void initSampleCollection();
};
#endif