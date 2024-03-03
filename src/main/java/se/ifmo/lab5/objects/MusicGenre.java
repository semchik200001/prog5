package se.ifmo.lab5.objects;

import java.util.Arrays;

public enum MusicGenre {
    PSYCHEDELIC_CLOUD_RAP,
    JAZZ,
    POP,
    POST_PUNK,
    BRIT_POP;

    public static boolean contains(String val) {
        return Arrays.stream(values()).anyMatch(temp -> String.valueOf(temp).equalsIgnoreCase(val));
    }
}
