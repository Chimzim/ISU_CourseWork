#include <HTTPPost.h>
#include <HTTPClient.h>



HTTPPost::HTTPPost() {
    //DO Nothing for now
}

void HTTPPost::init() {
    Serial.begin(115200); 
    delay(10); // We start by connecting to a WiFi network 
    Serial.println(); 
    Serial.println(); Serial.print("Connecting to "); 
    Serial.println(SSID); 
/* Explicitly set the ESP8266 to be a WiFi-client, otherwise, it by default, would try to act as both a client and an access-point and could cause network-issues with your other WiFi-devices on your WiFi-network. */ 
    WiFi.mode(WIFI_STA); 
    WiFi.begin(SSID, Password); 
    while (WiFi.status() != WL_CONNECTED) { 
    delay(500); 
    Serial.print("."); 
    } 
    Serial.println(""); 
    Serial.println("WiFi connected"); 
    Serial.println("IP address: "); 
    Serial.println(WiFi.localIP()); } 
    int value = 0; 
}

bool HTTPPost::sendPostData(float[] data) {
    delay(5000); 
    Serial.print("connecting to "); 
    Serial.println(host); // Use WiFiClient class to create TCP connections 
    WiFiClient client; 
    const int httpPort = 80; 
    if (!client.connect(host, httpPort)) { 
    Serial.println("connection failed"); 
    return false;
    } 
    Serial.print("Requesting URL: "); 
    Serial.println(url); //Post Data 
    String postData = "20 COllected NDIR C02 sensor readings sensor 1-5=" + String(data); 
    String address = Host + url; 
    HTTPClient http; 
    http.begin(address); 
    http.addHeader("Content-Type", "application/x-www-form-urlencoded"); 
    auto httpCode = http.POST(postData); 
    Serial.println(httpCode); //Print HTTP return code 
        String payload = http.getString(); 
    Serial.println(payload); //Print request response payload 
    http.end(); //Close connection Serial.println(); 
    Serial.println("closing connection"); 
    return true;
}