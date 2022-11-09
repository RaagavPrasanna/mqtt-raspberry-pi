#!/usr/bin/env python3
#############################################################################
# Filename    : DHT11.py
# Description :	read the temperature and humidity data of DHT11
# Author      : freenove
# modification: 2020/10/16
########################################################################
import RPi.GPIO as GPIO
import time
import Freenove_DHT as DHT
DHTPin = 11     #define the pin of DHT11

def temperature():
    dht = DHT.DHT(DHTPin)   #create a DHT class object
    print(dht.temperature)     
        
temperature()

