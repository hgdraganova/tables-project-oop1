package bg.tu_varna.sit.f24621604;

public class CloseCommand extends Command {
    private FileService fileService;

    public CloseCommand(FileService fileService) {
        super("close", "close","\tcloses currently opened file");
        this.fileService = fileService;
    }

    @Override
    public void execute() {
        fileService.close();
    }
}
