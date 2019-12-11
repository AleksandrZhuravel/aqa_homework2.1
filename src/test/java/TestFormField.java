import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestFormField {
   @Test
   @DisplayName("Should show confirmation message if all fields completed correctly")
    public void positiveRequest() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        form.$("[data-test-id = name] input").setValue("Иванов Иван");
        form.$("[data-test-id = phone] input").setValue("+79270000000");
        form.$(By.className("checkbox__box")).click();
        form.$(By.className("button__content")).click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена!" +
                " Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    @DisplayName("Should show error message if name-field completed incorrectly(Latin letters)")
    public void negativeRequestLatinName() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        form.$("[data-test-id = name] input").setValue("Ivanov Ivan");
        form.$("[data-test-id = phone] input").setValue("+79270000000");
        form.$(By.className("checkbox__box")).click();
        form.$(By.className("button__content")).click();
        $(By.className("input__sub")).shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы."));

    }
        @Test
        @DisplayName("Should show error message if name-field left empty")
        public void negativeRequestNoName () {
            open("http://localhost:9999");
            SelenideElement form = $(By.className("App_appContainer__3jRx1"));
            form.$("[data-test-id = phone] input").setValue("+79270000000");
            form.$(By.className("checkbox__box")).click();
            form.$(By.className("button__content")).click();
            $(By.className("input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));

        }
        @Test
        @DisplayName("Should show error message if phone-field completed incorrectly(letters)")
        public void negativeRequestLettersInPhone () {
            open("http://localhost:9999");
            SelenideElement form = $(By.className("App_appContainer__3jRx1"));
            form.$("[data-test-id = name] input").setValue("Иванов Иван");
            form.$("[data-test-id = phone] input").setValue("Иванов Иван");
            form.$(By.className("checkbox__box")).click();
            form.$(By.className("button__content")).click();
            $(By.xpath("//div/form/div[2]/span/span/span[3]")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

        }
        @Test
        @DisplayName("Should show error message if phone-field left empty")
        public void negativeRequestNoPhone () {
            open("http://localhost:9999");
            SelenideElement form = $(By.className("App_appContainer__3jRx1"));
            form.$("[data-test-id = name] input").setValue("Иванов Иван");
            form.$(By.className("checkbox__box")).click();
            form.$(By.className("button__content")).click();
            $(By.xpath("//div[2]//span[3]")).shouldHave(exactText("Поле обязательно для заполнения"));

        }
    }








