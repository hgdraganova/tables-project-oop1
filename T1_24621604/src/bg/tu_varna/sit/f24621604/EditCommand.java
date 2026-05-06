package bg.tu_varna.sit.f24621604;

public class EditCommand extends Command {
    private Table table;
    private FileService fileService;

    public EditCommand(Table table, FileService fileService) {
        super("edit", "edit <row> <col> <value>","\tedit table");
        this.table = table;
        this.fileService = fileService;
    }

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
            int row = Integer.parseInt(getArgs()[1]) - 1;
            int col = Integer.parseInt(getArgs()[2]) - 1;
            String value = getArgs()[3];

            table.edit(row, col, value);
        } catch (NumberFormatException e) {
            System.out.println("Row and column must be numbers.");
        }
    }
}
