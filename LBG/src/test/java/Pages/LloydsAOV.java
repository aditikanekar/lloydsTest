package Pages;

import java.io.IOException;

import Utilities.Log;
import Utilities.browserDriver;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import static ExtentReports.ExtentTestManager.*;


public class LloydsAOV extends browserDriver {
    private static final String browserName = "CHROME";
    private final String ldsAovProperties    ="src/test/resources/Screens/LloydsAOV.properties";
    public LloydsAOV() throws IOException {
       new browserDriver(browserName,ldsAovProperties);
       objectReader(ldsAovProperties);
    }
    public void launchLloydsURL()  {
        launchUrl("https://www.lloydsbank.com/");
        waitForPageToLoad();
    }
    public void hoverOverProductsAndServices(){
        click(getElement("xpath","productsAndService_xpath"));
    }
    public void verifyCurrentAccountOption(){
        isVisible("currentAccount_xpath");
    }

    public void hoverOverCurrentAccount(){
        click(getElement("xpath","takeALook_xpath"));
  }
}
