import methods.UserMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Login;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.RegisterPage;

import java.util.concurrent.TimeUnit;

import static data.DataForUser.userInvalidRegistration;
import static data.DataForUser.userValidRegistration;

public class RegisterTest {
    User userRequest;
    RegisterPage registrationPage;
    LoginPage loginPage;
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registrationPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void cleanOut() {
        UserMethods userAction = new UserMethods();
        Login loginRequest = new Login(userRequest.getEmail(), userRequest.getPassword());
        Response response = userAction.login(loginRequest);
        String accessToken = response
                .then()
                .extract()
                .path("accessToken");

        if (accessToken != null) {
            userAction.delete(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Корректная регистрация")
    @Description("Проверка успешной регистрации с валидным паролем")
    public void validRegistration() {
        userRequest = userValidRegistration();
        loginPage.clickLoginAccountButton();
        loginPage.clickRegister();
        registrationPage.setRegisterForm(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        registrationPage.clickRegisterButton();
        registrationPage.isDisplayedLoginButton();
    }

    @Test
    @DisplayName("Некорректная регистрация")
    @Description("Нельзя зарегистрироваться с паролем меньше 6 символов")
    public void invalidRegistration() {
        userRequest = userInvalidRegistration();
        loginPage.clickLoginAccountButton();
        loginPage.clickRegister();
        registrationPage.setRegisterForm(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        registrationPage.clickRegisterButton();
        Assert.assertEquals("Некорректный пароль", registrationPage.getTextPasswordError());
    }
}