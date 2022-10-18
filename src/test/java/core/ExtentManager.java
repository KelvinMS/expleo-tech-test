package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.io.File;
import java.util.HashMap;

public class ExtentManager {

    private static ExtentReports extentReports;
    private static ExtentHtmlReporter htmlReporter;
    private static final String EXTENTREPORTPATH = "target/ExtentReport";
    private static final String REPORTNAME = "extentReport.html";
    private static final String FILENAME = "Extent Report";
    private static final String HTML_TAG_BR = "<br />";
    private static final String HTML_TAG_PRE = "<pre>";
    private static final String HTML_TAG_PRE_CLOSE = "</pre>";

    /**
     * Construction of html Reporter to build the interface of the report
     *
     * @return Return instance os ExtentReports
     */
    public static ExtentReports createInstance() {
        if (extentReports == null) {
            File testDirectory = new File(EXTENTREPORTPATH);
            if (!testDirectory.exists() && !testDirectory.mkdir()) {
                System.out.println("\n\n Test directory doesn't exist");
            }
            htmlReporter = new ExtentHtmlReporter(new File(EXTENTREPORTPATH + "/" + REPORTNAME));
            htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
            htmlReporter.config().setChartVisibilityOnOpen(true);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setDocumentTitle(FILENAME);
            htmlReporter.config().setEncoding("UTF-8");
            htmlReporter.config().setReportName(FILENAME);
            htmlReporter.config().setProtocol(Protocol.HTTPS);
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
        }
        return extentReports;
    }

    /**
     * Coloca uma serie de informações em uma lista
     *
     * @param iTestResult
     */
    public static void setTestParams(ITestResult iTestResult) {
        Object parameterMap = new HashMap();
        if (iTestResult.getTestContext().getCurrentXmlTest().getAllParameters().size() > 0) {
            parameterMap = iTestResult.getTestContext().getCurrentXmlTest().getAllParameters();
        } else {
            parameterMap = "No parameters Found";
        }
        ExtentTestListeners.getInstance().debug(HTML_TAG_BR + "Suite: " + iTestResult.getTestContext().getSuite().getName() + HTML_TAG_PRE
                + HTML_TAG_BR + "Test: " + iTestResult.getTestContext().getName()
                + HTML_TAG_BR + "Method: " + iTestResult.getMethod().getMethodName()
                + HTML_TAG_BR + "Params: " + HTML_TAG_BR + parameterMap + HTML_TAG_PRE_CLOSE);
        }

    /**
     * Set Environment variables at report
     */
    public static void setReportParams() {
        //Environment
        ExtentTestListeners.getRunningReport().setSystemInfo("User Name", System.getProperty("user.name"));
        ExtentTestListeners.getRunningReport().setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        ExtentTestListeners.getRunningReport().setSystemInfo("OS Name", System.getProperty("os.name"));
        ExtentTestListeners.getRunningReport().setSystemInfo("User language", System.getProperty("user.language"));
        ExtentTestListeners.getRunningReport().setSystemInfo("CPU", System.getProperty("cpu"));
    }



    /**
     * Cria uma infoMessage no report
     *
     * @param infoMessage String infoMessage
     */
    public static void info(String infoMessage) {
        ExtentTestListeners.getInstance().info(infoMessage);
    }

    /**
     * Cria uma infoMessage no report
     *
     * @param infoMessage String infoMessage
     */
    public static void info(String... infoMessage) {
        String infoMessages = "";
        for (String arg : infoMessage) {
            infoMessages = infoMessages + arg + HTML_TAG_BR;
        }
            ExtentTestListeners.getInstance().info(infoMessages);
    }
}
