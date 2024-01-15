package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.Header;
import pages.LoggedMyAccountPage;
import pages.MyAccountPage;
import pages.SearchResultPage;

import java.util.concurrent.TimeUnit;

import static tests.DriverSingleton.closeDriver;
import static tests.DriverSingleton.getDriver;

public class BaseTests {

    protected MyAccountPage myAccountPage;
    protected LoggedMyAccountPage loggedMyAccountPage;
    protected Header header;
    protected SoftAssert softAssert;
    protected SearchResultPage searchResultPage;

    @BeforeMethod
    public void setup() {
        getDriver().manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        getDriver().get("https://skleptest.pl/");
        myAccountPage = new MyAccountPage();
        loggedMyAccountPage = new LoggedMyAccountPage();
        header = new Header();
        softAssert = new SoftAssert();
        searchResultPage = new SearchResultPage();
    }

    @AfterMethod
    public void tearDown() {
        closeDriver();
    }
}
