 # -*- coding: utf-8 -*-#! /usr/bin/python
import requests
#import csv
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import datetime
from datetime import date, timedelta
#import urllib2

browser = webdriver.Chrome()
browser.get("http://portal.acttv.in/web/blr/home")
time.sleep(5)

username = browser.find_element_by_name("uname")
password = browser.find_element_by_name("pword")

username.send_keys("10990630")
password.send_keys("sn32ca")

login_attempt = browser.find_element_by_xpath("//*[@type='submit']")
login_attempt.submit()
