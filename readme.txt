

- All dependencies and its versions is at the POM.xml

- All tests are under the path - src/test/java/tets/functional

    --> **Develop to run single test or parallel multi-thread**
        tu run multi-thread go to testSuite.xml and change the parameter "thread-count" to the number of threads you want
    
    --> **There's some ways to trigger the test**
        1. As the project has configured to the test lifecycle, you can run the tests simply using the bellow command on terminal
            and it'll run the tets provided at src/test/java/resources/testSuite.xml
            -- $ mvn test
        2. Another way is using the surefire and suiteXmlFile plugins by commandline to trigger an especific .xml file life following
            -- $ mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testSuite.xml
        3. Trigger using the TestNG running:
            --> Go to the test class under path - src/test/java/tets/functional/ConvertionTest.java
            --> right-click --> run 'className'
    
    --> **To see the Report result you can** 
        1.Go to target/extentReport.html right-click and open in browser, or directly open the file at the browser page
        2. run the bellow command on terminal
            -- $ start target/ExtentReport/extentReport.html
            to see a report example
            -- $ start target/ExtentReport/example-report.html
        3. One of the requirements of the test is to verify the result after the convertion
            at the report  on right, there's a info called "ConvertionRate:" where we can se the convertion rate for the contry paramets
            