import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import methods.UserMethods;
import model.Login;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.RegisterPage;

import java.util.concurrent.TimeUnit;

public class TestBase {
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
}
