package praktikum.login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.main.BaseTest;
import praktikum.pom.ForgotPwdPage;
import praktikum.pom.LoginPage;
import praktikum.pom.MainPage;
import praktikum.pom.RegisterPage;
import praktikum.user.User;
import praktikum.user.UserClient;
import praktikum.user.UserGenerator;

import static org.junit.Assert.assertTrue;

public class TestLogin extends BaseTest {
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Ожидаем появления кнопки \"Оформить заказ\" после успешного логина.", mainPage.isMakeOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginFromPersonalArea() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAreaButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Ожидаем появления кнопки \"Оформить заказ\" после успешного логина.", mainPage.isMakeOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegistrationPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLogInLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        assertTrue("Ожидаем появления кнопки \"Оформить заказ\" после успешного логина.", mainPage.isMakeOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля.")
    public void loginFromForgotPwdPage() {
        ForgotPwdPage forgotPwdPage = new ForgotPwdPage(driver);
        forgotPwdPage.open();
        forgotPwdPage.clickLogInLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        assertTrue("Ожидаем появления кнопки \"Оформить заказ\" после успешного логина.", mainPage.isMakeOrderButtonVisible());
    }
}
