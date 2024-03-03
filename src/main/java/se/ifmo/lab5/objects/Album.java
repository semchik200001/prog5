package se.ifmo.lab5.objects;

import com.opencsv.bean.CsvBindByName;
import se.ifmo.lab5.objects.exceptions.InvalidArgumentException;
import se.ifmo.lab5.objects.exceptions.NullableFieldException;

public class Album {
    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @CsvBindByName(required = true)
    String name;

    /**
     * set name of the album with validation
     * @param name - name of the album
     * @throws NullableFieldException when argument is null
     * @throws InvalidArgumentException when argument invalid (blank)
     */
    public void setName(String name) {
        if (name == null) throw new NullableFieldException("name");
        if (name.isBlank()) throw new InvalidArgumentException("name", "строка не может быть пустой");

        this.name = name;
    }

    /**
     * get name of the album
     * @return name of the album
     */
    public String getName() {
        return name;
    }

    /**
     * Поле может быть null
     * Значение поля должно быть больше 0
     */
    @CsvBindByName
    Long sales;

    /**
     * set sales of the album
     * @throws NullableFieldException when argument is null
     */
    public void setSales(Long sales) {
        if (sales <= 0) throw new InvalidArgumentException("sales", "значение поля должно быть больше 0");

        this.sales = sales;
    }

    /**
     * get sales of the album
     * @return sales of the album
     */
    public Long getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", sales=" + sales +
                '}';
    }
}
