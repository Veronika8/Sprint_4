package edu.praktikum.sprint4;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    private final int button;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String countDate;
    private final String color;
    private final String comment;

    public OrderScooterTest(int button,String name, String surname,String address,String metro,String phone,String date,String countDate,String color,String comment) {
        this.button=button;
        this.name = name;
        this.surname = surname;
        this.address=address;
        this.metro = metro;
        this.phone=phone;
        this.date=date;
        this.countDate=countDate;
        this.color=color;
        this.comment=comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        //тестовые данные
        return new Object[][] {
                {1,"Иван", "Иванов","Москва ул Ленина","Лубянка","89099090099","Choose суббота, 1-е июня 2024 г.","сутки","black","комментарий 1"},
                {2,"Сергей", "Сергеев","Москва ул Кутузова","Сокольники","88089099988","Choose воскресенье, 2-е июня 2024 г.","двое суток","grey","комментарий 2"},
        };
    }
    private WebDriver driver;

    @Test
    public void checkTextInList() {
        //драйвер для браузера Chrome
        //ChromeOptions options = new ChromeOptions();
        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        //options.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(options);
        driver = new FirefoxDriver(options);
        driver.getWindowHandle();
        //перейти на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //объект для класса страницы заказа
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        //клик по кнопке заказать
        orderPageScooter.clickOrderInButton(button);
        //заполнить форму Для кого самокат
        orderPageScooter.setClientForm(name,surname,address,metro,phone);
        //клик по кнопке далее на форме Для кого самокат
        orderPageScooter.clickFurtherClientInButton();
        //заполнить форму Про аренду
        orderPageScooter.setAboutOrderForm(date,countDate,color,comment);
        //клик по кнопке далее на форме Про аренду
        orderPageScooter.clickFurtherAboutOrderInButton();
        //клик по кнопке Да в окне подтверждения заказа
        orderPageScooter.clickYesInButton();

        String textInWindow = "Заказ оформлен";
        String textInWindowSec=orderPageScooter.textInList();
        MatcherAssert.assertThat(textInWindowSec,containsString(textInWindow));

    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
