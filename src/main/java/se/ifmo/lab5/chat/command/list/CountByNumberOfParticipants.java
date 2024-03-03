package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;

public class CountByNumberOfParticipants extends Command {
    public CountByNumberOfParticipants() {
        super("count_by_number_of_participants", "count_by_number_of_participants numberOfParticipants - вывести количество элементов, значение поля numberOfParticipants которых равно заданному");
    }

    @Override
    public Response execute(Request request) {
        // проверки перед тем, как начать выполнение запроса
        if (request.getText() == null || request.getText().isEmpty())
            return new Response("ошибка! в запросе отсутствуют данные!");
        if (!request.getText().matches("\\d+"))
            return new Response("ошибка! запрос должен быть вида | count_by_number_of_participants {numberOfParticipants}");

        int inputNumberOfParticipants = Integer.parseInt(request.getText());

        // Stream API
        return new Response(String.format("Количество музыкальных групп с кол-вом участников %d: %d",
                inputNumberOfParticipants, CollectionManager.getInstance().getCollection()
                        .values().stream().filter(musicBand -> musicBand.getNumberOfParticipants() == inputNumberOfParticipants)
                        .count()));
    }
}
