package bg.tu_varna.sit.f24621604;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    private boolean fileOpened = false;
    private String openedFileName = "";
    private FileStorage fileStorage = new FileStorage();

    public boolean isFileOpened() {
        return fileOpened;
    }

    public void open(String fileName, Table table) {
        if (fileOpened) {
            System.out.println("File already opened.");
            return;
        }
        fileStorage.read(fileName, table);

        fileOpened = true;
        openedFileName = fileName;
        System.out.println("Successfully opened file " + fileName);
    }

    public void close() {
        if (!fileOpened) {
            System.out.println("No file is currently open.");
            return;
        } else {
            System.out.println("Successfully closed file " + openedFileName);
            fileOpened = false;
            openedFileName = "";
        }
    }

    public void save(Table table) {
        if (!fileOpened) {
            System.out.println("No file is currently open.");
            return;
        }
        fileStorage.write(openedFileName, table);
        System.out.println("File saved successfully.");
    }

    public void saveAs(String newFileName, Table table) {
        if (!fileOpened) {
            System.out.println("No file is currently open.");
            return;
        }
        fileStorage.write(newFileName, table);
        openedFileName = newFileName;
        System.out.println("File saved successfully as " + newFileName);
    }
}
