package com.qaengineer.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public class BaseTest {

    protected WebDriver driver;
    protected ConfigManager config = ConfigManager.getInstance();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Lee headless desde config.properties
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Screenshot en PASS y FAIL con estado en el nombre
        String status = result.getStatus() == ITestResult.SUCCESS ? "PASS" : "FAIL";
        takeScreenshot(status + "_" + result.getName());

        if (driver != null) {
            driver.quit();
        }
    }

    @Attachment(value = "Screenshot - {testName}", type = "image/png")
    public byte[] takeScreenshot(String testName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}