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
These are the steps to run the project on MAC devices. (Chrome Example)
1. Download the driver for MAC devices [chromedriver](http://chromedriver.chromium.org/)
2. The driver should be storaged at ../xform-controllers/src/main/resources/
3. Set the TestNG xmls to Chrome_Win
4. In the file config.xml make the following changes:

###### <google_exe></google_exe>
```
<google_exe>/Applications/Google Chrome.app/Contents/MacOS/Google Chrome</google_exe>
```
###### <google_binary></google_binary>
```
<google_binary>../xform-controllers/src/main/resources/chromedriver</google_binary>
```
5. Then run the tests and they should open chrome

** NOTE: Please keep in mind that this changes should be only in your local if you are using a Mac device. Please DO NOT PUSH these files ("TestNG xmls" and "config.xml") to the repository.
 
## Running the tests
This project used TestNG to run test cases. For references go to:[TestNG](http://testng.org/doc/selenium.html)
