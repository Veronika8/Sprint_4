package edu.praktikum.sprint4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageScooter {
    private WebDriver driver;
    //кнопка для закрытия куки
    private By cookieInButtom = By.xpath(".//button[@class='App_CookieButton__3cvqF']");
    //кнопка заказать вверху страницы
    private By orderTopInButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //кнопка заказать внизу
    private By orderBottomInButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //поле Имя
    private By nameField =
            By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Имя']");
    //поле Фамилия
    private By surnameField =
            By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Фамилия']");
    //поле адресс
    private By addressField =
            By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    //список метро
    private By selectMetro = By.xpath(".//div[@class='select-search__select']");
    //поле метро
    private By metroField =
            By.xpath(".//input[@class='select-search__input' and @placeholder='* Станция метро']");
    //станция метро
    private By stationMetro = By.xpath(".//div[@class='Order_Text__2broi']");
    //поле телефон
    private By phoneField =
            By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private By furtherField =
            By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Поле Когда привезти
    private By dateField =
            By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Когда привезти самокат']");
    //календарь
    private By calendar = By.xpath(".//div[@class='react-datepicker__month-container']");
    //дата
    private By dataInCalendar = By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--002 react-datepicker__day--weekend react-datepicker__day--outside-month']");
    //поле Срок аренды
    private By rentalPeroidInButton = By.xpath(".//div[@class='Dropdown-control']");
    //пункт в селекте Срок аренды
    private By peroidOptionInButton = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    //поле цвет самоката
    private By colorScooterInButton = By.xpath(".//input[@class='Checkbox_Input__14A2w' and @id='black']");
    //поле Комментарий для курьера
    private By commentInBField =
            By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='Комментарий для курьера']");
    //кнопка заказать на форме Про аренду
    private By orderOnPageOrderInButton =
            By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //кнопка Да
    private By confirmationOrderInButton =
            By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //окно успешной оплаты
    private By successWindow = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    public OrderPageScooter(WebDriver driver) {
        this.driver=driver;
    }
    //клик по кнопке Заказать вверху страницы
    public void clickOrderInButton(int button) {
        driver.findElement(cookieInButtom).click(); //закрываем куки уведомление
        if(button==1) {
            driver.findElement(orderTopInButton).click();
        }
        else {
            WebElement element = driver.findElement(orderBottomInButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(orderBottomInButton).click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }
    //заполнить форму Для кого самокат
    public void setClientForm(String name, String surname,String address, String metro, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroField).click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(selectMetro));
        driver.findElement(By.xpath(".//div[@class='Order_Text__2broi' and text()='"+metro+"']")).click();
        driver.findElement(phoneField).sendKeys(phone);
    }
    //клик по кнопке далее на форме Для кого самокат
    public void clickFurtherClientInButton() {
        driver.findElement(furtherField).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(dateField));
    }
    //заполнить форму Про аренду
    public void setAboutOrderForm(String date, String period, String color, String comment) {
        driver.findElement(dateField).click();
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(calendar));
        driver.findElement(By.xpath(".//div[@aria-label='"+date+"']")).click();
        driver.findElement(rentalPeroidInButton).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='"+period+"']")).click();
        driver.findElement(By.xpath(".//input[@class='Checkbox_Input__14A2w' and @id='"+color+"']")).click();
        driver.findElement(commentInBField).sendKeys(comment);
    }
    //клик по кнопке далее на форме Про аренду
    public void clickFurtherAboutOrderInButton() {
        driver.findElement(orderOnPageOrderInButton).click();
    }

    //клик по кнопке Да в окне подтверждения заказа
    public void clickYesInButton() {
        driver.findElement(confirmationOrderInButton).click();
    }
    //метод для получения заголовка в окне подтверждения заказа
    public String textInList() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(successWindow));
        return driver.findElement(successWindow).getText();
    }
}
