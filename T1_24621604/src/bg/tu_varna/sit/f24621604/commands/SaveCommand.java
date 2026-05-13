package bg.tu_varna.sit.f24621604.commands;

import bg.tu_varna.sit.f24621604.io.FileService;
import bg.tu_varna.sit.f24621604.models.Table;

/**
 * Command used to save the currently opened file.
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
        super("save", "save","\tsaves the currently opened file");
        this.fileService = fileService;
        this.table = table;
    }

    /**
     * Executes the save command.
     * Saves the current table to the opened file.
     */
    @Override
    public void execute() {
        fileService.save(table);
    }
}
