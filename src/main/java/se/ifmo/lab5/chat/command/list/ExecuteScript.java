package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script", "execute_script filename - считать и исполнить скрипт из указанного файла.");
    }

    @Override
    public Response execute(Request request) {
        // проверки перед тем, как начать выполнение запроса
        if (request.getText() == null || request.getText().isEmpty())
            return new Response("ошибка! в запросе отсутствуют данные!");
        if (Files.notExists(Paths.get(request.getText())))
            return new Response("ошибка! введенный файл не существует");

        Deque<String> inboundRequests = new ArrayDeque<>();

        try(BufferedReader fileReader = new BufferedReader(new FileReader(request.getText()))) {
            while (fileReader.ready())
                inboundRequests.push(fileReader.readLine());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Response response = new Response(null);
        response.addInboundRequests(inboundRequests);

        return response;
    }
}
