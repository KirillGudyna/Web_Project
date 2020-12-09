package com.gudyna.webproject.controller;

public class CommandProvider {
    private CommandProvider() {
    }

    public static ActionCommand defineCommand(String commandName) {

        ActionCommand currentCommand = CommandType.EMPTY.getCommand();

        if (commandName != null && !commandName.isEmpty()) {
            try {
                currentCommand = CommandType.valueOf(commandName.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e) {
                //logger
            }
        }

        return currentCommand;
    }
}
