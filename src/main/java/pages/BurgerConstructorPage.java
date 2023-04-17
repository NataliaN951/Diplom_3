package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerConstructorPage {
    private final By textPutBurger = By.xpath("//h1[text()='Соберите бургер']");
    private final WebDriver driver;
    private final By logoLink = By.className("AppHeader_header__logo__2D0X2");
    private final By constructorLink = By.xpath("//p[text()='Конструктор']");
    private final By activeTab = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default']");
    private final By burgerIngredients = By.className("BurgerIngredients_ingredients__list__2A-mT");
    private final By sauceTab = By.xpath("//span[text()='Соусы']");


    public BurgerConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на логотип")
    public void clickLogo() {
        driver.findElement(logoLink).click();
    }

    @Step("Кликнуть на конструктор")
    public void clickBurgerConstructor() {
        driver.findElement(constructorLink).click();
    }

    @Step("Текст Соберите бургер")
    public void textForBurger() {
        Assert.assertTrue(driver.findElement(textPutBurger).isDisplayed());
    }

    @Step("Текст активной вкладки")
    public String textForActiveTab() {
        return driver.findElement(activeTab).getText();
    }

    @Step("Нажать на булки")
    public void clickBun() {
        driver.findElements(burgerIngredients).get(0).click();
    }

    @Step("Нажать на соусы")
    public void clickSauce() {
        driver.findElement(sauceTab).click();
    }

    @Step("Нажать на начинки")
    public void clickFilling() {
        driver.findElements(burgerIngredients).get(2).click();
    }

}