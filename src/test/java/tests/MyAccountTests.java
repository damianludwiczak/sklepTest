package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.LoggedMyAccount;
import pages.MyAccount;

import java.util.Random;

public class MyAccountTests extends BaseTests {
    @Test
    public void registerUserWithValidInputTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();
        LoggedMyAccount loggedMyAccount = new LoggedMyAccount();

        header
                .clickAccountButton();

        Random random = new Random();

        String username = "Testowy" + random.nextInt(10000);
        myAccount
                .setRegEmail(username + "@test.com")
                .setRegPassword(username + "@test.com")
                .performRegister();

        Assert.assertEquals(loggedMyAccount.getWelcomeSub(), username);
    }

    @Test
    public void registerWithEmailWhichDoesNotContainAtSignTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .setRegEmail("Testowy")
                .performRegister();

        Assert.assertTrue(myAccount.isRegEmailLabelTextDisplayed());
    }

    @Test
    public void registerWithInvalidEmailTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .setRegEmail("Testowy@aaa")
                .setRegPassword("Testowy@aaa")
                .performRegister();

        Assert.assertTrue(myAccount.getErrorMessage().contains(" Please provide a valid email address."));
    }

    @Test
    public void registerWithEmptyEmailFieldTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .setRegPassword("Testowy123@")
                .performRegister();

        Assert.assertEquals(myAccount.getErrorMessage(), ("Error: Please provide a valid email address."));
    }

    @Test
    public void registerWithEmptyAllFieldsTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .performRegister();

        Assert.assertEquals(myAccount.getErrorMessage(), ("Error: Please provide a valid email address."));
    }


    @Test
    public void registerWithEmptyPasswordFieldTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .setRegEmail("Testowy@test.com")
                .performRegister();

        Assert.assertTrue(myAccount.getErrorMessage().contains("Please enter an account password."));
    }

    @Test
    public void registerWithWeakPasswordTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .setRegEmail("Testowy@test.com")
                .setRegPassword("Testowy12")
                .performRegister();

        Assert.assertTrue(myAccount.isRegPasswordLabelText() &
                myAccount.getRegPasswordLabelText().contains("Weak - Please enter a stronger password.") &
                !myAccount.isRegisterButtonAvailable());
    }

    @Test
    public void loginWithInvalidPasswordTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        String login = "Testowy286@test.com";

        myAccount
                .setUsername(login)
                .setPassword("Testowy286@test")
                .performLogin();

        Assert.assertTrue(myAccount.getErrorMessage().contains(" The password you entered for the username "
                + login + " is incorrect. "));
    }

    @Test
    public void loginWithNonExistsUsernameTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        String login = "Testowy286121@test.com";

        myAccount
                .setUsername(login)
                .setPassword("Testowy286@test")
                .performLogin();

        Assert.assertTrue(myAccount.getErrorMessage().contains(" A user could not be found with this email address."));
    }

    @Test
    public void loginWithEmptyUsernameFieldTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .performLogin();
        Assert.assertEquals(myAccount.getErrorMessage(), "Error: Username is required.");
    }

    @Test
    public void loginWithEmptyPasswordFieldTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();

        header
                .clickAccountButton();

        myAccount
                .setUsername("Testowy286@test.com")
                .performLogin();

        Assert.assertEquals(myAccount.getErrorMessage(), "Error: The password field is empty.");
    }

    @Test
    public void loginWithValidCredentialsTest() {
        Header header = new Header();
        MyAccount myAccount = new MyAccount();
        LoggedMyAccount loggedMyAccount = new LoggedMyAccount();

        header
                .clickAccountButton();

        myAccount
                .setUsername("Testowy286@test.com")
                .setPassword("Testowy286@test.com")
                .performLogin();

        Assert.assertTrue(loggedMyAccount.getWelcomeSub().contains("Testowy286"));
    }
}
