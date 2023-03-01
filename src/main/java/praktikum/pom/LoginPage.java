package praktikum.pom;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final static String URL_REGISTER = "https://stellarburgers.nomoreparties.site/login";

    //Локатор секции "Вход":
    private final By enterSection = By.xpath(".//h2[text()='Вход']");
    //Локатор поля "Email":
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    //Локатор поля "Пароль":
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By logInButton = By.xpath(".//button[text()='Войти']");
    private final By forgotPwdLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод проверяет, что отображается секция "Вход":
    @Step
    @DisplayName("Проверяем, что отобразилась форма \"Вход\".")
    public boolean isEnterSectionVisible() {
        return driver.findElements(enterSection).size() > 0;
    }
    public void inputEmail(String text) {
        driver.findElement(emailInput).sendKeys(text);
    }
    public void inputPassword(String text) {
        driver.findElement(passwordInput).sendKeys(text);
    }
    public void clickLoginButton() {
        driver.findElement(logInButton).click();
    }
    // Метод логина в приложении: объединяет ввод email и Пароля и клик по кнопке. Объединение отдельных методов в шаг.
    public void open() {
        driver.get(URL_REGISTER);
    }
    @Step
    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        clickLoginButton();
    }
}
