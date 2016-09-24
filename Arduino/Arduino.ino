#include <SoftwareSerial.h>
#include "ESP8266.h"
#include <Wire.h>
#include <Adafruit_MLX90614.h>
/*#define SSID "G3 Beat_8470"
#define PASS "hansung2990"*/
#define SSID "jeff'siPhone"
#define PASS "yjs60333"
#define DST_IP "220.67.113.124" //Protector server
#define product_num "CPU1234&"
#define buzer 12
SoftwareSerial esp8266Serial = SoftwareSerial(10, 11);
ESP8266 wifi = ESP8266(esp8266Serial);
Adafruit_MLX90614 mlx = Adafruit_MLX90614();

//  Variables
int pulsePin = 0;                 // Pulse Sensor purple wire connected to analog pin 0
int fadeRate = 0;                 // used to fade LED on with PWM on fadePin

// Volatile Variables, used in the interrupt service routine!
volatile int BPM;                   // int that holds raw Analog in 0. updated every 2mS
volatile int Signal;                // holds the incoming raw data
volatile int IBI = 600;             // int that holds the time interval between beats! Must be seeded! 
volatile boolean Pulse = false;     // "True" when User's live heartbeat is detected. "False" when not a "live beat". 
volatile boolean QS = false;        // becomes true when Arduoino finds a beat.
volatile boolean togle = false;
volatile unsigned long startm;
// Regards Serial OutPut  -- Set This Up to your needs
static boolean serialVisual = true;   // Set to 'false' by Default.  Re-set to 'true' to see Arduino Serial Monitor ASCII Visual Pulse 
char inbyte;
double temp;
void setup() {
  // put your setup code here, to run once:
    Serial.begin(9600);
    pinMode(buzer, OUTPUT); // 약 알림 부저
    pinMode(13, OUTPUT); // 응급상황 체크 led
    esp8266Serial.begin(9600);
    mlx.begin();
    wifi.begin();
    
    Serial.print("restart: ");
    Serial.println(getStatus(wifi.restart()));
    delay(1000);
    
    wifi.setTimeout(360000);
    Serial.print("setWifiMode: ");
    Serial.println(getStatus(wifi.setMode(ESP8266_WIFI_STATION)));
    // restart
    /****************************************/
    /******        WiFi commands       ******/
    /****************************************/
    // joinAP
    Serial.print("joinAP: ");
    Serial.println(getStatus(wifi.joinAP(SSID, PASS)));


    /****************************************/
    /******       TCP/IP commands      ******/
    /****************************************/
    // connect
    Serial.print("connect: ");
    Serial.println(getStatus(wifi.connect(ESP8266_PROTOCOL_TCP, DST_IP, 9001)));
   mysend(product_num);
    interruptSetup(); 
    startm=millis();
}

void loop() {
  temp =   mlx.readObjectTempC();    
  
   /* serialOutput() ;*/       
  if (QS == true){     // A Heartbeat Was Found
                       // BPM and IBI have been Determined
                       // Quantified Self "QS" true when arduino finds a heartbeat
        fadeRate = 255;         // Makes the LED Fade Effect Happen
                                // Set 'fadeRate' Variable to 255 to fade LED with pulse
       serialOutputWhenBeatHappens();   // A Beat Happened, Output that to serial.  */   
        QS = false;                      // reset the Quantified Self flag for next time    
  }
  int totalRead,length;
  char buffer[5];
  if ((length = wifi.available()) > 0) {
      totalRead = wifi.read(buffer, 5);

      if (length > 0) {
        Serial.print("Received ");
        Serial.print(totalRead);
        Serial.print("/");
        Serial.print(length);
        Serial.print(": ");
        Serial.println((char*)buffer);


       
        for(int j=0;j<3;j++)
        {
           tone(buzer,2903,300);
           delay(500);
        } 
       
      }
  }
  if((millis()-startm) >=60000)
  {
  routine();
  startm = millis();
  }
  delay(20);
}
String getStatus(bool status)
{
    if (status)
        return "OK";

    return "KO";
}

String getStatus(ESP8266CommandStatus status)
{
    switch (status) {
    case ESP8266_COMMAND_INVALID:
        return "INVALID";
        break;

    case ESP8266_COMMAND_TIMEOUT:
        return "TIMEOUT";
        break;

    case ESP8266_COMMAND_OK:
        return "OK";
        break;

    case ESP8266_COMMAND_NO_CHANGE:
        return "NO CHANGE";
        break;

    case ESP8266_COMMAND_ERROR:
        return "ERROR";
        break;

    case ESP8266_COMMAND_NO_LINK:
        return "NO LINK";
        break;

    case ESP8266_COMMAND_TOO_LONG:
        return "TOO LONG";
        break;

    case ESP8266_COMMAND_FAIL:
        return "FAIL";
        break;

    default:
        return "UNKNOWN COMMAND STATUS";
        break;
    }
}
void mysend(String str)
{
  int len = str.length();
   esp8266Serial.print("AT+CIPSEND=");
   esp8266Serial.println(len);
   delay(100);
   esp8266Serial.println(str);
   delay(100);
}
void routine()
{
  String msg = "M-"+(String)temp+"-"+(String)BPM+"& \n";  
  Serial.print(msg);
  mysend(msg); 
}/*
void is_danger()
{
    if(temp>40 || temp < 30|| BPM > 130 || BPM < 50)
    {
        String msg="D-" +(String) temp+"-"+(String)BPM+"& \n";
       Serial.print(msg);
        Serial.println(getStatus(wifi.send(msg))); 
    }
}*/

