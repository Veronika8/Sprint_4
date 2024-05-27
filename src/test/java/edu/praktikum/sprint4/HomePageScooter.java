package edu.praktikum.sprint4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageScooter {
    private WebDriver driver;
    //стрелка в разделе вопросы о важном
    private By arrowInButton = By.xpath(".//div[@id='accordion__heading-0' and @role='button']");
    //текст в разделе вопросы о важном
    private By textInList = By.xpath(".//div[@id='accordion__panel-0' and @role='region']");

    public HomePageScooter(WebDriver driver) {
        this.driver=driver;
    }

    //метод для проверки текста в списке "Вопросы о важном"
    public void clickArrowInButton() {
         WebElement element = driver.findElement(arrowInButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(arrowInButton).click();
    }
    //метод для получения текста в раскрывающемся списке "Вопросы о важном"
    public String textInList() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textInList));
        return driver.findElement(textInList).getText();
    }
}