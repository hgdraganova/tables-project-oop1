package bg.tu_varna.sit.f24621604;

public class SaveCommand extends Command {
    private FileService fileService;
    private Table table;

    public SaveCommand(FileService fileService, Table table) {
        super("save", "save","\tsaves the currently opened file");
        this.fileService = fileService;
        this.table = table;
    }

    @Override
    public void execute() {
        fileService.save(table);
    }
}
