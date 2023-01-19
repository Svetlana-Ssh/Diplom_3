package praktikum.main;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        // выбор браузера для прогона тестов:
        /*Yandex browser:
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriverya106.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\svetl\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
         */

        //Chrome browser:
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
