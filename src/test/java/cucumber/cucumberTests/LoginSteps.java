package cucumber.cucumberTests;

import api.coca.cola.launcher.screen.LauncherView;
import api.coca.cola.login.screen.LoginView;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.FileNotFoundException;

import static core.json.parsers.ConfigJasonFileReading.runningSetup;

public class LoginSteps {
    private String unregisteredEmail = runningSetup().getUnregisteredEmail();

    public LoginSteps() throws FileNotFoundException {
    }

    @And("^User selects Log In$")
    public void userSelectsLogIn() {
        LauncherView launcherView = new LauncherView();
        launcherView.clickLoginCategory();
    }

    @Given("^The elements from Login screen are displayed$")
    public void theElementsFromLoginScreenAreDisplayed() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.validateElementsLoginScreen();
    }


    @Given("^User enters a registered e-mail address$")
    public void userEntersARegisteredEMailAddress() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.sendTextEmailAddressUsingJson();
    }

    @And("^User clicks on proceed with Login$")
    public void userClicksOnProceedWithLogin() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.clickProceedWithLogin();
    }


    @Given("^User enters an unregistered e-mail address$")
    public void userEntersAnUnregisteredEMailAddress() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.sendTextEmailAddressUsingString(unregisteredEmail);
    }

    @Then("^Wrong email message is displayed to the user$")
    public void wrongEmailMessageIsDisplayedToTheUser() throws FileNotFoundException {
        LoginView loginView = new LoginView();
        loginView.validateWrongEmailNotification();
    }
}
