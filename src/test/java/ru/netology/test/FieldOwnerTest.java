package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlDataProvider;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.DataHelper.ownerInvalid;
import static ru.netology.data.SqlDataProvider.clearTables;

public class FieldOwnerTest {
    private PaymentPage paymentPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
        paymentPage = new PaymentPage();
    }

    @AfterEach
    public void cleanTables() {
        clearTables();
    }

    @DisplayName("5.1.1 Payment for the tour by debit card, last name and first name in Latin letters")
    @Test
    void shouldNotSubmitApplicationWrongFormatFullNameInLatinLetters() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("5.1.2 Payment for the tour by credit card, last name and first name in Latin letters")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatLastNameInLatinLetters() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameEn());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

     @DisplayName("5.2.1 Payment for the tour by debit card, last name and first name in Cyrillic")
    @Test
    void shouldNotSubmitApplicationWrongFormatFullNameInCyrillic() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameRu());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("5.2.2 Payment for the tour by credit card, last name and first name in Cyrillic")
    @Test
    void shouldNotSubmitApplicationCreditCardWrongFormatFullNameInCyrillic() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(DataHelper.getOwnerFullNameRu());
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("5.3.1 Payment for the tour by debit card, empty owner input")
    @Test
    void shouldNotSubmitApplicationEmptyInput() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationRequiredField();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("5.3.2 Payment for the tour by credit card, empty owner input")
    @Test
    void shouldNotSubmitApplicationCreditCardEmptyInput() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField("");
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationRequiredField();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    @DisplayName("5.4.1 Payment for the tour by debit card, invalid owner")
    @Test
    void shouldNotSubmitApplicationInvalidOwner() {
        paymentPage.openCardPaymentPage();
        paymentPage.fillOwnerField(ownerInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getPaymentStatus());
    }

    @DisplayName("5.4.2 Payment for the tour by credit card, invalid owner")
    @Test
    void shouldNotSubmitApplicationCreditCardInvalidCardOwner() {
        paymentPage.openCreditCardPaymentPage();
        paymentPage.fillOwnerField(ownerInvalid);
        fillOtherFieldsByValidInfo();

        paymentPage.shouldHaveErrorNotificationWrongFormat();
        assertNull(new SqlDataProvider().getCreditRequestStatus());
    }

    private void fillOtherFieldsByValidInfo() {
        paymentPage.fillCardNumberField(DataHelper.getCardNumberSign16());     //случайная карта
        paymentPage.fillMonthField(DataHelper.getCurrentMonth());       //текущий месяц
        paymentPage.fillYearField(DataHelper.getYear(0));       //текущий месяц
        paymentPage.fillCvcCvvField(DataHelper.getCVC_CVV());
        paymentPage.clickContinueButton();
    }
}