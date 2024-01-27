package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedMyAccount extends BasePage {
    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']/p/strong[1]")
    private WebElement welcomeSub;

    public String getWelcomeSub() {
        return welcomeSub.getText();
    }
}
