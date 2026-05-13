package bg.tu_varna.sit.f24621604;

import bg.tu_varna.sit.f24621604.io.FileService;
import bg.tu_varna.sit.f24621604.io.FileStorage;
import bg.tu_varna.sit.f24621604.models.*;

/**
 * Entry point of the application.
 * Initializes all core components and starts the command line interface.
 */
public class Application {
    /**
     * Main method that starts the program.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        /**
         * Responsible for parsing input into Cell objects
         */
        CellParser parser = new CellParser();
        /**
         * Responsible for printing the table
         */
        TablePrinter printer = new TablePrinter();
        /**
         * Responsible for evaluating formulas
         */
        FormulaEvaluator evaluator = new FormulaEvaluator();

        /**
         * Main table that stores all data
         */
        Table table = new Table(parser, printer, evaluator);

        /**
         * Low-level storage responsible for reading and writing files.
         */
        FileStorage storage = new FileStorage();
        /**
         * Service that manages file operations (open, save, close),
         * using the provided FileStorage implementation.
         */
        FileService fileService = new FileService(storage);

        /**
         * Controls user input and executes commands
         */
        CommandLineController controller = new CommandLineController(table, fileService);
        /**
         * Starts the program loop
         */
        controller.start();
    }
}
