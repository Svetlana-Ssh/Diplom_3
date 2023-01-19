package praktikum.registration;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.main.BaseTest;
import praktikum.pom.LoginPage;
import praktikum.pom.RegisterPage;
import praktikum.user.User;
import praktikum.user.UserClient;

import static org.junit.Assert.assertTrue;

public class TestRegistration extends BaseTest {
    private User user;
    private final UserClient userClient = new UserClient();
    private String userAccessToken;

    @Test
    @DisplayName("Проверка успешной регистрации пользователя.")
    public void validRegistration() {
        user = new User("ssh_reg@yandex.ru", "123qweASD", "ssh_reg");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Ожидаем появления секции \"Вход\" после успешной регистрации.", loginPage.isEnterSectionVisible());

        //Если регистрация прошла успешно, то через API удаляем пользователя в конце теста.
        userAccessToken = userClient.getAccessTokenOnLogin(user);
        if (userAccessToken != null) {
            userClient.delete(userAccessToken);
        }
    }

    @Test
    @DisplayName("Регистрация. Возникает ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    public void registrationWithIncorrectPwdErrHndl() {
        user = new User("ssh_BadPwdreg@yandex.ru", "123", "ssh_BadPwd");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue("Ожидаем появления сообщения \"Некорректный пароль\" после попытки регистрации с некорректным паролем, меньше необходимых 6 символов.", registerPage.isInvalidPasswordMsgVisible());
    }
}
