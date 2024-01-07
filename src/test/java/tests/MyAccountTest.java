package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoggedMyAccountPage;
import pages.MyAccountPage;

import java.util.Random;

public class RegisterTest extends BaseTest {
    private MyAccountPage myAccountPage;
    private LoggedMyAccountPage loggedMyAccountPage;

    @Test
    public void registerUserWithValidInput() {
        openAccountTab();
        initLoggedMyAccountPage();
        Random random = new Random();

        String username = "Testowy" + random.nextInt(10000);
        myAccountPage.setRegEmail(username + "@test.com");
        myAccountPage.setRegPassword(username + "@test.com");
        myAccountPage.performRegister();

        Assert.assertEquals(loggedMyAccountPage.getWelcomeSub(), username);
    }

    @Test
    public void registerWithEmailWhichDoesNotContainAtSign() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.isRegEmailLabelTextDisplayed());
    }

    @Test
    public void registerWithInvalidEmail() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy@aaa");
        myAccountPage.setRegPassword("Testowy@aaa");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" Please provide a valid email address."));
    }
    @Test
    public void registerWithEmptyEmailField() {
        openAccountTab();
        myAccountPage.setRegPassword("Testowy123@");
        myAccountPage.performRegister();

        Assert.assertEquals(myAccountPage.getErrorMessage(), ("Error: Please provide a valid email address."));
    }

    @Test
    public void registerWithEmptyAllFields() {
        openAccountTab();
        myAccountPage.performRegister();
        System.out.println(myAccountPage.getErrorMessage());
        Assert.assertEquals(myAccountPage.getErrorMessage(), ("Error: Please provide a valid email address."));
    }



    @Test
    public void registerWithEmptyPasswordField() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy@test.com");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains("Please enter an account password."));
    }

    @Test
    public void registerWithWeakPassword() {
        openAccountTab();

        myAccountPage.setRegEmail("Testowy@test.com");
        myAccountPage.setRegPassword("Testowy12");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.isRegPasswordLabelText() &
                myAccountPage.getRegPasswordLabelText().contains("Weak - Please enter a stronger password.") &
                !myAccountPage.isRegisterButtonAvailable());
    }

    private void openAccountTab() {
        myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickAccountButton();
    }
    // todo refactor

    private void initLoggedMyAccountPage() {
        loggedMyAccountPage = new LoggedMyAccountPage(driver);
    }
}
