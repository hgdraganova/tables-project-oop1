package bg.tu_varna.sit.f24621604;

public class PrintCommand extends Command {
    private Table table;
    private FileService fileService;

    public PrintCommand(Table table, FileService fileService) {
        super("print","print", "\tprint table");
        this.table = table;
        this.fileService = fileService;
    }

    @Override
    public void execute() {
        if (!fileService.isFileOpened()) {
            System.out.println("No file opened.");
        } else {
            table.print();
        }
    }
}
