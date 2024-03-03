package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.objects.MusicBand;

import java.util.List;
import java.util.Map;

public class RemoveLower extends Command {
    public RemoveLower() {
        super("remove_greater", "remove_greater {element} - удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public Response execute(Request request) {
        // проверки перед тем, как начать выполнение запроса
        if (request.getText() == null || request.getText().isEmpty())
            return new Response("ошибка! в запросе отсутствуют данные!");
        if (!request.getText().matches("\\d+"))
            return new Response("ошибка! запрос должен быть вида | insert ID {element}");
        if (request.getCollection().isEmpty()) return new Response("ошибка! должен быть введен хотя бы один элемент");

        // получим инстанс менеджера коллекции
        CollectionManager collectionManager = CollectionManager.getInstance();

        // получим элемент, который ввел пользователь, а так же с которым мы будем сравнивать
        MusicBand inputElement = request.getCollection().values().iterator().next();

        // список id, которые подвергнуться удалению
        List<Long> idsToRemove = collectionManager.getCollection().entrySet().stream()
                .filter(e -> e.getValue().compareTo(inputElement) <= 0)
                .map(Map.Entry::getKey).toList();

        // удалим их с коллекции
        idsToRemove.forEach(collectionManager.getCollection()::remove);

        // вернем сообщение об успешном удалении
        return new Response(String.format("ID %s успешно удалены", idsToRemove));
    }
}
