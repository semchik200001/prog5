package se.ifmo.lab5.collection;

import se.ifmo.lab5.objects.MusicBand;

import java.util.Date;
import java.util.Hashtable;

public class UserCollection extends Hashtable<Long, MusicBand> {
    private final Date initializedDate = new Date();

    public Date getInitializedDate() {
        return initializedDate;
    }

    public void add(MusicBand musicBand) {
            put(musicBand.getId(), musicBand);
    }

    public void set(Long id, MusicBand musicBand) {
        put(id, musicBand);
    }
}
