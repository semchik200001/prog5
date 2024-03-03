package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.objects.MusicBand;
import se.ifmo.lab5.objects.MusicGenre;

import java.util.Arrays;
import java.util.Optional;

public class RemoveAnyByGenre extends Command {
    public RemoveAnyByGenre() {
        super("remove_any_by_genre", "remove_any_by_genre genre - удалить из коллекции один элемент, значение поля genre которого эквивалентно заданному");
    }

    @Override
    public Response execute(Request request) {
        // проверки перед тем, как начать выполнение запроса
        if (request.getText() == null || request.getText().isEmpty())
            return new Response("ошибка! в запросе отсутствуют данные!");
        if (!MusicGenre.contains(request.getText()))
            return new Response("ошибка! такого genre не существует: " + Arrays.toString(MusicGenre.values()));

        MusicGenre inputGenre = MusicGenre.valueOf(request.getText());

        Optional<MusicBand> toRemove = CollectionManager.getInstance().getCollection().values()
                .stream().filter(musicBand -> musicBand.getGenre() == inputGenre).findAny();

        if (toRemove.isEmpty())
            return new Response("в коллекции отсутствует элемент с " + inputGenre);

        CollectionManager.getInstance().getCollection().remove(toRemove.get().getId());

        return new Response("Элемент с id " + toRemove.get().getId() + " удален из коллекции.");
    }
}
