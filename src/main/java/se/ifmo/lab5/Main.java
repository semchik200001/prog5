package se.ifmo.lab5;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.command.list.*;
import se.ifmo.lab5.chat.handler.Handler;
import se.ifmo.lab5.chat.io.console.BufferedConsoleWorker;
import se.ifmo.lab5.chat.io.console.ConsoleWorker;
import se.ifmo.lab5.chat.runner.ConsoleRunner;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Command> commands = List.of(
                new Clear(),
                new CountByNumberOfParticipants(),
                new ExecuteScript(),
                new Exit(),
                new History(),
                new Info(),
                new InsertById(),
                new RemoveByKey(),
                new RemoveGreater(),
                new RemoveLower(),
                new Save(),
                new Show(),
                new UpdateById(),
                new RemoveAnyByGenre(),
                new GroupCountingByNumberOfParticipants()
        );

        Handler handler = new Handler(commands);
        ConsoleWorker bufferedConsoleWorker = new BufferedConsoleWorker();

        ConsoleRunner consoleRunner = new ConsoleRunner(handler, bufferedConsoleWorker);

        new Thread(consoleRunner).start();
    }
}