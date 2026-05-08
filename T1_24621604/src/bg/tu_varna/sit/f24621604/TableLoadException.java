package bg.tu_varna.sit.f24621604;
/**
 * Custom exception thrown when an error occurs while loading table data.
 * Used to signal invalid input during file parsing.
 */
public class TableLoadException extends RuntimeException {
    /**
     * Constructs a TableLoadException with a specific error message.
     *
     * @param message description of the error
     */
    public TableLoadException(String message) {
        super(message);
    }
}
