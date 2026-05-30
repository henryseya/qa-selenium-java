package com.qaengineer.pages;

import com.qaengineer.utils.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.cssSelector("button[type='submit']");
    private final By flashMessage  = By.id("flash");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Navegar a la página de login")
    public LoginPage goTo(String url) {
        driver.get(url);
        return this;
    }

    @Step("Ingresar usuario: {username}")
    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    @Step("Ingresar contraseña")
    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    @Step("Hacer clic en Login")
    public LoginPage clickLogin() {
        click(loginButton);
        return this;
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isFlashMessageVisible() {
        return isVisible(flashMessage);
    }
}