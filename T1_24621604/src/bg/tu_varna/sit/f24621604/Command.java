package bg.tu_varna.sit.f24621604;
/**
 * Abstract base class for all commands in the application.
 * Defines common properties and behavior for command execution.
 */
public abstract class Command {
    /**
     * The name of the command.
     */
    private String name;
    /**
     * The usage format of the command.
     */
    private String usage;
    /**
     * Description of what the command does.
     */
    private String description;
    /**
     * Arguments passed to the command during execution.
     * These are updated on each invocation.
     */
    private String[] args; //args се променят при всяко извикване - същият обект се използва, но с различни аргументи

    /**
     * Constructs a command with name, usage and description.
     *
     * @param name command name
     * @param usage command usage format
     * @param description command description
     */
    public Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    /**
     * Returns the command name.
     *
     * @return command name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the usage format of the command.
     *
     * @return usage string
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Returns the description of the command.
     *
     * @return description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the arguments of the command.
     *
     * @return array of arguments
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Sets the arguments for the command.
     *
     * @param args array of arguments
     */
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * Executes the command.
     * Must be implemented by all concrete command classes.
     */
    public abstract void execute();
}
