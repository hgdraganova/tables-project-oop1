package bg.tu_varna.sit.f24621604;
/**
 * Command used to close the currently opened file.
 */
public class CloseCommand extends Command {
    /**
     * Service responsible for file operations.
     */
    private FileService fileService;

    /**
     * Constructs the CloseCommand.
     *
     * @param fileService the file service used to close files
     */
    public CloseCommand(FileService fileService) {
        super("close", "close","\tcloses currently opened file");
        this.fileService = fileService;
    }

    /**
     * Executes the close command.
     * Closes the currently opened file.
     */
    @Override
    public void execute() {
        fileService.close();
    }
}
