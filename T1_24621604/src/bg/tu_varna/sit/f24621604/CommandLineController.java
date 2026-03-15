package bg.tu_varna.sit.f24621604;

import java.util.Scanner;

public class CommandLineController {
    private Table table = new Table();
    private FileService fileService = new FileService();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            String[] parts = input.split(" ", 3);
            String command = parts[0];

            if (command.equals("save") && parts.length > 1 && parts[1].equals("as")) {
                command = "save as";
            }

            switch (command) {
                case "open":
                    if (parts.length < 2) {
                        System.out.println("Please provide file name.");
                    } else {
                        fileService.open(parts[1], table);
                    }
                    break;

                case "close":
                    fileService.close();
                    break;

                case "save":
                    fileService.save(table);
                    break;

                case "save as":
                    if (parts.length < 3) {
                        System.out.println("Please provide file name.");
                    } else {
                        fileService.saveAs(parts[2], table);
                    }
                    break;

                case "help":
                    printHelp();
                    break;

                case "exit":
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                case "print":
                    if (!fileService.isFileOpened()) {
                        System.out.println("No file opened.");
                    } else {
                        table.print();
                    }
                    break;

                case "edit":
                    if (!fileService.isFileOpened()) {
                        System.out.println("No file opened.");
                        break;
                    }

                    String[] arg = input.split(" ", 4);
                    if (arg.length < 4) {
                        System.out.println("Usage: edit <row> <col> <value>");
                        break;
                    }

                    try {
                        int row = Integer.parseInt(arg[1]) - 1;
                        int col = Integer.parseInt(arg[2]) - 1;
                        String value = arg[3];

                        table.edit(row, col, value);

                    } catch (NumberFormatException e) {
                        System.out.println("Row and column must be numbers.");
                    }
                    break;

                default:
                    System.out.println("Unknown command. Type 'help'.");
            }
        }
    }

    private void printHelp() {
        System.out.println("The following commands are supported:");
        System.out.println("open <file> - opens <file>");
        System.out.println("close - closes currently opened file");
        System.out.println("save - saves the currently open file");
        System.out.println("save as <file> - saves the currently open file in <file>");
        System.out.println("help - prints this information");
        System.out.println("exit - exits the program");
    }
}
