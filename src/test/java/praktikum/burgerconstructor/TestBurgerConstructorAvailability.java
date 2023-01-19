package praktikum.burgerconstructor;

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
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TestBurgerConstructorAvailability extends BaseTest {
    private User user;
    private final UserClient userClient = new UserClient();
    private String userAccessToken;

    @Before
    @DisplayName("Регистрация тестового аккаунта и переход в его личный кабинет.")
    public void accountRegistration() {
        user = new User("ssh_reg@yandex.ru", "123qweASD", "ssh_reg");
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
    @DisplayName("Переход по клику на «Конструктор». Из Личного кабинета. LoggedIn пользователь.")
    public void constructorButtonFromPersonalArea() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickBurgerConstructorButton();
        MainPage mainPage = new MainPage(driver);
        assertTrue("Ожидаем появления конструктора на главной странице", mainPage.isMakeBurgerSectionVisible());
    }

    @Test
    @DisplayName("Переход по клику на Лого. Из Личного кабинета. LoggedIn пользователь.")
    public void logoButtonFromPersonalArea() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickLogoButton();
        MainPage mainPage1 = new MainPage(driver);
        assertTrue("Ожидаем появления конструктора на главной странице", mainPage1.isMakeBurgerSectionVisible());
    }
}
