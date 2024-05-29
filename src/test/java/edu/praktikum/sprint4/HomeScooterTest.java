package edu.praktikum.sprint4;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class HomeScooterTest {
    private final String textArrowInButton;
    private final String textInList;
    public HomeScooterTest(String textArrowInButton,String textInList) {
        this.textArrowInButton=textArrowInButton;
        this.textInList=textInList;
    }
    @Parameterized.Parameters
    public static Object[][] getData() {
        //тестовые данные
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    private WebDriver driver;

    @Test
    public void checkTextInList() {
        //драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //перейти на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //объект для класса главной страницы
        HomePageScooter homePageScooter = new HomePageScooter(driver,textArrowInButton,textInList);
        //клик по стрелке
        homePageScooter.clickArrowInButton();
        //проверка отображения текста в раскрывающемся окне
        String textInButton = homePageScooter.textInList();
        MatcherAssert.assertThat(textInButton, is(textInList));
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
