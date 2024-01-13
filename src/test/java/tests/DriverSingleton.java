package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {  // TODO: 09.01.2024
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.close();
        driver.quit();
        driver = null;
    }
}
