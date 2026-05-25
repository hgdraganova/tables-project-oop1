package bg.tu_varna.sit.f24621604.io;

import bg.tu_varna.sit.f24621604.models.Table;

/**
 * Service responsible for handling file operations such as
 * opening, closing, saving and saving as a new file.
 */
public class FileService {
    /**
     * Indicates whether a file is currently opened.
     */
    private boolean fileOpened = false;
    /**
     * Stores the name of the currently opened file.
     */
    private String openedFileName = "";
    /**
     * Handles low-level file reading and writing.
     */
    private FileStorage fileStorage;

    /**
     * Constructs a FileService with a given FileStorage implementation.
     *
     * @param fileStorage the storage used for file operations
     */
    public FileService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }


    /**
     * Checks if a file is currently opened.
     *
     * @return true if a file is opened, false otherwise
     */
    public boolean isFileOpened() {
        return fileOpened;
    }

    /**
     * Opens a file and loads its content into the table.
     *
     * @param fileName the name of the file
     * @param table the table to load data into
     */
    public void open(String fileName, Table table) {
        if (fileOpened) {
            throw new IllegalStateException("File already opened.");
        }
        fileStorage.read(fileName, table);
        fileOpened = true;
        openedFileName = fileName;
        System.out.println("Successfully opened file " + fileName);
    }

    /**
     * Closes the currently opened file.
     */
    public void close() {
        if (!fileOpened) {
            throw new IllegalStateException("No file is currently open.");
        }
        System.out.println("Successfully closed file " + openedFileName);
        fileOpened = false;
        openedFileName = "";
    }

    /**
     * Saves the current table to the opened file.
     *
     * @param table the table to save
     */
    public void save(Table table) {
        if (!fileOpened) {
            throw new IllegalStateException("No file is currently open.");
        }
        fileStorage.write(openedFileName, table);
        System.out.println("Successfully saved file " + openedFileName);
    }

    /**
     * Saves the current table to a new file.
     *
     * @param newFileName the name of the new file
     * @param table the table to save
     */
    public void saveAs(String newFileName, Table table) {
        if (!fileOpened) {
            throw new IllegalStateException("No file is currently open.");
        }
        fileStorage.write(newFileName, table);
        openedFileName = newFileName;
        System.out.println("Successfully saved another file as " + newFileName);
    }
}