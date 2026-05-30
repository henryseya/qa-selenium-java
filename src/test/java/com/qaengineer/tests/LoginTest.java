package com.qaengineer.tests;

import com.qaengineer.pages.LoginPage;
import com.qaengineer.utils.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final String LOGIN_URL =
            "https://the-internet.herokuapp.com/login";

    @Test(groups = {"smoke"})
    @Description("Login exitoso con credenciales válidas")
    @Severity(SeverityLevel.BLOCKER)
    public void loginExitosoTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .goTo(LOGIN_URL)
                .enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .clickLogin();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/secure"),
                "Debe redirigir a /secure tras login exitoso"
        );
    }

    @Test(groups = {"regression"}, dataProvider = "credencialesInvalidas")
    @Description("Login fallido muestra mensaje de error")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFallidoTest(String username, String password, String expectedMsg) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .goTo(LOGIN_URL)
                .enterUsername(username)
                .enterPassword(password)
                .clickLogin();

        Assert.assertTrue(
                loginPage.isFlashMessageVisible(),
                "El mensaje de error debe ser visible"
        );
        Assert.assertTrue(
                loginPage.getFlashMessage().contains(expectedMsg),
                "Mensaje esperado: " + expectedMsg
        );
    }

    @DataProvider(name = "credencialesInvalidas")
    public Object[][] credencialesInvalidas() {
        return new Object[][] {
                { "wronguser",  "SuperSecretPassword!", "Your username is invalid" },
                { "tomsmith",   "wrongpassword",        "Your password is invalid" },
        };
    }
}