package praktikum.burgerconstructor;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.main.BaseTest;
import praktikum.pom.MainPage;
import java.time.Duration;

import static org.hamcrest.Matchers.containsString;

public class TestBurgerConstructorNavigation extends BaseTest {
    private MainPage mainPage;

    @Before
    public void openMainPage() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Переход к разделу Булки.")
    public void selectBuns() {
        mainPage.clickSauceButton();
        mainPage.clickBunsButton();
        MatcherAssert.assertThat(mainPage.getBunsButtonParentClass(), containsString("current"));
    }

    @Test
    @DisplayName("Переход к разделу Соусы.")
    public void selectSauces() {
        mainPage.clickSauceButton();
        MatcherAssert.assertThat(mainPage.getSaucesButtonParentClass(), containsString("current"));
    }

    @Test
    @DisplayName("Переход к разделу Начинки.")
    public void selectFillings() {
        mainPage.clickFillingsButton();
        MatcherAssert.assertThat(mainPage.getFillingsButtonParentClass(), containsString("current"));
    }

    @Test
    @DisplayName("Скролл к разделу Булки.")
    public void scrollBuns() {
        mainPage.clickFillingsButton();
        mainPage.scrollToBunsSection();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPage.getFillingsButton()));
        MatcherAssert.assertThat(mainPage.getBunsButtonParentClass(), containsString("current"));
    }

    @Test
    @DisplayName("Скролл к разделу Соусы.")
    public void scrollSauces() {
        mainPage.scrollToSaucesSection();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPage.getBunsButton()));
        MatcherAssert.assertThat(mainPage.getSaucesButtonParentClass(), containsString("current"));
    }

    @Test
    @DisplayName("Скролл к разделу Начинки.")
    public void scrollFillings() {
        mainPage.scrollToFillingsSection();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPage.getBunsButton()));
        MatcherAssert.assertThat(mainPage.getFillingsButtonParentClass(), containsString("current"));
    }
}
