package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.BaseTests;
import tests.DriverSingleton;

import static tests.DriverSingleton.getDriver;

public class MyAccountPage {
    private WebDriver driver;
    private Actions actions;
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(id = "rememberme")
    private WebElement rememberMeChceckbox;

    @FindBy(css = "a[href='https://skleptest.pl/my-account/lost-password/']")
    private WebElement lostPasswordLink;

    @FindBy(id = "reg_email")
    private WebElement regEmailInput;

    @FindBy(id = "reg_password")
    private WebElement regPasswordInput;

    @FindBy(name = "register")
    private WebElement regButton;

    @FindBy(xpath = "//article[@id='post-8']/div[2]/ul/li")
    private WebElement error;

    @FindBy (css = "label[for='reg_password']")
    private WebElement regEmailLabel;

    @FindBy(css = "div[class='woocommerce-password-strength bad']")
    private WebElement regPasswordLabel;

    public MyAccountPage() {
        PageFactory.initElements(getDriver(), this);
        this.driver = getDriver();
        actions = new Actions(getDriver());
    }

    public void setUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void performLogin() {
        loginButton.click();
    }

    public void setRememberMeCheckBox(boolean condition) {
        if (condition)
            rememberMeChceckbox.click();
    }

    public void clickLostPassword() {
        lostPasswordLink.click();
    }

    public void setRegEmail(String email) {
        regEmailInput.sendKeys(email);
    }

    public void setRegPassword(String password) {
        actions.moveToElement(regPasswordInput).click().perform();
        regPasswordInput.sendKeys(password);
    }

    public void performRegister() {
        actions.moveToElement(regPasswordInput).click().perform();
        regButton.click();
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
