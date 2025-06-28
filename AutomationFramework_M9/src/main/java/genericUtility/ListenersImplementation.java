package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "----Started");

		test = report.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "----Passed");
		test.log(Status.PASS, methodName + "----Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "----Failed");
		test.log(Status.FAIL, methodName + "----Failed");
		test.log(Status.INFO, result.getThrowable());

		WebDriverUtility driverUtility = new WebDriverUtility();
		JavaUtility javaUtil = new JavaUtility();

		String screenshotName = methodName + "-" + javaUtil.toGetSystemDateAndTime();
		try {
			String path = driverUtility.toTakeScreenshot(BaseClass.sDriver, screenshotName);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "----Skipped");
		test.log(Status.SKIP, methodName + "----Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("---Suite Execution Started---");

		ExtentSparkReporter htmlReport = new ExtentSparkReporter(
				"./ExtentReports/Report-" + new JavaUtility().toGetSystemDateAndTime() + ".html");
		htmlReport.config().setDocumentTitle("VTIGER EXECUTION REPORT");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("Vtiger Execution Report");

		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("url", "http://localhost:8888/");
		report.setSystemInfo("username", "admin");
		report.setSystemInfo("password", "password");
		report.setSystemInfo("Base Browser", "chrome");
		report.setSystemInfo("Reporter Name", "Balaram");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("---Suite Execution Finished---");
		report.flush();
	}
}
