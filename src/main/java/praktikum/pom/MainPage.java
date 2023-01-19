package praktikum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;
    private final static String URL = "https://stellarburgers.nomoreparties.site/";

    //Локатор кнопки "Войти в аккаунт":
    private final By logInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //Локатор кнопки "Личный кабинет":
    private final By personalAreaButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By makeBurgerSection = By.xpath(".//h1[text()='Соберите бургер']");
    private final By bunsButton = By.xpath(".//span[text() ='Булки']");
    private final By bunsButtonParent = By.xpath(".//span[text() ='Булки']/parent::div");
    private final By sauсesButton = By.xpath(".//span[text() ='Соусы']");
    private final By sauсesButtonParent = By.xpath(".//span[text() ='Соусы']/parent::div");
    private final By fillingsButton = By.xpath(".//span[text() ='Начинки']");
    private final By fillingsButtonParent = By.xpath(".//span[text() ='Начинки']/parent::div");
    private final By bunsSection = By.xpath(".//h2[text() ='Булки']");
    private final By sauсesSection = By.xpath(".//h2[text() ='Соусы']");
    private final By fillingsSection = By.xpath(".//h2[text() ='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }
    // Метод прокрутки страницы до раздела "Булки":
    @Step
    public void scrollToBunsSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(bunsSection));
    }
    // Метод прокрутки страницы до раздела "Соусы":
    @Step
    public void scrollToSaucesSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(sauсesSection));
    }
    // Метод прокрутки страницы до раздела "Начинки":
    @Step
    public void scrollToFillingsSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(fillingsSection));
    }
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }
    public void clickPersonalAreaButton() {
        driver.findElement(personalAreaButton).click();
    }
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }
    public void clickSauceButton() {
        driver.findElement(sauсesButton).click();
    }
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }
    public WebElement getBunsButton() {
        return driver.findElement(bunsButton);
    }
    public WebElement getSaucesButton() {
        return driver.findElement(sauсesButton);
    }
    public WebElement getFillingsButton() {
        return driver.findElement(fillingsButton);
    }
    public String getBunsButtonParentClass() {
        return driver.findElement(bunsButtonParent).getDomAttribute("class");
    }
    public String getSaucesButtonParentClass() {
        return driver.findElement(sauсesButtonParent).getDomAttribute("class");
    }
    public String getFillingsButtonParentClass() {
        return driver.findElement(fillingsButtonParent).getDomAttribute("class");
    }
    //Метод проверяет, что отобразилась кнопка с названием "Оформить заказ":
    public boolean isMakeOrderButtonVisible() {
        return driver.findElements(makeOrderButton).size() > 0;
    }
    public boolean isMakeBurgerSectionVisible() {
        return driver.findElements(makeBurgerSection).size() > 0;
    }
}
