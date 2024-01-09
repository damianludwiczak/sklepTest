package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    @FindBy(xpath = "//div[@id='page']/header/div/div/div/ul/li/a[1]")
    private WebElement emailLink;

    @FindBy(id = "search-field-top-bar")
    private WebElement seachInput;

    @FindBy(id = "search-top-bar-submit")
    private WebElement searchButton;

    @FindBy(css = "a[href='https://skleptest.pl/my-account/']")
    private WebElement accountButton;

    @FindBy(css = "a[href='https://skleptest.pl/cart/']")
    private WebElement myCartButton;

    @FindBy(css = "a[href='https://skleptest.pl']")
    private WebElement logoButton;

    @FindBy(css = "a[href='https://skleptest.pl/']")
    private WebElement shopMenuButton;

    @FindBy(css = "a[href='https://skleptest.pl/product-category/most-wanted/']")
    private WebElement mostWantedButton;

    @FindBy(xpath = "//li[@id='menu-item-123']/a[1]")
    private WebElement categoriesButton;

    @FindBy(css = "a[href='https://skleptest.pl/shop/']")
    private WebElement categoryAllButton;

    @FindBy(xpath = "//li[@id='menu-item-125']/a")
    private WebElement categoryShirtButton;

    @FindBy(xpath = "//li[@id='menu-item-126']/a")
    private WebElement categoryFeatured;

    @FindBy(xpath = "//li[@id='menu-item-127']/a")
    private WebElement categoryTrends;

    @FindBy(xpath = "//li[@id='menu-item-129']/a")
    private WebElement categoryScarfs;

    @FindBy(xpath = "//li[@id='menu-item-130']/a")
    private WebElement categoryShoes;

    @FindBy(xpath = "//li[@id='menu-item-131']/a")
    private WebElement categoryTops;

    @FindBy(xpath = "//li[@id='menu-item-132']/a")
    private WebElement categoryBlouse;

    @FindBy(xpath = "//li[@id='menu-item-134']/a")
    private WebElement categoryJeans;

    @FindBy(xpath = "//li[@id='menu-item-133']/a")
    private WebElement categoryDresses;

    @FindBy(xpath = "//li[@id='menu-item-118']/a")
    private WebElement aboutUsButton;

    @FindBy(xpath = "//li[@id='menu-item-118']/a")
    private WebElement contactButton;
    @FindBy(css = "a[href='https://skleptest.pl/tag/all/']")
    private WebElement blogButton;
    private WebDriver driver;

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getEmailText() {
        return emailLink.getText();
    }

    public void setSearchInput(String keyword) {
        seachInput.sendKeys(keyword);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickAccountButton() {
        accountButton.click();
    }

    public void clickMyCartButton() {
        myCartButton.click();
    }

    public void clickLogoButton() {
        logoButton.click();
    }

    public void clickShopMenuButton() {
        shopMenuButton.click();
    }

    public void clickMostWantedButton() {
        mostWantedButton.click();
    }

    public void clickCategoryAll() {
        categoriesButton.click();
    }

    public void chooseCategory(String category) {
        Actions actions = new Actions(driver);
        actions.moveToElement(categoriesButton).build().perform();

        switch (category) {
            case "All" -> categoryAllButton.click();
            case "Shirts" -> categoryShirtButton.click();
            case "Featured" -> categoryFeatured.click();
            case "Trends" -> categoryTrends.click();
            case "Scarfs" -> categoryScarfs.click();
            case "Shoes" -> categoryShoes.click();
            case "Tops" -> categoryTops.click();
            case "Blouse" -> categoryBlouse.click();
            case "Jeans" -> categoryJeans.click();
            case "Dresses" -> categoryDresses.click();
            default -> categoriesButton.click();
        }
    }

    public void clickAboutUsButton() {
        aboutUsButton.click();
    }
    public void clickContactButton() {
        contactButton.click();
    }

    public void clickBlogButton() {
        blogButton.click();
    }

}
