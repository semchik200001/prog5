package se.ifmo.lab5.collection.format.csv.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import se.ifmo.lab5.objects.Album;

public class AlbumConverter extends AbstractBeanField<Album, String> {
    @Override
    protected Album convert(String str) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (str == null || str.isEmpty()) return null;

        String[] parts = str.split(",");

        if (parts.length != 2 || !parts[1].matches("\\d+"))
            throw new CsvDataTypeMismatchException("Неверный формат альбома");

        Album album = new Album();

        album.setName(parts[0]);
        album.setSales(Long.parseLong(parts[1]));

        return album;
    }

    @Override
    public String convertToWrite(Object value) {
        Album album = (Album) value;
        if (album == null)
            return "";

        return album.getName() + "," + album.getSales();
    }
}
