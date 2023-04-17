package methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Login;
import model.User;

import static io.restassured.RestAssured.given;

public class UserMethods {
    String baseUri = "http://stellarburgers.nomoreparties.site/api/";

    @Step("Регистрация пользователя")
    public void create(User userRequest) {
        given()
                .header("Content-type", "application/json")
                .baseUri(baseUri)
                .body(userRequest)
                .post("auth/register");
    }

    @Step("Авторизация пользователя")
    public Response login(Login loginRequest) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(baseUri)
                .body(loginRequest)
                .post("auth/login");
    }

    @Step("Удаление пользователя")
    public void delete(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .baseUri(baseUri)
                .delete("auth/user");
    }
}