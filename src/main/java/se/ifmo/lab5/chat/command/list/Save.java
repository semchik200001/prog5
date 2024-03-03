package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.Configuration;
import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;

public class Save extends Command {
    public Save() {
        super("save", "сохранить коллекцию в файл");
    }

    @Override
    public Response execute(Request request) {
        CollectionManager.getInstance().save(Configuration.ROOT_FILE);
        return new Response(null);
    }
}
