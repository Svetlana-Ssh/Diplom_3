package praktikum.pom;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountProfilePage {
    private final WebDriver driver;
    private final By profileSection = By.xpath(".//a[text()='Профиль']");
    private final By burgerConstructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By logOutButton = By.xpath(".//button[text()='Выход']");

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public By getProfileSection() {
        return profileSection;
    }
    // Метод проверяет, что отображается секция "Профиль":
    @Step
    @DisplayName("Проверяем, что отобразилась форма \"Профиль\".")
    public boolean isProfileSectionVisible() {
        return driver.findElements(profileSection).size() > 0;
    }
    public void clickBurgerConstructorButton() {
        driver.findElement(burgerConstructorButton).click();
    }
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}
