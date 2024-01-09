package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    protected static WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://skleptest.pl/");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public static WebDriver getInstance() { // // TODO: 09.01.2024  
        if (Objects.isNull(driver))
            driver = new ChromeDriver();
        return driver;
    }
}
