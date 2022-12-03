#!/usr/bin/env python3
########################################################################
# Filename    : Doorbell.py
# Description : Make doorbell with buzzer and button
# Author      : www.freenove.com
# modification: 2019/12/28
########################################################################
import RPi.GPIO as GPIO
from datetime import datetime
buzzerPin = 16    # define buzzerPin
buttonPin = 12    # define buttonPin

def setup():
    GPIO.setmode(GPIO.BOARD)        # use PHYSICAL GPIO Numbering
    GPIO.setup(buzzerPin, GPIO.OUT)   # set buzzerPin to OUTPUT mode
    GPIO.setup(buttonPin, GPIO.IN, pull_up_down=GPIO.PUD_UP)    # set buttonPin to PULL UP INPUT mode

def loop():
    while True:
        if GPIO.input(buttonPin)==GPIO.LOW: # if button is pressed
            GPIO.output(buzzerPin,GPIO.HIGH) # turn on buzzer
            #when the button is pressed, create the timestamp
            dt = datetime.now()
            ts = datetime.timestamp(dt)
            # print out timestamp in format like 2022-12-03 21:21:12
            print(ts)
            
        else : # if button is relessed
            GPIO.output(buzzerPin,GPIO.LOW) # turn off buzzer

def destroy():
    GPIO.cleanup()                     # Release all GPIO

if __name__ == '__main__':     # Program entrance
    print ('Program is starting...')
    setup()
    try:
        loop()
    except KeyboardInterrupt:  # Press ctrl-c to end the program.
        destroy()

