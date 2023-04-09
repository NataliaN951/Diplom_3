import methods.UserMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Login;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AccountPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static data.DataForUser.userValidRegistration;

public class LoginTest {
    User userRequest;
    UserMethods userMethods;
    LoginPage loginPage;
    AccountPage personalAccountPage;
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userMethods = new UserMethods();
        loginPage = new LoginPage(driver);
        personalAccountPage = new AccountPage(driver);
        userRequest = userValidRegistration();
        userMethods.create(userRequest);
    }

    @After
    public void cleanOut() {
        Login loginRequest = new Login(userRequest.getEmail(), userRequest.getPassword());
        Response response = userMethods.login(loginRequest);
        String accessToken = response
                .then()
                .extract()
                .path("accessToken");

        if (accessToken != null) {
            userMethods.delete(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Войти в аккаунт с главной страницы")
    @Description("Проверка корректного входа через кнопку \"Войти в аккаунт\" на главной странице")
    public void loginToMainPage() {
        loginPage.clickLoginAccountButton();
        loginPage.setEmail(userRequest.getEmail());
        loginPage.setPassword(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayedTextPutBurger();
    }

    @Test
    @DisplayName("Войти в аккаунт через ссылку под формой регистрации")
    @Description("Проверка корректного входа через кнопку \"Войти\" под формой регистрации")
    public void loginToRegistrationForm() {
        loginPage.clickLoginAccountButton();
        loginPage.clickRegister();
        loginPage.clickEnter();
        loginPage.setEmail(userRequest.getEmail());
        loginPage.setPassword(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayedTextPutBurger();
    }

    @Test
    @DisplayName("Войти в аккаунт через личный кабинет")
    @Description("Проверка корректного входа через форму авторизации в личном кабинете")
    public void loginToPersonalAccount() {
        personalAccountPage.clickToAccountButton();
        loginPage.setEmail(userRequest.getEmail());
        loginPage.setPassword(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayedTextPutBurger();
    }

    @Test
    @DisplayName("Войти в аккаунт через ссылку под формой восстановления пароля")
    @Description("Проверка корректного входа через кнопку Войти под формой восстановления пароля")
    public void loginToRecoverPassword() {
        personalAccountPage.clickToAccountButton();
        loginPage.clickRecoverPassword();
        loginPage.clickEnter();
        loginPage.setEmail(userRequest.getEmail());
        loginPage.setPassword(userRequest.getPassword());
        loginPage.clickLoginButton();
        loginPage.isDisplayedTextPutBurger();
    }
}