package bg.tu_varna.sit.f24621604;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "exit","\texits the program");
    }

    @Override
    public void execute() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}
