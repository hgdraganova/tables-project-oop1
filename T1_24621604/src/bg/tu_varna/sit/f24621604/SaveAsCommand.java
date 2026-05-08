package bg.tu_varna.sit.f24621604;
/**
 * Command used to save the current table into a new file.
 */
public class SaveAsCommand extends Command {
    /**
     * Service responsible for file operations.
     */
    private FileService fileService;
    /**
     * The table to be saved.
     */
    private Table table;

    /**
     * Constructs the SaveAsCommand.
     *
     * @param fileService the file service
     * @param table the table to save
     */
    public SaveAsCommand(FileService fileService, Table table) {
        super("save as", "save as <file>","\tsaves the currently open file in <file>");
        this.fileService = fileService;
        this.table = table;
    }

    /**
     * Executes the save as command.
     * Validates input and saves the table to a new file.
     */
    @Override
    public void execute() {
        if (getArgs().length < 3) {
            System.out.println("Please provide file name.");
        } else {
            fileService.saveAs(getArgs()[2], table);
        }
    }
}
