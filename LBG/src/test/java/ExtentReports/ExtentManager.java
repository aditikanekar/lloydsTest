package ExtentReports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
       // ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent.html");
     //   reporter.config().setReportName("LBG Extent Report");
      //  extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Testcase Name", "LBG");
        extentReports.setSystemInfo("Author", "Aditi Kanekar");
        return extentReports;
    }
}
