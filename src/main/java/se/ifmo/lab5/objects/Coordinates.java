package se.ifmo.lab5.objects;

import com.opencsv.bean.CsvBindByName;
import se.ifmo.lab5.objects.exceptions.InvalidArgumentException;
import se.ifmo.lab5.objects.exceptions.NullableFieldException;

public class Coordinates {
    /**
     * Значение поля должно быть больше -599
     * Поле не может быть null
     */
    @CsvBindByName(required = true)
    private Double x;

    /**
     * set x of the coordinates with validation
     * @param x - x of the coordinates
     * @throws NullableFieldException when argument is null
     * @throws InvalidArgumentException when argument invalid (less than -600)
     */
    public void setX(Double x) {
        if (x == null) throw new NullableFieldException("x");
        if (x <= -600) throw new InvalidArgumentException("x", "значение поля должно быть больше -599");

        this.x = x;
    }

    /**
     * get x of the coordinates
     * @return x of the coordinates
     */
    public Double getX() {
        return x;
    }

    /**
     * Значение поля должно быть больше -75
     */
    @CsvBindByName
    private float y;

    /**
     * set y of the coordinates with validation
     * @param y - y of the coordinates
     * @throws InvalidArgumentException when argument invalid (less than -76)
     */
    public void setY(float y) {
        if (y <= -76) throw new InvalidArgumentException("y", "значение поля должно быть больше -75");

        this.y = y;
    }

    /**
     * get y of the coordinates
     * @return y of the coordinates
     */
    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
