package bg.tu_varna.sit.f24621604;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean fileOpened = false;
        List<List<String>> tableData = new ArrayList<>();
        String openedFileName = "";

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();

            if (input.startsWith("open ")) {
                if (fileOpened) {
                    System.out.println("File already opened.");
                }
                else {
                    String fileName = input.substring(5);

                    try {
                        File file = new File(fileName);
                        Scanner fileScanner = new Scanner(file);

                        tableData.clear();

                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();

                            String[] cells = line.split(",");

                            List<String> row = new ArrayList<>();

                            for (String cell : cells) {
                                row.add(cell.trim());
                            }
                            tableData.add(row);
                        }
                        fileScanner.close();
                        fileOpened = true;
                        openedFileName = file.getName();
                        System.out.println("Successfully opened file " + openedFileName);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    }
                }
                continue;
            }

            if (input.equals("close")) {
                if (!fileOpened) {
                    System.out.println("No file is currently open.");
                } else {
                    fileOpened = false;
                    System.out.println("Successfuly closed file " + openedFileName);
                }
                continue;
            }

            if (input.equals("help")) {
                System.out.println("The following commands are supported:");
                System.out.println("open <file> - opens <file>");
                System.out.println("close - closes currently opened file");
                System.out.println("save - saves the currently open file");
                System.out.println("save as - saves the currently open file in <file>");
                System.out.println("help - prints this information");
                System.out.println("exit - exits the program");
                continue;
            }

            if (input.equals("exit")) {
                System.out.println("Exiting program...");
                break;
            }

            System.out.println("Unknown command. Type 'help'.");
        }
        scanner.close();
    }
}
