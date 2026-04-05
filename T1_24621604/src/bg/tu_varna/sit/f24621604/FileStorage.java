package bg.tu_varna.sit.f24621604;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileStorage {
    public void read(String fileName, Table table) {
        Scanner scanner = null;
        try {
            File file = new File(fileName);
            scanner  = new Scanner(file);

            table.clear();
            int row = 0;

            while (scanner .hasNextLine()) {
                String line = scanner.nextLine();
                table.addParsedRow(line, row);
                row++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
            //спиране на програмата при откриване на грешка във входните данни
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            //спиране на програмата при откриване на грешка във входните данни
        } finally {
            if (scanner != null) {
                scanner.close();
            }//Scanner.close() не хвърля checked exception
        }
    }

    public void write(String fileName, Table table) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName);

            for (List<Cell> row : table.getData()) {
                for (int i = 0; i < row.size(); i++) {
                    writer.write(row.get(i).getValue());
                    if (i < row.size() - 1) writer.write(",");
                }
                writer.write("\n");
            }

        } catch (IOException e) {
            System.out.println("Error while saving file.");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing file.");
                } //FileWriter.close() throws IOException
            }
        }
    }
}
