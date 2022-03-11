package StepDefs;
import Pages.LloydsAOV;
import io.cucumber.java.en.*;
import java.io.IOException;

public class LloydsAOVStepdefs {
    LloydsAOV lloydsAOV=new LloydsAOV();
    public LloydsAOVStepdefs() throws IOException {

    }
    @Given("I am on Lloyds banking web page")
    public void i_am_on_lloydsAccount() {
        lloydsAOV.launchLloydsURL();
    }

    @When("I select Products and Services")
    public void iHoverAtProductsAndServices() {
        lloydsAOV.hoverOverProductsAndServices();
    }

    @Then("I should see Current Accounts")
    public void iShouldSeeCurrentAccounts() {
        lloydsAOV.verifyCurrentAccountOption();
    }

    @When("I select Current accounts")
    public void iHoverAtCurrentAccounts() {
        lloydsAOV.hoverOverCurrentAccount();
    }

    @When("I select <accounts>")
    public void iSelectAccounts() {
    }

}
