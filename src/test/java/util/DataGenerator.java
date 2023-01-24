package util;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {

    private DataGenerator() {
    }

    public static Faker faker = new Faker(new Locale("ru"));

    @Value
    public static class UserInfo {
        String name;
        String phone;
        String city;
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String[] selectCity = {
            "Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик"
            , "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск",
            "Якутск"
            , "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул",
            "Чита"
            , "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток",
            "Ставрополь"
            , "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир",
            "Волгоград"
            , "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров",
            "Кострома"
            , "Курган", "Курск", "Гатчина", "Липецк", "Магадан", "Красногорск", "Мурманск",
            "Нижний Новгород"
            , "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков",
            "Ростов-на-Дону"
            , "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов",
            "Тверь"
            , "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль", "Москва",
            "Санкт-Петербург"
            , "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"
    };

    public static String generateName(String locale) {

        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    public static String generatePhone(String locale) {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            int i = (int) Math.floor(Math.random() * selectCity.length);
            UserInfo user = new UserInfo(selectCity[i], generateName(locale), generatePhone(locale));
            return user;
        }
    }
}

