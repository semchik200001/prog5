package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.objects.MusicBand;

public class InsertById extends Command {
    public InsertById() {
        super("insert", "insert ID {element} - добавить новый элемент с заданным ключом");
    }

    @Override
    public Response execute(Request request) {
        // проверки перед тем, как начать выполнение запроса
        if (request.getText() == null || request.getText().isEmpty())
            return new Response("ошибка! в запросе отсутствуют данные!");
        if (!request.getText().matches("\\d+"))
            return new Response("ошибка! запрос должен быть вида | insert ID {element}");
        if (request.getCollection().isEmpty()) return new Response("ошибка! должен быть введен хотя бы один элемент");

        // получаем id, который так же введен пользователем (подразумевает синтаксис команды, но по факту бесполезен)
        long id = Long.parseLong(request.getText());

        // получаем первый элемент из введенных пользователем
        MusicBand inputElement = request.getCollection().values().iterator().next();

        // вообще, команда insert должна вставлять по id, но это не имеет никакого смысла, ведь тогда это будет считаться заменой
        // поэтому просто добавляем новый элемент
        CollectionManager.getInstance().getCollection().add(inputElement);

        return new Response(String.format("элемент с id %d успешно добавлен %s", id, inputElement));
    }
}
