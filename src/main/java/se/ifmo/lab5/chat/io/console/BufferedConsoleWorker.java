package se.ifmo.lab5.chat.io.console;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class BufferedConsoleWorker extends ConsoleWorker {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private final BufferedWriter error = new BufferedWriter(new OutputStreamWriter(System.err));

    @Override
    public void send(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void error(String message) {
        try {
            error.write(message);
            error.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String get(String label) {
        try {
            final Date currentDate = new Date();
            writer.append(String.format(label, currentDate, currentDate, currentDate)).append(": ").flush();
            return reader.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
