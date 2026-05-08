package bg.tu_varna.sit.f24621604;
/**
 * Command used to print the contents of the table.
 */
public class PrintCommand extends Command {
    /**
     * The table to be printed.
     */
    private Table table;
    /**
     * Service used to check file state.
     */
    private FileService fileService;

    /**
     * Constructs the PrintCommand.
     *
     * @param table the table to print
     * @param fileService the file service
     */
    public PrintCommand(Table table, FileService fileService) {
        super("print","print", "\tprint table");
        this.table = table;
        this.fileService = fileService;
    }

    /**
     * Executes the print command.
     * Prints the table only if a file is currently opened.
     */
    @Override
    public void execute() {
        if (!fileService.isFileOpened()) {
            System.out.println("No file opened.");
        } else {
            table.print();
        }
    }
}
