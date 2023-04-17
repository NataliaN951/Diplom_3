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
import pages.BurgerConstructorPage;
import pages.LoginPage;
import pages.RegisterPage;

import java.util.concurrent.TimeUnit;

import static data.DataForUser.userValidRegistration;

public class AccountTest {
    User user;
    UserMethods userMethods;
    Login login;
    RegisterPage registerPage;
    LoginPage loginPage;
    BurgerConstructorPage constructorPage;
    AccountPage accountPage;
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        user = userValidRegistration();
        userMethods = new UserMethods();
        login = new Login(user.getEmail(), user.getPassword());
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        constructorPage = new BurgerConstructorPage(driver);
        accountPage = new AccountPage(driver);

        userMethods.create(user);
        loginPage.clickLoginAccountButton();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
    }

    @After
    public void clean() {
        Login loginRequest = new Login(user.getEmail(), user.getPassword());
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
    @DisplayName("Переход к Личному кабинету")
    @Description("Переход по клику в Личный кабинет")
    public void goToAccount() {
        accountPage.clickToAccountButton();
        accountPage.infoAboutAccount();

    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода из аккаунта")
    public void logOutAccount() {
        accountPage.clickToAccountButton();
        accountPage.clickToLogOut();
        loginPage.isDisplayLogin();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Переход из личного кабинета в конструктор по клику на Конструктор")
    public void goToConstructor() {
        accountPage.clickToAccountButton();
        constructorPage.clickBurgerConstructor();
        constructorPage.textForBurger();
    }

    @Test
    @DisplayName("Переход из личного аккаунта в конструктор по клику на лого")
    @Description("Перехода из личного аккаунта в конструктор по клику на лого Stellar Burgers")
    public void goToConstructorLogo() {
        accountPage.clickToAccountButton();
        constructorPage.clickLogo();
        constructorPage.textForBurger();
    }
}