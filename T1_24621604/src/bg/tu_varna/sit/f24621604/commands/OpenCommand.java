package bg.tu_varna.sit.f24621604.commands;

import bg.tu_varna.sit.f24621604.io.FileService;
import bg.tu_varna.sit.f24621604.models.Table;

/**
 * Command used to open a file and load its contents into the table.
 */
public class OpenCommand extends Command {
    /**
     * Service responsible for file operations.
     */
    private FileService fileService;
    /**
     * The table where data will be loaded.
     */
    private Table table;

    /**
     * Constructs the OpenCommand.
     *
     * @param fileService the file service
     * @param table the table to load data into
     */
    public OpenCommand(FileService fileService, Table table) {
        super("open", "open <file>","\topens file");
        this.fileService = fileService;
        this.table = table;
    }

    /**
     * Executes the open command.
     * Validates input and opens the specified file.
     */
    @Override
    public void execute() {
        if (getArgs().length < 2) {
            System.out.println("Please provide file name.");
        } else {
            fileService.open(getArgs()[1], table);
        }
    }
}
