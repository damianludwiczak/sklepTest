package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoggedMyAccountPage;
import pages.MyAccountPage;

public class LoginTest extends BaseTest {
    @Test
    public void loginWithValidCredentials() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        LoggedMyAccountPage loggedMyAccountPage = new LoggedMyAccountPage(driver);

        myAccountPage.clickAccountButton();

        myAccountPage.setUsername("Testowy286@test.com");
        myAccountPage.setPassword("Testowy286@test.com");
        myAccountPage.performLogin();

        Assert.assertTrue(loggedMyAccountPage.getWelcomeSub().contains("Testowy286"));
    }

    @Test
    public void loginWithInvalidPassword() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        LoggedMyAccountPage loggedMyAccountPage = new LoggedMyAccountPage(driver);

        myAccountPage.clickAccountButton();
        String login = "Testowy286@test.com";

        myAccountPage.setUsername(login);
        myAccountPage.setPassword("Testowy286@test");
        myAccountPage.performLogin();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" The password you entered for the username "
                + login + " is incorrect. "));
    }

    @Test
    public void loginWithNotExistsUsername() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        LoggedMyAccountPage loggedMyAccountPage = new LoggedMyAccountPage(driver);

        myAccountPage.clickAccountButton();
        String login = "Testowy286121@test.com";

        myAccountPage.setUsername(login);
        myAccountPage.setPassword("Testowy286@test");
        myAccountPage.performLogin();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" A user could not be found with this email address."));
    }
}
