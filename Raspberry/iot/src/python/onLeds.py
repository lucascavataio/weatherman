from gpiozero import LED
import time
import datetime
from time import sleep
import sys

tempChecked = sys.argv[1];

humChecked = sys.argv[2];

RED_LED = LED(2)
GREEN_LED = LED(3)
YELLOW_LED = LED(4)
BLUE_LED = LED(17)

SLEEP = 3

def low_leds():
    RED_LED.off()
    GREEN_LED.off()
    YELLOW_LED.off()
    BLUE_LED.off()
        
def show_state(tempChecked, humChecked):
    tempOk = tempChecked
    humOk = humChecked
    if tempOk is "1":
        RED_LED.off()
        GREEN_LED.on()
    else:
        RED_LED.on()
        GREEN_LED.off() 
    if humOk is "1":
        YELLOW_LED.off() 
        BLUE_LED.on()
    else:
        YELLOW_LED.on()
        BLUE_LED.off()
        

show_state(tempChecked, humChecked)
sleep(SLEEP)
low_leds()