import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import static data.DataForUser.userInvalidRegistration;
import static data.DataForUser.userValidRegistration;

public class RegisterTest extends TestBase {

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
        registrationPage.setRegisterForm(userRequest.getName(), userRequest.getEmail(), "1");
        registrationPage.clickRegisterButton();
        Assert.assertEquals("Некорректный пароль", registrationPage.getTextPasswordError());
    }
}