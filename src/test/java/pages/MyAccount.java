package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static tests.DriverSingleton.getDriver;

public class MyAccount extends BasePage {
    private Actions actions;
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(id = "reg_email")
    private WebElement regEmailInput;

    @FindBy(id = "reg_password")
    private WebElement regPasswordInput;

    @FindBy(name = "register")
    private WebElement regButton;

    @FindBy(xpath = "//article[@id='post-8']/div[2]/ul/li")
    private WebElement error;

    @FindBy(css = "label[for='reg_password']")
    private WebElement regEmailLabel;

    @FindBy(css = "div[class='woocommerce-password-strength bad']")
    private WebElement regPasswordLabel;

    public MyAccount setUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public MyAccount setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public MyAccount performLogin() {
        loginButton.click();
        return this;
    }

    public MyAccount setRegEmail(String email) {
        regEmailInput.sendKeys(email);
        return this;
    }

    public MyAccount setRegPassword(String password) {
        actions = new Actions(getDriver());
        actions.moveToElement(regPasswordInput).click().perform();
        regPasswordInput.sendKeys(password);
        return this;
    }

    public MyAccount performRegister() {
        actions = new Actions(getDriver());
        actions.moveToElement(regPasswordInput).click().perform();
        regButton.click();
        return this;
    }

    public boolean isRegisterButtonAvailable() {
        return regButton.isEnabled();
    }

    public String getErrorMessage() {
        return error.getText();
    }

    public boolean isRegEmailLabelTextDisplayed() {
        return regEmailLabel.isDisplayed() & regEmailLabel.getText().contains("Password *");
    }

    public String getRegPasswordLabelText() {
        return regPasswordLabel.getText();
    }

    public boolean isRegPasswordLabelText() {
        return regPasswordLabel.isDisplayed();
    }
}
