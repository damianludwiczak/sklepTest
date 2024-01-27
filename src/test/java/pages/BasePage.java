package pages;

import static org.openqa.selenium.support.PageFactory.initElements;
import static tests.DriverSingleton.getDriver;

public class BasePage {
    public BasePage() {
        initElements(getDriver(), this);
    }
}
