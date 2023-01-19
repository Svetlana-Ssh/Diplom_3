package praktikum.pom;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private final static String URL_REGISTER = "https://stellarburgers.nomoreparties.site/register";

    //Локатор кнопки "Войти" в форме регистрации https://stellarburgers.nomoreparties.site/register:
    private final By logInLink = By.xpath(".//a[text()='Войти']");
    //Локатор секции "Регистрация":
    private final By registrationSection = By.xpath(".//h2[text()='Регистрация']");
    //Локатор поля "Имя":
    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    //Локатор поля "Email":
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    //Локатор поля "Пароль":
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    //Локатор кнопки "Зарегистрироваться":
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Локатор раздела "Некорректный пароль":
    private final By invalidPasswordText = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод открывает страницу регистрации:
    public void open() {
        driver.get(URL_REGISTER);
    }
    //Методы заполняют поля для регистрации:
    public void inputName(String text) {
        driver.findElement(nameInput).sendKeys(text);
    }
    public void inputEmail(String text) {
        driver.findElement(emailInput).sendKeys(text);
    }
    public void inputPassword(String text) {
        driver.findElement(passwordInput).sendKeys(text);
    }
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public void clickLogInLink() {
        driver.findElement(logInLink).click();
    }
    //Метод проверяет, что отобразилось сообщение "Некорректный пароль":
    @Step
    @DisplayName("Проверяем, что отобразилось сообщение \"Некорректный пароль\".")
    public boolean isInvalidPasswordMsgVisible() {
        return driver.findElements(invalidPasswordText).size() > 0;
    }
    // Метод регистрации в приложении: объединяет ввод Имени, email, Пароля и клик по кнопке. Объединение отдельных методов в шаг.
    @Step
    public void register(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickRegistrationButton();
    }
}
