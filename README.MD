Test Scenario:
==========================================
1. Automate below scenario in Java language :
- Parse the attached buildings.xml and retrieve the information inside the XML file
- Visit https://demoqa.com/automation-practice-table/
- Compare if the information in the XML file is the same as the table. Give an assertion at the end of your test.

Framework/libraries to use:

Selenium framework
Maven
Any xml marshal library
Any external reporting tools (not including the Junit in-built report)
Any necessary libraries

Test Cases:
==========================================
1. Verify Country of Burj Khalifa is the same between the XML and the Web
2. Verify Rank of Financial Center is the same between the XML and the Web
3. Verify Built Year of Tai Pei 101 is the same between the XML and the Web
4. Verify Height of Clock Tower Hotel is the same between the XML and the Web
5. Verify Country of Burj Khalifa in XML is not the same as Country of Taipei 101 from the Web  

Prerequisite:
==========================================
Please run the test in windows, as the file path checking for OSX and Linux is not yet verified due to time constrain

Implementation Approach:
==========================================
Two classes are created to handle the data loading:
 - Parser.class handles xml resource file loading as Dictionary
 - WebParser.class handles web scraping by selenium
 
 The test allows user to specify which field to compare by using different argument between the data loading from these 2 classes
 
 How to run?
 1. Use Intellij to excute the FunctionTest, the project is not yet configure to support command line test run