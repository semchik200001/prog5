package se.ifmo.lab5.collection;

import se.ifmo.lab5.collection.format.FormatWorker;
import se.ifmo.lab5.collection.format.csv.CsvFormatWorker;
import se.ifmo.lab5.objects.MusicBand;

import java.nio.file.Path;

public class FileManager {
    private static FormatWorker<MusicBand> formatWorker = new CsvFormatWorker();

    public static UserCollection loadCollection(Path path) {
        UserCollection collection = new UserCollection();
        formatWorker.readFile(path).forEach(collection::add);
        return collection;
    }

    public static void saveCollection(Path path, UserCollection collection) {
        formatWorker.writeFile(collection.values(), path);
    }
}
