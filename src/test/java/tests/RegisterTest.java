package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoggedMyAccountPage;
import pages.MyAccountPage;

import java.util.Random;

public class RegisterTest extends BaseTest {
    @Test
    public void registerUserWithValidInput() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        LoggedMyAccountPage loggedMyAccountPage = new LoggedMyAccountPage(driver);
        Random random =new Random();

        myAccountPage.clickAccountButton();

        String username = "Testowy" + random.nextInt(10000);
        myAccountPage.setRegEmail(username + "@test.com");
        myAccountPage.setRegPassword(username + "@test.com");
        myAccountPage.performRegister();

        Assert.assertTrue(loggedMyAccountPage.getWelcomeSub().contains(username));
    }

    @Test
    public void registerWithEmailWithout() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        myAccountPage.clickAccountButton();

        myAccountPage.setRegEmail("Testowy");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.isRegEmailLabelTextDisplayed());
    }

    @Test
    public void registerWithInvalidEmail() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        myAccountPage.clickAccountButton();

        myAccountPage.setRegEmail("Testowy@aaa");
        myAccountPage.setRegPassword("Testowy@aaa");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains(" Please provide a valid email address."));
    }

    @Test
    public void registerWithoutPassword() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        myAccountPage.clickAccountButton();

        myAccountPage.setRegEmail("Testowy@test.com");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.getErrorMessage().contains("Please enter an account password."));
    }

    @Test
    public void registerWithWeakPassword() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        myAccountPage.clickAccountButton();

        myAccountPage.setRegEmail("Testowy@test.com");
        myAccountPage.setRegPassword("Testowy12");
        myAccountPage.performRegister();

        Assert.assertTrue(myAccountPage.isRegPasswordLabelText() &
                        myAccountPage.getRegPasswordLabelText().contains("Weak - Please enter a stronger password.") &
                        !myAccountPage.isRegisterButtonAvailable());
    }
}
