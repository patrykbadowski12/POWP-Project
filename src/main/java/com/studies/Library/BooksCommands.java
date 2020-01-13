package com.studies.Library;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksCommands {

    private List<Command> commandList = new ArrayList<>();

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void executeCommands(Long id) {
        for (Command command: commandList) {
            command.execute(id);
        }
        commandList.clear();
    }
}
