import RPi.GPIO as gpio
from gpiozero import LED
import dht11
import time
import datetime
from time import sleep
import json

# initialize GPIO
gpio.setwarnings(True)
gpio.setmode(gpio.BCM)

SENSOR = 27

sensor = dht11.DHT11(pin=SENSOR)

result = sensor.read()

while not result.is_valid():

    result = sensor.read()
    
out = {
"temp":result.temperature,
"hum":result.humidity,
"readTime":"Time: "+str(datetime.datetime.now())
    }

print(out)



    