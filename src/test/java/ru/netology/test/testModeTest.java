package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.RegistrationData;
import ru.netology.data.RegistrationDto;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class testModeTest {
    RegistrationDto userActive = RegistrationData.registrationUserStatusActive();
    RegistrationDto userBlocked =RegistrationData.registrationUserStatusBlocked();

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
         }

    @Test
    void shouldRegistrationLoginPasswordValidStatusActive()  {
        $("[name='login']").setValue(userActive.getLogin());
        $("[name='password']").setValue(userActive.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Личный кабинет")).waitUntil(visible, 15000);
    }

    @Test
    void shouldRegistrationLoginEmptyPasswordValidStatusActive() {
        $("[name='password']").setValue(userActive.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        SelenideElement login = $("[data-test-id='login']");
        login.$("[class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordEmptyStatusActive()  {
        $("[name='login']").setValue(userActive.getLogin());
        $$("button").find(exactText("Продолжить")).click();
        SelenideElement password = $("[data-test-id='password']");
        password.$("[class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotRegistrationLoginNotValidPasswordValidStatusActive()  {
        $("[name='login']").setValue("kate");
        $("[name='password']").setValue(userActive.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordNotValidStatusActive()  {
        $("[name='login']").setValue(userActive.getLogin());
        $("[name='password']").setValue("password");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordValidStatusBlocked()  {
        $("[name='login']").setValue(userBlocked.getLogin());
        $("[name='password']").setValue(userBlocked.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Пользователь заблокирован")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginValidPasswordNotValidStatusBlocked()  {
        $("[name='login']").setValue(userBlocked.getLogin());
        $("[name='password']").setValue("password");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegistrationLoginNotValidPasswordValidStatusBlocked()  {
        $("[name='login']").setValue("kate");
        $("[name='password']").setValue(userBlocked.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }
}



