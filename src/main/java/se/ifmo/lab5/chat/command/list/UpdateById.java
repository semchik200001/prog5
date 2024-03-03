package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.objects.MusicBand;

public class UpdateById extends Command {
    public UpdateById() {
        super("update", "update id {element} - обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public Response execute(Request request) {
        // проверки перед тем, как начать выполнение запроса
        if (request.getText() == null || request.getText().isEmpty())
            return new Response("ошибка! в запросе отсутствуют данные!");
        if (!request.getText().matches("\\d+"))
            return new Response("ошибка! запрос должен быть вида | insert ID {element}");
        if (request.getCollection().isEmpty()) return new Response("ошибка! должен быть введен хотя бы один элемент");

        // получаем id с запроса
        long id = Long.parseLong(request.getText());

        // получаем первый элемент из введенных пользователем
        MusicBand inputElement = request.getCollection().values().iterator().next();

        // обновляем элемент по данному id
        CollectionManager.getInstance().getCollection().set(id, inputElement);

        // возвращаем сообщение об успешном обновлении элемента
        return new Response(String.format("элемент с id %d успешно обновлен", id));
    }
}
