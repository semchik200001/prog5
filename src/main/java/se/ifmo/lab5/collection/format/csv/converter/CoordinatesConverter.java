package se.ifmo.lab5.collection.format.csv.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import se.ifmo.lab5.objects.Coordinates;

public class CoordinatesConverter extends AbstractBeanField<Coordinates, String> {
    @Override
    protected Coordinates convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value == null || value.isEmpty())
            return null;

        String[] parts = value.split(",");
        if (parts.length != 2 || !parts[0].matches("\\d+[.,]\\d+") || !parts[1].matches("\\d+[.,]\\d+"))
            throw new CsvDataTypeMismatchException("Неверный формат координат");

        Coordinates coordinates = new Coordinates();

        coordinates.setX(Double.parseDouble(parts[0]));
        coordinates.setY(Float.parseFloat(parts[1]));

        return coordinates;
    }

    @Override
    public String convertToWrite(Object value) {
        Coordinates coordinates = (Coordinates) value;
        if (coordinates == null)
            return "";
        return coordinates.getX() + "," + coordinates.getY();
    }
}
