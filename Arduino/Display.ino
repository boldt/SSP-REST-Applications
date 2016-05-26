/*
 * Quelle: http://www.arduino.cc/en/Tutorial/LiquidCrystal
 */

#include <LiquidCrystal.h>

String inData;
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

void setup() {

  Serial.begin(9600);
  
  lcd.begin(16, 2);
  lcd.print("Pflanze:");
}

void loop() {
    while (Serial.available() > 0) {
        char recieved = Serial.read();
        //Serial.print("R: ");
        //Serial.print(recieved);
        inData += recieved; 
        if (recieved == '\n') {
            Serial.println();          
            inData.trim();
            
            lcd.clear();
            
            lcd.setCursor(0, 0);
            lcd.print("Pflanze:");
            
            lcd.setCursor(0, 1);
            lcd.print(inData);  
            
            inData = ""; // Clear recieved buffer
        }
    }
}
