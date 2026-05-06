package bg.tu_varna.sit.f24621604;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineController {
    private Map<String, Command> commands = new LinkedHashMap<>();

    public CommandLineController(Table table, FileService fileService) {
        commands.put("open", new OpenCommand(fileService, table));
        commands.put("close", new CloseCommand(fileService));
        commands.put("save", new SaveCommand(fileService, table));
        commands.put("save as", new SaveAsCommand(fileService, table));
        commands.put("print", new PrintCommand(table, fileService));
        commands.put("edit", new EditCommand(table, fileService));
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand());
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            String[] parts = input.split(" ", 4); // разделя входа на максимум 4 части по интервал, като последната част запазва целия остатък (edit 1 2 "Hello world" - остатък)
            String commandName = parts[0];

            if (commandName.equals("save") && parts.length > 1 && parts[1].equals("as")) {
                commandName = "save as";
            }

            Command command = commands.get(commandName);

            if (command == null) {
                System.out.println("Unknown command. Type 'help'.");
                continue;
            }
            command.setArgs(parts);
            command.execute();
        }
    }
}
