package bg.tu_varna.sit.f24621604;

public class Application {
    public static void main(String[] args) {
        CellParser parser = new CellParser();
        TablePrinter printer = new TablePrinter();
        FormulaEvaluator evaluator = new FormulaEvaluator();

        Table table = new Table(parser, printer, evaluator);

        FileService fileService = new FileService();

        CommandLineController controller = new CommandLineController(table, fileService);
        controller.start();
    }
}
