package praktikum.personalarea;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.main.BaseTest;
import praktikum.pom.AccountProfilePage;
import praktikum.pom.LoginPage;
import praktikum.pom.MainPage;
import praktikum.pom.RegisterPage;
import praktikum.user.User;
import praktikum.user.UserClient;
import praktikum.user.UserGenerator;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TestLogOut extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String userAccessToken;

    @Before
    @DisplayName("Регистрация тестового аккаунта и переход в его личный кабинет.")
    public void accountRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue("Ожидаем появления секции \"Вход\" после успешной регистрации.", loginPage.isEnterSectionVisible());
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAreaButton();
    }

    @After
    @DisplayName("Удаление тестового аккаунта.")
    public void accountCleanUp() {
        userAccessToken = userClient.getAccessTokenOnLogin(user);
        if (userAccessToken != null) {
            userClient.delete(userAccessToken);
        }
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете. LoggedIn пользователь.")
    public void constructorButtonFromPersonalArea() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickLogOutButton();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Ожидаем появления секции \"Вход\" странице", loginPage.isEnterSectionVisible());
    }
}
