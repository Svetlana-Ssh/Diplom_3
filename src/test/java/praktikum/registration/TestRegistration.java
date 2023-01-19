package praktikum.registration;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import praktikum.main.BaseTest;
import praktikum.pom.LoginPage;
import praktikum.pom.RegisterPage;
import praktikum.user.User;
import praktikum.user.UserClient;
import praktikum.user.UserGenerator;

import static org.junit.Assert.assertTrue;

public class TestRegistration extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String userAccessToken;

    @After
    @DisplayName("Удаление тестового аккаунта")
    public void accountCleanUp() {
        userAccessToken = userClient.getAccessTokenOnLogin(user);
        if (userAccessToken != null) {
            userClient.delete(userAccessToken);
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя.")
    public void validRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Ожидаем появления секции \"Вход\" после успешной регистрации.", loginPage.isEnterSectionVisible());
    }

    @Test
    @DisplayName("Регистрация. Возникает ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    public void registrationWithIncorrectPwdErrHndl() {
        user = userGenerator.randomBadPwd();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue("Ожидаем появления сообщения \"Некорректный пароль\" после попытки регистрации с некорректным паролем, меньше необходимых 6 символов.", registerPage.isInvalidPasswordMsgVisible());
    }
}
