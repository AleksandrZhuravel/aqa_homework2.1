import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestFormField {
    @Test
    public void shouldSubmintRequest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        form.$("[data-test-id = name] input").setValue("Иванов Иван");
        form.$("[data-test-id = phone] input").setValue("+79270000000");
        form.$(By.className("checkbox__box")).click();
        form.$(By.className("button__content")).click();
        $("[data-test-id = order-success").shouldHave(exactText("Ваша заявка успешно отправлена!" +
                " Наш менеджер свяжется с вами в ближайшее время."));
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
