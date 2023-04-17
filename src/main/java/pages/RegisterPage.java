package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By registerForm = By.xpath("//input[@class='text input__textfield text_type_main-default']");
    private final By passwordError = By.xpath("//p[text() = 'Некорректный пароль']");
    private final By loginButton = By.xpath("//button[text()='Зарегистрироваться']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setRegisterForm(String name, String email, String password) {
        driver.findElements(registerForm).get(0).sendKeys(name);
        driver.findElements(registerForm).get(1).sendKeys(email);
        driver.findElements(registerForm).get(2).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Предупреждение о некорректном пароле")
    public String getTextPasswordError() {
        return driver.findElement(passwordError).getText();
    }

    @Step("Кнопка Вход")
    public void isDisplayedLoginButton() {
        Assert.assertTrue(driver.findElement(loginButton).isDisplayed());
    }
}