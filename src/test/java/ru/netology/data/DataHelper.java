package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static final String cardNumberApproved = "4444 4444 4444 4441";
    public static final String cardNumberDeclined = "4444 4444 4444 4442";
    public static final String cardNumberAll0 = "0000 0000 0000 0000";
    public static final String cardNumberInvalid = "-/2465fb,4&‘+";
    public static final String monthAndYearNumbersIsAll0 = "00";
    public static final String nonExistentMonthNumber = "13";
    public static final String monthNumberInvalid = "*8";
    public static final String longMonthInvalid = "011";
    public static final String yearNumberInvalid = "-}";
    public static final String ownerInvalid = "/*lm)";
    public static final String cvcCvvIsAll0 = "000";
    public static final String cvcCvvInvalid = "/*J_";
    public static final String longCvcCvvNumber = "5149";

    public static String getCardNumberSign15() {
        Faker faker = new Faker();
        return faker.number().digits(15);
    }

    public static String getCardNumberSign16() {
        Faker faker = new Faker();
        return faker.number().digits(16);
    }

    public static String getCardNumberSign17() {
        Faker faker = new Faker();
        return faker.number().digits(17);
    }

    public static String getCurrentMonth() {
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String getOneNumber() {
        Faker faker = new Faker();
        return faker.number().digits(1);
    }

    public static String getTwoNumber() {
        Faker faker = new Faker();
        return faker.number().digits(2);
    }

    public static String getYear(int shiftYears) {
        return LocalDate.now().plusYears(shiftYears).format(DateTimeFormatter.ofPattern("yy"));

    }

    public static String getFullYearNumber(int shiftYears) {
        return LocalDate.now().plusYears(shiftYears).format(DateTimeFormatter.ofPattern("yyyy"));
    }

    public static String getOwnerFullNameEn() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String getOwnerFullNameRu() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName();
    }

    public static String getCVC_CVV() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }
}
