#include <SensingUnit.h>

    //Constructor for the sensing unit class
    SensingUnit::SensingUnit(int assignedPorts[]){
        tcaPortNums[0] = assignedPorts[0];
        tcaPortNums[1] = assignedPorts[1];
        tcaPortNums[2] = assignedPorts[2];
    }
    //private method that helps in setting up the sensors to the I2C device
    void SensingUnit::tcaSelect(uint8_t i) {
        if (i > 7) return;
 
        Wire.beginTransmission(TCAADDR);
        Wire.write(1 << i);
        Wire.endTransmission();  
    }
    //sets the measurement interval for all the NDIR sensors connected to the I2C device
    bool SensingUnit::setMeasurementInterval(uint16_t interval){
        int i = 0;
        for(int i = 0; i < numSensors; i++) {
            tcaSelect(tcaPortNums[i]);
            sensors[i].setMeasurementInterval(interval);
        }
        MeasurementInterval = (int) interval;
        return true;
    }
    //gets the measurement interval for all the NDIR sensors connected to the I2C device
    uint16_t* SensingUnit::getMeasurementInterval(){
        int i = 0;
        uint16_t * toReturn;
        for(int i = 0; i < numSensors; i++) {
            tcaSelect(tcaPortNums[i]);
            toReturn[i] = sensors[i].getMeasurementInterval();
        }
        return toReturn;
    }
    //returns the most recent readings in an Array from the all the sensors connected to the I2C device
    float* SensingUnit::getCO2Readings() {
        float * toReturn;
        int i = 0;
        for(int i = 0; i < numSensors; i++) {
            tcaSelect(tcaPortNums[i]);
            toReturn[i] = sensors[i].CO2;
        }
        return toReturn;
    }
    //Starts recording data from all NDIR sensors connected to the I2C device
    void SensingUnit::beginSampleCollection(){
        if(collectSample == true){
            int limit = 20;
            dataReady = false;
            for(int i = 0; i < limit; i++) {
                float * readings = getCO2Readings();
                if(MeasurementInterval < 0) {
                    break; //Measurement Interval needs to be set
                }
                delay(MeasurementInterval * 1000); //converting 
                sensorStorage[i] = readings[0]; 
                sensorStorage[i+(limit)] = readings[1];
                sensorStorage[i+(limit*2)] = readings[2];
               // sensorStorage[i(limit*3)] = readings[3];
               // sensorStorage[i(limit*4)] = readings[4];
            }
        for(int i = 0; i < 60; i++) {
            storageSend[i] = sensorStorage[i];
        }
          dataReady = true;
        }
    }
    //Stops recording data from all NDIR sensors connected to the I2C device
    void SensingUnit::endSampleCollection(){
        collectSample = false;
    }
    //resets all sensors connected to the I2C device
    void SensingUnit::resetSensors(){
        int i = 0;
        for(i = 0; i < numSensors; i++) {
            tcaSelect(tcaPortNums[i]);
            sensors[i].reset();
        }
    }
    //gets the collected data to be sent
    float* SensingUnit::getCollectedData() {
        return storageSend;
    }
    //setter method for begining sample collection
    void SensingUnit::initSampleCollection() {
        collectSample = true;
    }