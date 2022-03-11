package StepDefs;

import Pages.AccountsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class AccountsStepdefs {
    AccountsPage accountsPage = new AccountsPage();

    public AccountsStepdefs() throws IOException {
    }

    @Then("I should see the respective page")
    public void iShouldSeeTheRespectivePage() {

    }

    @And("I should see account fee for {string}")
    public void iShouldSeeAccountFee(String account) {
        accountsPage.validateAccountPage(account);
    }

    @When("I select {string}")
    public void iSelectAccounts(String account) {
        accountsPage.selectAccounts(account);
    }

    @Then("I should see the respective page for")
    public void iShouldSeeTheRespectivePageFor(DataTable dataTable) {
        for (int i = 1; i < dataTable.height(); i++) {
            accountsPage.verifyAccountOption(dataTable.cell(i, 0));
        }
    }
}
