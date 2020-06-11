package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static ru.netology.data.AuthTest.requestSpec;

public class RegistrationData {

    private RegistrationData() {
    }

//    static class AuthTest {
//        public static RequestSpecification requestSpec = new RequestSpecBuilder()
//                .setBaseUri("http://localhost")
//                .setPort(9999)
//                .setAccept(ContentType.JSON)
//                .setContentType(ContentType.JSON)
//                .log(LogDetail.ALL)
//                .build();}

    public static RegistrationDto registrationUserStatusActive() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.regexify("[a-z]{6}");
        String password =faker.regexify("[a-z1-9]{6}");
        RegistrationDto user=new RegistrationDto(login, password, "active");
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
        String password =faker.regexify("[a-z1-9]{6}");
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(login, password, "blocked")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
        return new RegistrationDto(login,password,"blocked");
    }
}
