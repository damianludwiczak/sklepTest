package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoggedMyAccountPage;
import pages.MyAccountPage;

public class LoginTest extends BaseTest {

    private MyAccountPage myAccountPage;
    private LoggedMyAccountPage loggedMyAccountPage;
    @Test
    public void loginWithValidCredentials() {
        openAccountTab();
        initLoggedMyAccountPage();

        myAccountPage.setUsername("Testowy286@test.com");
        myAccountPage.setPassword("Testowy286@test.com");
        myAccountPage.performLogin();

        Assert.assertTrue(loggedMyAccountPage.getWelcomeSub().contains("Testowy286"));

        myAccountPage.clickContactButton();
    }

    @Test
    public void loginWithInvalidPassword() {
        openAccountTab();
        initLoggedMyAccountPage();

        myAccountPage.clickAccountButton();
        String login = "Testowy286@test.com";

        myAccountPage.setUsername(login);
        myAccountPage.setPassword("Testowy286@test");
        myAccountPage.performLogin();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" The password you entered for the username "
                + login + " is incorrect. "));
    }

    @Test
    public void loginWithNonExistsUsername() {
        openAccountTab();
        initLoggedMyAccountPage();

        String login = "Testowy286121@test.com";

        myAccountPage.setUsername(login);
        myAccountPage.setPassword("Testowy286@test");
        myAccountPage.performLogin();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" A user could not be found with this email address."));
    }
    @Test
    public void loginWithEmptyUsernameField() {
        openAccountTab();

        myAccountPage.performLogin();
        Assert.assertEquals(myAccountPage.getErrorMessage(), "Error: Username is required.");
    }

    @Test
    public void loginWithEmptyPasswordField() {
        openAccountTab();

        myAccountPage.setUsername("Testowy286@test.com");
        myAccountPage.performLogin();

        Assert.assertEquals(myAccountPage.getErrorMessage(), "Error: The password field is empty.");
    }

    private void openAccountTab() {
        myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickAccountButton();
    }

    private void initLoggedMyAccountPage() {
        loggedMyAccountPage = new LoggedMyAccountPage(driver);
    }
}
