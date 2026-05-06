package bg.tu_varna.sit.f24621604;

public class SaveAsCommand extends Command {
    private FileService fileService;
    private Table table;

    public SaveAsCommand(FileService fileService, Table table) {
        super("save as", "save as <file>","\tsaves the currently open file in <file>");
        this.fileService = fileService;
        this.table = table;
    }

    @Override
    public void execute() {
        if (getArgs().length < 3) {
            System.out.println("Please provide file name.");
        } else {
            fileService.saveAs(getArgs()[2], table);
        }
    }
}
