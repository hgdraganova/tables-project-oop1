package bg.tu_varna.sit.f24621604.commands;

import bg.tu_varna.sit.f24621604.io.FileService;
import bg.tu_varna.sit.f24621604.models.Table;

/**
 * Command used to save the currently opened file.
 * Supports both:
 * - save
 * - save as <file>
 */
public class SaveCommand extends Command {
    /**
     * Service responsible for file operations.
     */
    private FileService fileService;
    /**
     * The table to be saved.
     */
    private Table table;

    /**
     * Constructs the SaveCommand.
     *
     * @param fileService the file service
     * @param table the table to save
     */
    public SaveCommand(FileService fileService, Table table) {
        super("save", "save [as <file>]","\tsaves the currently opened file or saves as <file>");
        this.fileService = fileService;
        this.table = table;
    }

    /**
     * Executes the save command.
     * Saves the current table to the opened file.
     */
    @Override
    public void execute() {
        String[] args = getArgs();
        if (args.length == 1) {
            fileService.save(table);
            return;
        }

        if (args.length >= 3 && "as".equals(args[1])) {
            fileService.saveAs(args[2], table);
            return;
        }
        throw new IllegalArgumentException("Usage: save [as <file>]");
    }
}