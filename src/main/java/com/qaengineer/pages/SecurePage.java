package com.qaengineer.pages;

import com.qaengineer.utils.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage extends BasePage {

    private final By pageHeader    = By.cssSelector("h2");
    private final By logoutButton  = By.cssSelector("a.button");
    private final By flashMessage  = By.id("flash");

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    @Step("Verificar que el área segura está cargada")
    public boolean isLoaded() {
        return isVisible(pageHeader) &&
                driver.getCurrentUrl().contains("/secure");
    }

    @Step("Obtener mensaje de bienvenida")
    public String getWelcomeMessage() {
        return getText(flashMessage);
    }

    @Step("Hacer logout")
    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage(driver);
    }
}