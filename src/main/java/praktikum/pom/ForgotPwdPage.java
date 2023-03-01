package praktikum.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPwdPage {
    private final WebDriver driver;
    private final static String URL_FORGOTPWD = "https://stellarburgers.nomoreparties.site/forgot-password";
    //Локатор кнопки "Войти" в форме восстановления пароля. https://stellarburgers.nomoreparties.site/forgot-password:
    private final By logInLink = By.xpath(".//a[text()='Войти']");
    public ForgotPwdPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get(URL_FORGOTPWD);
    }
    public void clickLogInLink() {
        driver.findElement(logInLink).click();
    }
}
