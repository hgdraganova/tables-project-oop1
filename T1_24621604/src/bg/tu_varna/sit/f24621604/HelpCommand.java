package bg.tu_varna.sit.f24621604;

import java.util.Map;

public class HelpCommand extends Command {
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        super("help", "help","\tprints this information");
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("The following commands are supported:");
        for (Command cmd : commands.values()) {
            System.out.printf("%-25s %s%n", cmd.getUsage(), cmd.getDescription());
        }
    }
}
