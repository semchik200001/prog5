package se.ifmo.lab5.collection.format;

import se.ifmo.lab5.objects.MusicBand;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public interface FormatWorker<K> {
    List<K> readFile(Path filePath);
    List<K> readString(String str);

    void writeFile(Collection<MusicBand> values, Path filePath);
    String writeString(List<K> values);

    void removeById(Long id, Path filePath);
}
