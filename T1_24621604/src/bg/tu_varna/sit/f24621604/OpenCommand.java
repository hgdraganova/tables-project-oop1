package bg.tu_varna.sit.f24621604;

public class OpenCommand extends Command {
    private FileService fileService;
    private Table table;

    public OpenCommand(FileService fileService, Table table) {
        super("open", "open <file>","\topens file");
        this.fileService = fileService;
        this.table = table;
    }

    @Override
    public void execute() {
        if (getArgs().length < 2) {
            System.out.println("Please provide file name.");
        } else {
            fileService.open(getArgs()[1], table);
        }
    }
}
