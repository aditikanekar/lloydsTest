package Utilities;

import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class commonUtilities {
    public String takeScreenshot() throws IOException {
        String str="";
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
        Date date = new Date();
        str = dateFormat.format(date);
        str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        TakesScreenshot screen = (TakesScreenshot) browserDriver.driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String dest = "./extent-reports/Screenshots/" +  str+ ".jpeg";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }
}
