package com.demo.service.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public final class FakeDataService {

    public FakeDataService() {
    }

    private static final String[] firstName = {"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Đặng", "Bùi"};
    private static final String[] middleName = {"Văn", "Thị", "Hữu", "Đức", "Thuỳ", "Đình", "Minh", "Như", "Công", "Thành"};
    private static final String[] lastName = {"Anh", "Bình", "Chi", "Cường", "Dương", "Giang", "Hải", "Hạnh", "Hùng", "Khánh"};

    public static String randomName() {
        Random rand = new Random();
        String name = firstName[rand.nextInt(firstName.length)] + " " + middleName[rand.nextInt(middleName.length)] + " " + lastName[rand.nextInt(lastName.length)];
        return name;
    }

    public static Date randomDateOfBirth() {
        Random rand = new Random();
        int minDay = (int) LocalDate.of(1950, Month.JANUARY, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2005, Month.DECEMBER, 31).toEpochDay();
        long randomDay = minDay + rand.nextInt(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String randomPhoneNumber() {
        Random random = new Random();
        String phoneNumber = "0" + (random.nextInt(9) + 1);
        for (int i = 0; i < 8; i++) {
            phoneNumber += random.nextInt(10);
        }
        return phoneNumber;
    }

    public static String randomGender() {
        Random random = new Random();
        String[] genders = {"Nam", "Nữ", "Khác"};
        int index = random.nextInt(genders.length);
        return genders[index];
    }
}
