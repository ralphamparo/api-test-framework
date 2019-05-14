package utils.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;
import org.testng.annotations.AfterTest;
import utils.Resources;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestListener implements ITestListener, ISuiteListener {

    private ExtentReports extent;

    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test
                .get()
                .createNode(iTestResult.getMethod().getMethodName())
                .log(Status.PASS, MarkupHelper.createLabel("PASSED", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test
                .get()
                .createNode(iTestResult.getMethod().getMethodName())
                .log(Status.FAIL, iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test
                .get()
                .createNode(iTestResult.getMethod().getMethodName())
                .log(Status.SKIP, MarkupHelper.createLabel("SKIPPED", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // TODO
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentTest extentInstance = extent.createTest(iTestContext.getCurrentXmlTest().getName());
        test.set(extentInstance);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }

    @AfterTest
    public void closeExtent() {}

    @Override
    public void onStart(ISuite suite) {
        Date currentDateAndTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-hh.mm.ss");
        String formattedDateAndTime = dateFormat.format(currentDateAndTime);
        extent = ExtentManager.createInstance( Resources.REPORTS + suite.getName() + "_" + formattedDateAndTime + ".html");
    }

    @Override
    public void onFinish(ISuite suite) {}
}
