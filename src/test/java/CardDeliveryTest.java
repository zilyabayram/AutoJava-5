import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import util.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    public void setUpTest() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {

        DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[placeholder='Город']").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='date'] input").sendKeys(Keys.ENTER);
        $("[name='name']").setValue(validUser.getName());
        $("[name='phone']").setValue(validUser.getPhone());
        $("[class='checkbox__box']").click();
        $("[class='button__content']").click();
        $((withText("Успешно!"))).should(visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(
                Condition.text("Встреча успешно запланирована на " + firstMeetingDate),
                Duration.ofSeconds(15)
        );

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $("[data-test-id='date'] input").sendKeys(Keys.ENTER);
        $("[class='button__content']").click();

        $((withText("Необходимо подтверждение"))).should(visible, Duration.ofSeconds(15));

        $x("//button/span[contains(.,'Перепланировать')]").click();

        $((withText("Успешно!"))).should(visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(
                Condition.text("Встреча успешно запланирована на " + secondMeetingDate),
                Duration.ofSeconds(15)
        ).shouldBe(visible);
    }
}

