package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.LoggedMyAccountPage;
import pages.MyAccountPage;

import java.util.Random;

public class MyAccountTests extends BaseTests {
    private MyAccountPage myAccountPage;
    private LoggedMyAccountPage loggedMyAccountPage;
    private Header header;

//    @BeforeClass // TODO: 09.01.2024
    private void openAccountTab() {
        myAccountPage = new MyAccountPage(driver);
        header = new Header(driver);
        loggedMyAccountPage = new LoggedMyAccountPage(driver);

        header.clickAccountButton();
    }

    @Test
    public void registerUserWithValidInputTest() {
        openAccountTab();

        Random random = new Random();

        String username = "Testowy" + random.nextInt(10000);
        myAccountPage.setRegEmail(username + "@test.com");
        myAccountPage.setRegPassword(username + "@test.com");
        myAccountPage.performRegister();

        Assert.assertEquals(loggedMyAccountPage.getWelcomeSub(), username);
    }

    @Test
    public void registerWithEmailWhichDoesNotContainAtSignTest() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.isRegEmailLabelTextDisplayed());
    }

    @Test
    public void registerWithInvalidEmailTest() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy@aaa");
        myAccountPage.setRegPassword("Testowy@aaa");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" Please provide a valid email address."));
    }

    @Test
    public void registerWithEmptyEmailFieldTest() {
        openAccountTab();

        myAccountPage.setRegPassword("Testowy123@");
        myAccountPage.performRegister();

        Assert.assertEquals(myAccountPage.getErrorMessage(), ("Error: Please provide a valid email address."));
    }

    @Test
    public void registerWithEmptyAllFieldsTest() {
        openAccountTab();

        myAccountPage.performRegister();
        Assert.assertEquals(myAccountPage.getErrorMessage(), ("Error: Please provide a valid email address."));
    }


    @Test
    public void registerWithEmptyPasswordFieldTest() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy@test.com");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains("Please enter an account password."));
    }

    @Test
    public void registerWithWeakPasswordTest() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy@test.com");
        myAccountPage.setRegPassword("Testowy12");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.isRegPasswordLabelText() &
                myAccountPage.getRegPasswordLabelText().contains("Weak - Please enter a stronger password.") &
                !myAccountPage.isRegisterButtonAvailable());
    }

    @Test
    public void loginWithInvalidPasswordTest() {
        openAccountTab();

        String login = "Testowy286@test.com";

        myAccountPage.setUsername(login);
        myAccountPage.setPassword("Testowy286@test");
        myAccountPage.performLogin();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" The password you entered for the username "
                + login + " is incorrect. "));
    }

    @Test
    public void loginWithNonExistsUsernameTest() {
        openAccountTab();

        String login = "Testowy286121@test.com";

        myAccountPage.setUsername(login);
        myAccountPage.setPassword("Testowy286@test");
        myAccountPage.performLogin();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" A user could not be found with this email address."));
    }

    @Test
    public void loginWithEmptyUsernameFieldTest() {
        openAccountTab();

        myAccountPage.performLogin();
        Assert.assertEquals(myAccountPage.getErrorMessage(), "Error: Username is required.");
    }

    @Test
    public void loginWithEmptyPasswordFieldTest() {
        openAccountTab();

        myAccountPage.setUsername("Testowy286@test.com");
        myAccountPage.performLogin();

        Assert.assertEquals(myAccountPage.getErrorMessage(), "Error: The password field is empty.");
    }

    @Test
    public void loginWithValidCredentialsTest() {
        openAccountTab();

        myAccountPage.setUsername("Testowy286@test.com");
        myAccountPage.setPassword("Testowy286@test.com");
        myAccountPage.performLogin();

        Assert.assertTrue(loggedMyAccountPage.getWelcomeSub().contains("Testowy286"));
    }
}
