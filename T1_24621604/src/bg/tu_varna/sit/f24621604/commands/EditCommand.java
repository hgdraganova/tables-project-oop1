package bg.tu_varna.sit.f24621604.commands;

import bg.tu_varna.sit.f24621604.io.FileService;
import bg.tu_varna.sit.f24621604.models.Table;

/**
 * Command used to edit a specific cell in the table.
 */
public class EditCommand extends Command {
    /**
     * The table on which the edit operation is performed.
     */
    private Table table;
    /**
     * Service used to check file state.
     */
    private FileService fileService;

    /**
     * Constructs the EditCommand.
     *
     * @param table the table to edit
     * @param fileService the file service
     */
    public EditCommand(Table table, FileService fileService) {
        super("edit", "edit <row> <col> <value>","\tedit table");
        this.table = table;
        this.fileService = fileService;
    }

    /**
     * Executes the edit command.
     * Validates input and updates the specified cell.
     */
    @Override
    public void execute() {
        if (!fileService.isFileOpened()) {
            System.out.println("No file opened.");
            return;
        }

        if (getArgs().length < 4) {
            System.out.println("Usage: edit <row> <col> <value>");
            return;
        }

        try {
            /**
             * Row index (converted from 1-based to 0-based)
             */
            int row = Integer.parseInt(getArgs()[1]) - 1;
            /**
             * Column index (converted from 1-based to 0-based)
             */
            int col = Integer.parseInt(getArgs()[2]) - 1;
            /**
             * New value for the cell
             */
            String value = getArgs()[3];

            table.edit(row, col, value);
        } catch (NumberFormatException e) {
            System.out.println("Row and column must be numbers.");
        }
    }
}
