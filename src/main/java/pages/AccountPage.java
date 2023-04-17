package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private final WebDriver driver;
    private final By personalAccountLink = By.xpath(".//*[text() = 'Личный Кабинет']");
    private final By logOutLink = By.xpath("//button[text()='Выход']");
    private final By textAboutAccount = By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickToAccountButton() {
        driver.findElement(personalAccountLink).click();
    }

    @Step("Информация о личном кабинете")
    public void infoAboutAccount() {
        Assert.assertTrue(driver.findElement(textAboutAccount).isDisplayed());
    }

    @Step("Кликпо кнопке Выход")
    public void clickToLogOut() {
        driver.findElement(logOutLink).click();
    }
}