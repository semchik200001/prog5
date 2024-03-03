package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.handler.Handler;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;

import java.util.stream.Collectors;

public class History extends Command {
    public History() {
        super("history", "вывести последние 13 команд (без их аргументов)");
    }

    @Override
    public Response execute(Request request) {
        return new Response(Handler.requests.stream().limit(13)
                .map(temp -> "\n" + temp.getCommand()).collect(Collectors.joining()));
    }
}
