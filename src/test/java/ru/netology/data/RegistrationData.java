package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static ru.netology.data.AuthTest.requestSpec;

public class RegistrationData {

    private RegistrationData() {
    }

    public static RegistrationDto registrationUserStatusActive() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.regexify("[a-z]{6}");
        String password = faker.regexify("[a-z1-9]{6}");
        RegistrationDto user = new RegistrationDto(login, password, "active");
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
        return user;
    }

    public static RegistrationDto registrationUserStatusBlocked() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.regexify("[a-z]{6}");
        String password = faker.regexify("[a-z1-9]{6}");
        RegistrationDto user = new RegistrationDto(login, password, "blocked");
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
        return user;
    }
}
