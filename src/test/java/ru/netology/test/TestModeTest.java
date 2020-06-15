package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.RegistrationData;
import ru.netology.data.RegistrationDto;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.RegistrationData.*;

public class TestModeTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRegistrationLoginPasswordValidStatusActive() {
        RegistrationDto userActive = registrationUserStatusActive();
        $("[name='login']").setValue(userActive.getLogin());
        $("[name='password']").setValue(userActive.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Личный кабинет")).waitUntil(visible, 15000);
    }

    @Test
    void shouldRegistrationLoginEmptyPasswordValidStatusActive() {
        RegistrationDto userActive = registrationUserStatusActive();
        $("[name='password']").setValue(userActive.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        SelenideElement login = $("[data-test-id='login']");
        login.$("[class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordEmptyStatusActive() {
        RegistrationDto userActive = registrationUserStatusActive();
        $("[name='login']").setValue(userActive.getLogin());
        $$("button").find(exactText("Продолжить")).click();
        SelenideElement password = $("[data-test-id='password']");
        password.$("[class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotRegistrationLoginNotValidPasswordValidStatusActive() {
        RegistrationDto userActive = registrationUserStatusActive();
        RegistrationDto userNotValid= registrationUserNotValid();
        $("[name='login']").setValue(userNotValid.getLogin());
        $("[name='password']").setValue(userActive.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordNotValidStatusActive() {
        RegistrationDto userActive = registrationUserStatusActive();
        RegistrationDto userNotValid= registrationUserNotValid();
        $("[name='login']").setValue(userActive.getLogin());
        $("[name='password']").setValue(userNotValid.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordValidStatusBlocked() {
        RegistrationDto userBlocked = registrationUserStatusBlocked();
        $("[name='login']").setValue(userBlocked.getLogin());
        $("[name='password']").setValue(userBlocked.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Пользователь заблокирован")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordNotValidStatusBlocked() {
        RegistrationDto userBlocked = registrationUserStatusBlocked();
        RegistrationDto userNotValid= registrationUserNotValid();
        $("[name='login']").setValue(userBlocked.getLogin());
        $("[name='password']").setValue(userNotValid.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginNotValidPasswordValidStatusBlocked() {
        RegistrationDto userBlocked = registrationUserStatusBlocked();
        RegistrationDto userNotValid= registrationUserNotValid();
        $("[name='login']").setValue(userNotValid.getLogin());
        $("[name='password']").setValue(userBlocked.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }
}



