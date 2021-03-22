package com.example.retenalCar.infra.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class TimeUtils {
    private TimeUtils() {

    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

    public static LocalDate toLocalDate(String date) {

        return LocalDate.parse(date, FORMATTER);
    }
}
