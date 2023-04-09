import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.BurgerConstructorPage;

import java.util.concurrent.TimeUnit;

public class BurgerConstructorTest {
    BurgerConstructorPage constructorPage;
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        constructorPage = new BurgerConstructorPage(driver);
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к булкам")
    @Description("Можно выбрать вкладку Булки")
    public void bunTab() {
        constructorPage.clickBun();
        Assert.assertEquals("Булки", constructorPage.textForActiveTab());
    }

    @Test
    @DisplayName("Переход к соусам")
    @Description("Можно выбрать вкладку Соусы")
    public void sauceTab() {
        constructorPage.clickSauce();
        Assert.assertEquals("Соусы", constructorPage.textForActiveTab());
    }

    @Test
    @DisplayName("Переход к начинкам")
    @Description("Можно выбрать вкладку Начинки")
    public void fillingsTab() {
        constructorPage.clickFilling();
        Assert.assertEquals("Начинки", constructorPage.textForActiveTab());
    }

}