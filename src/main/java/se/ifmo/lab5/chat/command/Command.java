package se.ifmo.lab5.chat.command;

import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;

public abstract class Command {
    private final String name;
    private final String help;

    public Command(String name, String help) {
        this.name = name;
        this.help = help;
    }
    public Command(String name) {
        this(name, "no help provided");
    }

    public String getName() {
        return name;
    }
    public String getHelp() {
        return help;
    }

    public abstract Response execute(Request request);
}
