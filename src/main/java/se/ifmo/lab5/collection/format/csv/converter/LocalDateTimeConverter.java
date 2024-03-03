package se.ifmo.lab5.collection.format.csv.converter;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeConverter extends AbstractBeanField<LocalDateTime, String> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");

    @Override
    protected LocalDateTime convert(String value) {
        if (value == null || value.isEmpty())
            return null;

        try {
            return LocalDate.parse(value, dateTimeFormatter).atStartOfDay();
//            return LocalDateTime.now();
//            return LocalDateTime.ofInstant(simpleDateFormat.parse(value.trim()).toInstant(), ZoneId.systemDefault());
        } catch (Exception e) {
            System.err.println("Неверный формат даты | dd.MM.yyyy hh:mm:ss");
            e.printStackTrace();
            return LocalDateTime.now();
        }
    }

    @Override
    public String convertToWrite(Object value) {
        LocalDateTime localDateTime = (LocalDateTime) value;
        if (localDateTime == null)
            return "";
        return localDateTime.format(dateTimeFormatter);
    }
}