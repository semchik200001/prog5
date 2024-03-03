package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.collection.UserCollection;

import java.util.Locale;

public class Info extends Command {
    public Info() {
        super("info", "вывести информацию о коллекции");
    }

    @Override
    public Response execute(Request request) {
        UserCollection collection = CollectionManager.getInstance().getCollection();

        return new Response(String.format("Коллекция:%n - тип: %s%n - инициализирована: %s%n - количество элементов: %d",
                collection.getClass().getSimpleName(),collection.getInitializedDate(), collection.size()));
    }
}
