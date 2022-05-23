#include <SensingUnit.h>
int sensingPorts[3] = {0, 1, 2};
SensingUnit robotSensingUnit(sensingPorts);
void setup() {
  // put your setup code here, to run once:

}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.begin(9600);
  Serial.println("-----Start of Simple Robot Sensing Unit Test case-----");

  uint16_t * currentMeasurement = robotSensingUnit.getMeasurementInterval();
  Serial.print("Current Robot Sensing Unit Measurement Interval: ");
  Serial.println(currentMeasurement[0]);

  uint16_t newInterval = 8;
  robotSensingUnit.setMeasurementInterval(newInterval);
  Serial.println("Setting the Robot Sensing Unit Measurement Interval to 8");
  uint16_t * newMeasurement = robotSensingUnit.getMeasurementInterval();
  Serial.print("New Robot Sensing Unit Measurement Interval: ");
  Serial.println(newMeasurement[0]);

  Serial.println("Current Robot Sensing Unit CO2 Measurements: ");
  float * currentReadings = robotSensingUnit.getCO2Readings();
  int i = 0;
  for(i = 0; i < 3; i++){
    Serial.print("CO2 Reading from Sensor on Multiplexer Port ");
    Serial.print(i);
    Serial.print("");
    Serial.println(currentReadings[i]);
  }
}
