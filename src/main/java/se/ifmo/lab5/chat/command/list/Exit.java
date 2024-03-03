package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;

public class Exit extends Command {
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public Response execute(Request request) {
        System.exit(1);
        return new Response("program executed");
    }
}
