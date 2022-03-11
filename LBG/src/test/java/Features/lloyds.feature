
 Feature: Verify Lloyds products

   @lloyds
   Scenario: Validation of Lloyds products
     Given I am on Lloyds banking web page
     When I select Products and Services
     And I select Current accounts
     Then I should see the respective page for
       | Accounts   |
       | Classic    |
       | Clublloyds |
       | Platinum   |