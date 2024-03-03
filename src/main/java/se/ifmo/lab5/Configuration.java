package se.ifmo.lab5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Configuration {
    public static final Path ROOT_FILE = Path.of("collection.csv");

    static {
        if (Files.notExists(ROOT_FILE)) {
            try {
                Files.createFile(ROOT_FILE);
            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
