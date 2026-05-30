package com.qaengineer.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("=== INICIANDO SETUP ===");

        WebDriverManager.chromedriver().setup();
        System.out.println("=== WEBDRIVERMANAGER OK ===");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        System.out.println("=== CREANDO CHROME DRIVER ===");
        driver = new ChromeDriver(options);
        System.out.println("=== DRIVER CREADO: " + driver + " ===");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("=== TEARDOWN ===");
        if (driver != null) {
            driver.quit();
        }
    }
}