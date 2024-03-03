package se.ifmo.lab5.collection.format.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import se.ifmo.lab5.collection.format.FormatWorker;
import se.ifmo.lab5.objects.Album;
import se.ifmo.lab5.objects.Coordinates;
import se.ifmo.lab5.objects.MusicBand;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class CsvFormatWorker implements FormatWorker<MusicBand> {
    @Override
    public List<MusicBand> readFile(Path filePath) {
        if (Files.notExists(filePath)) {
            System.out.println("файл " + filePath.getFileName() + " не существует");
            return Collections.emptyList();
        }

        if (!Files.isReadable(filePath)) {
            System.out.println("файл " + filePath.getFileName() + " невозможно прочитать");
            return Collections.emptyList();
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(filePath, StandardOpenOption.READ))) {
            return new CsvToBeanBuilder<>(new InputStreamReader(inputStream))
                    .withType(MusicBand.class)
                    .withFilter(strings -> {
                        for (String one : strings)
                            if (one != null && !one.isEmpty())
                                return true;

                        return false;
                    })
                    .withExceptionHandler(e -> {
                        System.out.println("! ошибка добавления элемента: #" + e.getLineNumber() + ": " + Arrays.toString(e.getLine()) + " (" + e.getMessage() + ")");
                        return null;
                    })
                    .build().parse().stream().map(q -> {
                        try {
                            MusicBand unverified = (MusicBand) q;

                            MusicBand result = new MusicBand();

                            result.setName(unverified.getName());
                            result.setId(unverified.getId());

                            Coordinates coordinates = new Coordinates();

                            coordinates.setX(unverified.getCoordinates().getX());
                            coordinates.setY(unverified.getCoordinates().getY());

                            result.setCoordinates(unverified.getCoordinates());

                            result.setNumberOfParticipants(unverified.getNumberOfParticipants());
                            result.setSinglesCount(unverified.getSinglesCount());

                            result.setGenre(unverified.getGenre());

                            Album album = new Album();

                            album.setName(unverified.getBestAlbum().getName());
                            album.setSales(unverified.getBestAlbum().getSales());

                            result.setBestAlbum(album);

                            return result;
                        } catch (Throwable ex) {
                            System.out.println("Не удалось добавить элемент: " + ex.getMessage());
                            return null;
                        }

                    }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("ошибка при попытке чтения файла: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<MusicBand> readString(String csvContent) {
        try {
            return new CsvToBeanBuilder<>(new StringReader(csvContent))
                    .withType(MusicBand.class)
                    .build().parse().stream().map(q -> (MusicBand) q).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void writeFile(Collection<MusicBand> values, Path filePath) {
        if (Files.notExists(filePath)) {
            System.out.println("файл " + filePath.getFileName() + " не существует");
            return;
        }

        if (!Files.isWritable(filePath)) {
            System.out.println("файл " + filePath.getFileName() + " невозможно записать");
            return;
        }

        try (Writer fileWriter = new FileWriter(filePath.toFile(), false)) {
            StatefulBeanToCsv<MusicBand> beanToCsv = new StatefulBeanToCsvBuilder<MusicBand>(fileWriter)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(new ArrayList<>(values));

            System.out.println("коллекция успешно сохранена в " + filePath.getFileName());
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String writeString(List<MusicBand> values) {
        try (Writer writer = new StringWriter()) {
            StatefulBeanToCsv<MusicBand> beanToCsv = new StatefulBeanToCsvBuilder<MusicBand>(writer)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(values.stream().toList());
            return writer.toString();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    @Override
    public void removeById(Long id, Path filePath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            while (fileReader.ready()) {
                final String line = fileReader.readLine();
                if (line.startsWith("\"" + id + "\"")) continue;
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
