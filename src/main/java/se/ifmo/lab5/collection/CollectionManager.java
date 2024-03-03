package se.ifmo.lab5.collection;

import se.ifmo.lab5.Configuration;

import java.nio.file.Path;

public class CollectionManager {
    private UserCollection collection = null;

    private CollectionManager() {}

    private static CollectionManager instance = null;

    public static CollectionManager getInstance() {
        if (instance == null) instance = new CollectionManager();
        return instance;
    }

    public UserCollection getCollection() {
        if (collection == null) load(Configuration.ROOT_FILE);
        return collection;
    }

    public void save() {
        save(Configuration.ROOT_FILE);
    }

    public void save(Path path) {
        FileManager.saveCollection(path, collection);
    }

    public UserCollection load() {
        return load(Configuration.ROOT_FILE);
    }

    public UserCollection load(Path filePath) {
        return load(filePath, false);
    }

    public UserCollection load(Path filePath, boolean append) {
        UserCollection loaded = FileManager.loadCollection(filePath);

        if (append) loaded.values().forEach(collection::add);
        else collection = loaded;

        return collection;
    }
}
