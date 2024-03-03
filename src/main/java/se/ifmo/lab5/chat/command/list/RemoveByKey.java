package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.objects.MusicBand;

public class RemoveByKey extends Command {
    public RemoveByKey() {
        super("remove_key", "remove_key ID - удалить элемент из коллекции по его ключу");
    }

    @Override
    public Response execute(Request request) {
        // проверки перед тем, как начать выполнение запроса
        if (request.getText() == null || request.getText().isEmpty())
            return new Response("ошибка! в запросе отсутствуют данные!");
        if (!request.getText().matches("\\d+"))
            return new Response("ошибка! запрос должен быть вида | remove_key ID");

        CollectionManager collectionManager = CollectionManager.getInstance();

        // получаем id элемента с пользовательского ввода
        long id = Long.parseLong(request.getText());

        // проверяем, есть ли такой id в коллекции
        if (!collectionManager.getCollection().contains(id))
            return new Response("ошибка! кажется, в коллекции нету элемента с id " + id);

        // удаляем элемент с коллекции
        collectionManager.getCollection().remove(id);

        // возвращаем сообщение об успешном удалении элемента
        return new Response(String.format("Элемент с id %d успешно удален.", id));
    }
}
