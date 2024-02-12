package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static tests.DriverSingleton.closeDriver;
import static tests.DriverSingleton.getDriver;

public class BaseTests {

    @BeforeMethod
    public void setup() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get("https://skleptest.pl/");
    }

    @AfterMethod
    public void tearDown() {
        closeDriver();
    }
}
