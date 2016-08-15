 # -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import datetime
from datetime import date, timedelta

browser = webdriver.Chrome()
browser.get("https://app.mavenlink.com/login")
time.sleep(5)

username=browser.find_element_by_name("login[email_address]")
password=browser.find_element_by_name("login[password]")


username.send_keys("sumit.pathak@iquanti.com")
password.send_keys("--23october--")

login_attempt = browser.find_element_by_xpath("//*[@type='submit']")
login_attempt.submit()

browser.maximize_window()
browser.implicitly_wait(20)

browser.get("https://app.mavenlink.com/timesheets")
#time.sleep(20)
#browser.find_element_by_link_text('Timesheets').click()
#browser.find_element_by_link_text('Time &amp; Expense').click()
#browser.find_element_by_xpath("//a[@class='outer-link']/span[@class='nav-link-text']").click()

browser.implicitly_wait(5)
#browser.FindElement(By.LinkText("Timesheets")).Click();


def pass_project_name():
    return "Product - ALPS Rel 0.3"

def pass_task_name():
    return "Sprint 6 June"

project_name = browser.find_element_by_xpath("//div[@class='selectize-input items not-full has-options']/input[@type='text']")
#task_name = browser.find_element_by_xpath("//div[@class='autocompleter ']/input[@class='ui-autocomplete-input']")

print "-----",project_name

project_name.send_keys("Product - ALPS Rel 0.3")
#browser.implicitly_wait(5)


task_name = browser.find_element_by_xpath("//div[@class='autocompleter ']/input[@class='ui-autocomplete-input']")
task_name.send_keys(Keys.TAB)
task_name.send_keys("Sprint 6 June")

hourfill_path=driver.find_element_by_xpath("//*td[@class='day2 time-entry-cell replaceable']/input[@class='entry-box ']")
hourfill_path.send_keys("8")



