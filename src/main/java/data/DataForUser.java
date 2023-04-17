package data;

import io.qameta.allure.Step;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;

public class DataForUser {
    @Step("Регистрация пользователя с корректным паролем")
    public static User userValidRegistration() {
        String name = "trtr";
        String email = "trtrtr@yandex.com";
        String password = "trtrtr";
        return new User(name, email, password);
    }

    @Step("Регистрация клиента с некорректным паролем")
    public static User userInvalidRegistration() {
        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.com";
        String password = RandomStringUtils.randomAlphabetic(10);
        return new User(name, email, password);
    }
}