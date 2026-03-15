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

    public boolean isFileOpened() {
        return fileOpened;
    }

    public void open(String fileName, Table table) {
        if (fileOpened) {
            System.out.println("File already opened.");
            return;
        }

        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            table.clear();
            int rowIdx = 0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                table.addParsedRow(line, rowIdx);
                rowIdx++;
            }
            fileScanner.close();

            this.fileOpened = true;
            this.openedFileName = fileName;
            System.out.println("Successfully opened file " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.exit(1);
        }
    }

    public void close() {
        if (!fileOpened) {
            System.out.println("No file is currently open.");
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
        writeToFile(openedFileName, table);
        System.out.println("File saved successfully.");
    }

    public void saveAs(String newFileName, Table table) {
        if (!fileOpened) {
            System.out.println("No file is currently open.");
            return;
        }
        writeToFile(newFileName, table);
        this.openedFileName = newFileName;
        System.out.println("File saved successfully as " + newFileName);
    }

    private void writeToFile(String fileName, Table table) {
        try (FileWriter writer = new FileWriter(fileName)) {

            for (List<Cell> row : table.getData()) {
                for (int i = 0; i < row.size(); i++) {
                    writer.write(row.get(i).getValue());
                    if (i < row.size() - 1) writer.write(",");
                }
                writer.write("\n");
            }

        } catch (IOException e) {
            System.out.println("Error while saving file.");
        }
    }
}
