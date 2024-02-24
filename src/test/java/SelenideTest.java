import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {
    private DateFormGenerator dateText = new DateFormGenerator();

    @Test
    public void testValid() {
        open("http://localhost:9999/");

        $("[data-test-id ='city'] input").setValue("Москва");
        $("[data-test-id ='name'] input").setValue("Иван Перов");
        $("[data-test-id ='date'] input").setValue(dateText.date());
        $(".input__inner").click();
        $("[data-test-id ='phone'] input").setValue("+79999999999");
        $("[data-test-id ='agreement']").click();
        $(".button__content").click();

        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15010));

        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateText.date()));
    }
}
