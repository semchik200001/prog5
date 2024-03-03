package se.ifmo.lab5.chat.command.list;

import se.ifmo.lab5.chat.command.Command;
import se.ifmo.lab5.chat.io.transfer.Request;
import se.ifmo.lab5.chat.io.transfer.Response;
import se.ifmo.lab5.collection.CollectionManager;
import se.ifmo.lab5.objects.MusicBand;

import java.util.*;
import java.util.stream.Collectors;

public class GroupCountingByNumberOfParticipants extends Command {
    public GroupCountingByNumberOfParticipants() {
        super("group_counting_by_number_of_participants", "сгруппировать элементы коллекции по значению поля numberOfParticipants, вывести количество элементов в каждой группе");
    }

    @Override
    public Response execute(Request request) {
        // <number_of_participants, List<MusicBand>>
        Map<Integer, List<MusicBand>> grouped = new HashMap<>();

        CollectionManager.getInstance().getCollection()
                .forEach((id, musicBand) -> {
                    if (grouped.containsKey(musicBand.getNumberOfParticipants()))
                        grouped.get(musicBand.getNumberOfParticipants()).add(musicBand);
                    else
                        grouped.put(musicBand.getNumberOfParticipants(), new ArrayList<>(Collections.singletonList(musicBand)));
                });

        return new Response(grouped.entrySet().stream()
                .map(e -> "* Кол-во участников " + e.getKey() + ":\n" + e.getValue().stream()
                        .map(MusicBand::toString)
                        .collect(Collectors.joining()) + "\n\n")
                .collect(Collectors.joining()));
    }
}
