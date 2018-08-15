# xform-qa
This xform-qa project contains 2 subprojects one for JMeter API testing and the second Automation testing using Web Driver.
## JMeter Local Deployment
### JMeter Installation
* Download JMeter from http://jmeter.apache.org/download_jmeter.cgi (http://mirrors.ucr.ac.cr/apache//jmeter/source/apache-jmeter-3.3_src.zip)
* Install JMeter.
* Open JMeter (\apache-jmeter-3.2\bin\ApacheJMeter.jar).
* Open the file xform-jd-testplan.jmx file from the Repository installatio .

### Test Cases parametrization
* The file testcases.csv is used to describe which API's will be called.
* The field "keywords" separated by semi-colons ";" will indicate which "Simple Controller" will be executed". 
<br>

## Web Driver Local Deployment

### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

### JDK Java 8
* Download and install the [JDK Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### IntelliJ Installation
* Download IntelliJ Community Edition from https://www.jetbrains.com/idea/download/#section=windows (https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC)

### Installing

TBD

### Setup on MAC 

To use the project in MAC environments the user just need to change the browser "value" in the .xml for each section that the user wants to run in mac enviroment. (To see the list of mac drivers availables go to /xform-object-repository/src/main/java/com/janeirodigital/xform/webdriver/enums/BrowsersEnum.java) 

** NOTE: Please keep in mind that this changes should be only in your local if you are using a Mac device. Please DO NOT PUSH the .xml files without CHROME_LINUX_64 as a browser value.
 
## Running the tests
This project used TestNG to run test cases. For references go to:[TestNG](http://testng.org/doc/selenium.html) in this case the project used a masterSuite.xml to run all sections.
