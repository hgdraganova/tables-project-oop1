package bg.tu_varna.sit.f24621604.models;

import bg.tu_varna.sit.f24621604.commands.*;
import bg.tu_varna.sit.f24621604.io.FileService;
import bg.tu_varna.sit.f24621604.validation.TableLoadException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Controls the command-line interaction with the user.
 * Reads input, parses commands and executes them.
 */
public class CommandLineController {
    /**
     * Stores all available commands mapped by their names.
     */
    private Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Initializes all supported commands.
     *
     * @param table the table instance
     * @param fileService the file service instance
     */
    public CommandLineController(Table table, FileService fileService) {
        commands.put("open", new OpenCommand(fileService, table));
        commands.put("close", new CloseCommand(fileService));
        commands.put("save", new SaveCommand(fileService, table));
        commands.put("print", new PrintCommand(table, fileService));
        commands.put("edit", new EditCommand(table, fileService));
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand());
    }

    /**
     * Starts the command loop.
     * Continuously reads user input and executes commands.
     */
    public void start() {
        /**
         * Reads input from the console
         */
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            /**
             * Raw input from the user
             */
            String input = scanner.nextLine();

            /**
             * Splits input into at most 4 parts:
             * command + arguments (last part keeps remaining text)
             */
            String[] parts = input.split(" ", 4); // разделя входа на максимум 4 части по интервал,
            // като последната част запазва целия остатък (edit 1 2 "Hello world" - остатък)
            /**
             * Extracts the command name
             */
            String commandName = parts[0];

            /**
             * Finds the command by name
             */
            Command command = commands.get(commandName);

            /**
             * Handles unknown command
             */
            if (command == null) {
                System.out.println("Unknown command. Type 'help'.");
                continue;
            }
            /**
             * Sets arguments for the command
             */
            command.setArgs(parts);
            /**
             * Executes the command
             */
            try {
                command.execute();
            } catch (TableLoadException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage()); //програмата не спира, само извежда съобщение за грешка
            }
        }
    }
}