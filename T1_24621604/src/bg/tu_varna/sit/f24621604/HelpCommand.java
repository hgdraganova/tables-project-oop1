package bg.tu_varna.sit.f24621604;

import java.util.Map;
/**
 * Command used to display information about all available commands.
 */
public class HelpCommand extends Command {
    /**
     * Stores all available commands.
     */
    private Map<String, Command> commands;

    /**
     * Constructs the HelpCommand.
     *
     * @param commands map of command names to command objects
     */
    public HelpCommand(Map<String, Command> commands) {
        super("help", "help","\tprints this information");
        this.commands = commands;
    }

    /**
     * Executes the help command.
     * Prints all supported commands with their usage and description.
     */
    @Override
    public void execute() {
        System.out.println("The following commands are supported:");
        for (Command cmd : commands.values()) {
            System.out.printf("%-25s %s%n", cmd.getUsage(), cmd.getDescription());
        }
    }
}
