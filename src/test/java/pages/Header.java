package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {
    @FindBy(id = "search-field-top-bar")
    private WebElement searchInput;

    @FindBy(id = "search-top-bar-submit")
    private WebElement searchButton;

    @FindBy(css = "a[href='https://skleptest.pl/my-account/']")
    private WebElement accountButton;

    public Header setSearchInput(String keyword) {
        searchInput.sendKeys(keyword);
        return this;
    }

    public Header clickSearchButton() {
        searchButton.click();
        return this;
    }

    public Header clickAccountButton() {
        accountButton.click();
        return this;
    }
}
