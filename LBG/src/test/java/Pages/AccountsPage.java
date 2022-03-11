package Pages;

import Utilities.browserDriver;

import java.io.IOException;

public class AccountsPage extends browserDriver {
    private final String accountProperties = "src/test/resources/Screens/Accounts.properties";

    public AccountsPage() throws IOException {
        objectReader(accountProperties);
    }

    public void selectAccounts(String account) {
        switch (account) {
            case "Classic":
                scrollUntilElementIsVisible("xpath", "classicAccount_xpath");
                click(getElement("xpath", "classicAccount_xpath"));
                break;
            case "ClubLloyds":
                click(getElement("xpath", "clubLloydsAccount_xpath"));
                break;
            case "Platinum":
                click(getElement("xpath", "platinumAccount_xpath"));
                break;
            default:
                System.out.println("invalid account option" + account);

        }
    }

    public void validateAccountPage(String account) {
        switch (account) {
            case "Classic":
                System.out.println(">>>>>>>>title is " + driver.getTitle());
                isVisible("classicAccountInfo_xpath");
                break;
            case "ClubLloyds":
                System.out.println(">>>>>>>>title is " + driver.getTitle());
                isVisible("clublloydsAccountInfo_xpath");
                break;
            case "Platinum":
                System.out.println(">>>>>>>>title is " + driver.getTitle());
                isVisible("platinumAccountInfo_xpath");
                break;
            default:
                System.out.println("invalid account option" + account);
        }
    }
    public void verifyAccountOption(String account){
        switch (account){
            case "Classic":
                scrollUntilElementIsVisible("xpath","classicAccount_xpath");
                isVisible("classicAccount_xpath");
                break;
            case "Clublloyds":
                scrollUntilElementIsVisible("xpath","clubLloydsAccount_xpath");
                isVisible("clubLloydsAccount_xpath");
                isVisible("platinumAccount_xpath");
                break;
            case "Platinum":
                scrollUntilElementIsVisible("xpath","platinumAccount_xpath");
                isVisible("platinumAccount_xpath");
                break;
            default:
                System.out.println("invalid account option"+account);

        }
    }
}
