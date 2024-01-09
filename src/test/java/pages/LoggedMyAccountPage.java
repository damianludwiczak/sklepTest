package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedMyAccountPage {
    @FindBy(xpath = "//li[@class='woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--dashboard is-active']//a")
    private WebElement dashboardLink;

    @FindBy(xpath = "//li[@class='woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--orders']/a")
    private WebElement ordersLink;

    @FindBy(css = "a[href='https://skleptest.pl/my-account/downloads/']")
    private WebElement downloadsLink;

    @FindBy(css = "a[href='https://skleptest.pl/my-account/edit-address/']")
    private WebElement addressesLink;

    @FindBy(css = "a[href='https://skleptest.pl/my-account/edit-account/']")
    private WebElement accountDetailsLink;

    @FindBy(css = "a[href='https://skleptest.pl/my-account/customer-logout/']")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[@class='woocommerce-message woocommerce-message--info woocommerce-Message woocommerce-Message--info woocommerce-info']/a")
    private WebElement goShopButton;

    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']/p/strong[1]")
    private WebElement welcomeSub;

    public LoggedMyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickDashboardLink() {
        dashboardLink.click();
    }

    public void clickOrdersLink() {
        ordersLink.click();
    }

    public void clickDownloadsLink() {
        downloadsLink.click();
    }

    public void clickAddressesLink() {
        addressesLink.click();
    }

    public void clickAccountDetailsLink() {
        accountDetailsLink.click();
    }

    public void clickLogoutLink() {
        logoutLink.click();
    }

    public void clickGoShopButton() {
        goShopButton.click();
    }

    public String getWelcomeSub() {
        return welcomeSub.getText();
    }
}
