package bg.tu_varna.sit.f24621604.commands;

/**
 * Command used to terminate the application.
 */
public class ExitCommand extends Command {
    /**
     * Constructs the ExitCommand.
     */
    public ExitCommand() {
        super("exit", "exit","\texits the program");
    }

    /**
     * Executes the exit command.
     * Prints a message and stops the program.
     */
    @Override
    public void execute() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}
