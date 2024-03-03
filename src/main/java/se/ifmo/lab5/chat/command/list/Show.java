package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.collection.UserCollection;

import java.util.stream.Collectors;

public class Show extends Command {
    public Show() {
        super("show", "все элементы коллекции");
    }

    @Override
    public Response execute(Request request) {
        if (CollectionManager.getInstance().getCollection().isEmpty())
            return new Response("в коллекции отсутствуют элементы.");

        return new Response(CollectionManager.getInstance().getCollection()
                .values().stream().map(musicBand -> "\n" + musicBand.toString())
                .collect(Collectors.joining()));
    }
}
