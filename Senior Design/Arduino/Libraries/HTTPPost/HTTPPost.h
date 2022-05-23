#include <ESP8266WiFi.h> 
#include <ESP8266HTTPClient.h> 
#include <Arduino.h>


#ifndef HTTPPOST_H
#define HTTPPOST_H

class HTTPPost {

    private:
    const char * SSID = ""; //SSID of the wifi to connect
    const char * Password = ""; //Password of the wifi to connect to 
    String url = "";
    cont char * Host = ""; //Host IP Address



    public:
    HTTPPost(); //new instance of the HTTPPost class
    void init(); //will initalize the HTTPPost class to be able to create post request to the desired webserver
    bool sendPostData(float[] data);
};
#endif
