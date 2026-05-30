package com.qaengineer.tests;

import com.qaengineer.data.TestDataLoader;
import com.qaengineer.data.UserData;
import com.qaengineer.pages.LoginPage;
import com.qaengineer.pages.SecurePage;
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
    @Description("Login exitoso con credenciales válidas desde JSON externo")
    @Severity(SeverityLevel.BLOCKER)
    public void loginExitosoTest() {
        // Lee datos desde users.json — no hardcodeados en el test
        UserData user = TestDataLoader.getValidUser();

        LoginPage loginPage = new LoginPage(driver);
        SecurePage securePage = (SecurePage) loginPage
                .goTo(LOGIN_URL)
                .enterUsername(user.getUsername())
                .enterPassword(user.getPassword())
                .clickLogin(new SecurePage(driver));

        Assert.assertTrue(
                securePage.isLoaded(),
                "El área segura debe estar cargada tras login exitoso"
        );
    }

    @Test(groups = {"regression"}, dataProvider = "invalidUsersFromJson")
    @Description("Login fallido muestra error correcto — datos desde JSON")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFallidoTest(String username, String password, String expectedError) {
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
                loginPage.getFlashMessage().contains(expectedError),
                "Error esperado: " + expectedError
        );
    }

    @DataProvider(name = "invalidUsersFromJson")
    public Object[][] invalidUsersFromJson() {
        // Lee todos los usuarios inválidos del JSON
        return TestDataLoader.getInvalidUsers();
    }
}