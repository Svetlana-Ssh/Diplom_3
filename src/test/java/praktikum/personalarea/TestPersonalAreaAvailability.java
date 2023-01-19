package praktikum.personalarea;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.main.BaseTest;
import praktikum.pom.AccountProfilePage;
import praktikum.pom.LoginPage;
import praktikum.pom.MainPage;
import praktikum.pom.RegisterPage;
import praktikum.user.User;
import praktikum.user.UserClient;
import praktikum.user.UserGenerator;

import static org.junit.Assert.assertTrue;

public class TestPersonalAreaAvailability extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String userAccessToken;

    @Before
    @DisplayName("Регистрация тестового аккаунта")
    public void accountRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Ожидаем появления секции \"Вход\" после успешной регистрации.", loginPage.isEnterSectionVisible());
    }

    @After
    @DisplayName("Удаление тестового аккаунта")
    public void accountCleanUp() {
        userAccessToken = userClient.getAccessTokenOnLogin(user);
        if (userAccessToken != null) {
            userClient.delete(userAccessToken);
        }
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет». С главной страницы. Пользователь не залогинен.")
    public void personalAreaNotLoggedInFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAreaButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Для незалогиненного пользователя ожидаем появления формы  \"Вход\".", loginPage.isEnterSectionVisible());
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет». С главной страницы. Пользователь залогинен.")
    public void personalAreaLoggedInFromMainPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAreaButton();

        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        assertTrue("Для логиненного пользователя ожидаем появления формы  \"Профиль\".", accountProfilePage.isProfileSectionVisible());
    }
}
