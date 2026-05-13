package bg.tu_varna.sit.f24621604.io;

import bg.tu_varna.sit.f24621604.models.Table;
import bg.tu_varna.sit.f24621604.contracts.Cell;
import bg.tu_varna.sit.f24621604.validation.TableLoadException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
/**
 * Handles low-level file operations such as reading from and writing to files.
 */
public class FileStorage {
    /**
     * Reads data from a file and loads it into the table.
     *
     * @param fileName the name of the file to read
     * @param table the table to populate
     * @throws TableLoadException if invalid input data is encountered
     */
    public void read(String fileName, Table table) {
        Scanner scanner = null;
        try {
            File file = new File(fileName);

            /**
             * If file does not exist, create empty table (logical behavior)
             */
            if (!file.exists()) {
                table.clear(); //създаване на празна таблица
                return;
            }

            scanner  = new Scanner(file);

            table.clear();
            /**
             * Current row index (used for error reporting)
             */
            int row = 0;

            /**
             * Reads file line by line and parses each row
             */
            while (scanner .hasNextLine()) {
                String line = scanner.nextLine();
                table.addParsedRow(line, row);
                row++;
            }
        } catch (IllegalArgumentException e) {
            /**
             * Wraps parsing errors and stops program
             */
            throw new TableLoadException(e.getMessage()); //спиране на програмата при откриване на грешка във входните данни
        } catch (FileNotFoundException e) {
            /**
             * If file is not found, clear the table
             */
            table.clear();
        } finally {
            /**
             * Closes scanner if it was opened
             */
            if (scanner != null) {
                scanner.close();
            }//Scanner.close() не хвърля checked exception
        }
    }

    /**
     * Writes the table data to a file.
     *
     * @param fileName the file to write to
     * @param table the table containing data
     */
    public void write(String fileName, Table table) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName);

            /**
             * Iterates through all rows and cells and writes them in CSV format
             */
            for (List<Cell> row : table.getData()) {
                for (int i = 0; i < row.size(); i++) {
                    writer.write(row.get(i).getValue());
                    /**
                     * Adds comma between values (but not after last one)
                     */
                    if (i < row.size() - 1) writer.write(",");
                }
                /**
                 * New line after each row
                 */
                writer.write("\n");
            }
        } catch (IOException e) {
            /**
             * Handles write errors without stopping the program
             */
            System.out.println("Error while saving file."); //програмата не спира
        } finally {
            /**
             * Closes writer safely
             */
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing file."); //програмата не спира
                } //FileWriter.close() throws IOException
            }
        }
    }
}
