package edu.praktikum.sprint4;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;

public class HomeScooterTest {
    private WebDriver driver;

    @Test
    public void checkTextInList() {
        //драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        //перейти на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //объект для класса главной страницы
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        //клик по стрелке
        homePageScooter.clickArrowInButton();
        //проверка отображения текста в раскрывающемся окне
        String textInButton = homePageScooter.textInList();
        String textInList = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        MatcherAssert.assertThat(textInButton, is(textInList));
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
