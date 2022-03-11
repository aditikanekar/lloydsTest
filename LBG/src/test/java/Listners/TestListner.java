package Listners;


import ExtentReports.ExtentManager;
import Utilities.Log;
import Utilities.browserDriver;
import Utilities.commonUtilities;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static ExtentReports.ExtentTestManager.getTest;
import static ExtentReports.ExtentTestManager.startTest;


public class TestListner extends browserDriver implements ITestListener {
    static ExtentTest test;
    commonUtilities commonUtilities = new commonUtilities();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {

        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
        driver.quit();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
        test = startTest("login", "Lloyds");
        System.out.println(">test status insideOn test start" + test.getStatus());
        System.out.println(">test model insideOn test start" + test.getModel());
        //System.out.println(">test status insideOn test start"+test.);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        try {
            test.pass(MediaEntityBuilder.createScreenCaptureFromPath(commonUtilities.takeScreenshot()).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            try {
                test.fail(iTestResult.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(commonUtilities.takeScreenshot()).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        try {
            getTest().log(Status.SKIP, iTestResult.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath("." + commonUtilities.takeScreenshot()).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}