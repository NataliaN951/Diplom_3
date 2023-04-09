package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public final By textPutBurger = By.xpath("//h1[text()='Соберите бургер']");
    private final WebDriver driver;
    private final By loginAccButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By loginRegistration = By.xpath("//a[text()='Войти']");
    private final By recoverPassword = By.xpath("//a[text()='Восстановить пароль']");
    private final By loginLink = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickLoginAccountButton() {
        driver.findElement(loginAccButton).click();
    }

    @Step("Заполнить пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Заполнить емейл")
    public void setEmail(String name) {
        driver.findElement(emailField).sendKeys(name);
    }

    @Step("Клик на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик на  Зарегистрироваться")
    public void clickRegister() {
        driver.findElement(registerLink).click();
    }

    @Step("Клик на ссылку Войти")
    public void clickEnter() {
        driver.findElement(loginRegistration).click();
    }

    @Step("Клик на ссылку для восстановления пароля")
    public void clickRecoverPassword() {
        driver.findElement(recoverPassword).click();
    }

    @Step("Кнопка для входа")
    public void isDisplayLogin() {
        Assert.assertTrue(driver.findElement(loginLink).isDisplayed());
    }

    @Step("Текст Соберите бургер")
    public void isDisplayedTextPutBurger() {
        Assert.assertTrue(driver.findElement(textPutBurger).isDisplayed());
    }
}