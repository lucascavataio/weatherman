from gpiozero import LED
import time
import datetime
from time import sleep
import sys

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

def on_leds():
    RED_LED.on()
    GREEN_LED.on()
    YELLOW_LED.on()
    BLUE_LED.on()
        
def show_state():
    
    green_test()
    
    show_succsess()
        
    red_test()
    
    show_succsess()

    yellow_test()
    
    show_succsess()

    blue_test()
    
    show_succsess()
    
    second_test()
    
    on_leds()

def show_succsess():
    GREEN_LED.on()
    sleep(0.1)
    GREEN_LED.off()
    sleep(0.1)
    GREEN_LED.on()
    sleep(0.1)
    GREEN_LED.off()
    sleep(0.1)
    GREEN_LED.on()
    sleep(0.1)
    GREEN_LED.off()
    sleep(0.1)
    GREEN_LED.on()
    sleep(0.5)
    GREEN_LED.off()
    sleep(1)

def green_test():
    GREEN_LED.on()
    sleep(0.5)
    GREEN_LED.off()
    sleep(0.5)
    GREEN_LED.on()
    sleep(0.5)
    GREEN_LED.off()
    sleep(0.5)
    GREEN_LED.on()
    sleep(2)
    GREEN_LED.off()
    sleep(1)

def red_test():
    RED_LED.on()
    sleep(0.5)
    RED_LED.off()
    sleep(0.5)
    RED_LED.on()
    sleep(0.5)
    RED_LED.off()
    sleep(0.5)
    RED_LED.on()
    sleep(2)
    RED_LED.off()
    sleep(1)
    
def yellow_test():
    YELLOW_LED.on()
    sleep(0.5)
    YELLOW_LED.off()
    sleep(0.5)
    YELLOW_LED.on()
    sleep(0.5)
    YELLOW_LED.off()
    sleep(0.5)
    YELLOW_LED.on()
    sleep(2)
    YELLOW_LED.off()
    sleep(1)

def blue_test():
    BLUE_LED.on()
    sleep(0.5)
    BLUE_LED.off()
    sleep(0.5)
    BLUE_LED.on()
    sleep(0.5)
    BLUE_LED.off()
    sleep(0.5)
    BLUE_LED.on()
    sleep(2)
    BLUE_LED.off()
    sleep(1)

def second_test():
    
    GREEN_LED.on()
    sleep(0.1)
    GREEN_LED.off()
    sleep(0.1)
    RED_LED.on()
    sleep(0.1)
    RED_LED.off()
    sleep(0.1)
    YELLOW_LED.on()
    sleep(0.1)
    YELLOW_LED.off()
    sleep(0.1)
    BLUE_LED.on()
    sleep(0.1)
    BLUE_LED.off()
    sleep(0.1)
    GREEN_LED.on()
    sleep(0.1)
    GREEN_LED.off()
    sleep(0.1)
    RED_LED.on()
    sleep(0.1)
    RED_LED.off()
    sleep(0.1)
    YELLOW_LED.on()
    sleep(0.1)
    YELLOW_LED.off()
    sleep(0.1)
    BLUE_LED.on()
    sleep(0.1)
    BLUE_LED.off()
    sleep(0.1)
    
show_state()
sleep(SLEEP)
low_leds()