package se.ifmo.lab5.chat.runner;

import se.ifmo.lab5.chat.handler.Handler;
import se.ifmo.lab5.chat.io.console.ConsoleWorker;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.objects.Album;
import se.ifmo.lab5.objects.Coordinates;
import se.ifmo.lab5.objects.MusicBand;
import se.ifmo.lab5.objects.MusicGenre;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConsoleRunner implements Runnable {
    public static final int MAX_RECURSION_DEPTH = 10;

    private final Handler handler;
    private final ConsoleWorker consoleWorker;

    private int currentRecursionDepth = 0;
    private final Deque<String> inboundQueries = new ArrayDeque<>();

    public ConsoleRunner(Handler handler, ConsoleWorker consoleWorker) {
        this.handler = handler;
        this.consoleWorker = consoleWorker;
    }

    @Override
    public void run() {
        // try load collection
        CollectionManager.getInstance().getCollection();

        String input;

        while ((input = consoleWorker.get("[%tl:%tM:%tS] ~ ")) != null) {
            currentRecursionDepth = 0;
            map(input);
        }

        consoleWorker.send("goodbye!");
    }

    private void map(String input) {
        if (currentRecursionDepth > MAX_RECURSION_DEPTH) {
            consoleWorker.send("Глубина рекурсии не может быть > " + MAX_RECURSION_DEPTH);
            inboundQueries.clear();
            return;
        }

        Request request = new Request();

        while (input.contains("element")) {
            input = input.replaceFirst("element", "");
            request.getCollection().add(inputMusicBand());
        }

        String[] parts = input.trim().split(" ", 2);

        request.setCommand(parts[0].trim());
        if (parts.length == 2) request.setText(parts[1].trim());

        Response response = handler.handle(request);

        if (!inboundQueries.isEmpty()) currentRecursionDepth++;
        inboundQueries.addAll(response.getInboundRequests());
        while (!inboundQueries.isEmpty()) map(inboundQueries.pollLast());

        if (response.getMessage() != null) consoleWorker.send(response.getMessage());
        consoleWorker.skip();
    }

    private MusicBand inputMusicBand() {
        consoleWorker.skip();
        consoleWorker.send("Новый элемент");
        consoleWorker.skip();
        consoleWorker.send("> Введите основную информацию: ");

        MusicBand musicBand = new MusicBand();
        while (!input("название", musicBand::setName, str -> str)) ;
        while (!input("кол-во участников", musicBand::setNumberOfParticipants, Integer::parseInt)) ;
        while (!input("кол-во синглов", musicBand::setSinglesCount, Long::parseLong)) ;
        while (!input("жанр " + Arrays.toString(MusicGenre.values()), musicBand::setGenre, MusicGenre::valueOf)) ;

        consoleWorker.skip();
        consoleWorker.send("> Введите информацию о координатах: ");

        Coordinates coordinates = new Coordinates();

        while (!input("x", coordinates::setX, Double::parseDouble)) ;
        while (!input("y", coordinates::setY, Float::parseFloat)) ;

        musicBand.setCoordinates(coordinates);

        consoleWorker.skip();
        consoleWorker.send("> Введите информацию о лучшем альбоме: ");

        Album album = new Album();

        while (!input("название", album::setName, str -> str)) ;
        while (!input("продажи", album::setSales, Long::parseLong)) ;

        musicBand.setBestAlbum(album);

        return musicBand;
    }

    private <K> boolean input(String fieldName, Consumer<K> setter, Function<String, K> parser) {
        try {
            setter.accept(parser.apply(consoleWorker.get(" - " + fieldName)));
            return true;
        } catch (Exception ex) {
            consoleWorker.error(ex.getMessage());
            return false;
        }
    }

}
