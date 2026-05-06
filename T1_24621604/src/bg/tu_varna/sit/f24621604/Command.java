package bg.tu_varna.sit.f24621604;

public abstract class Command {
    private String name;
    private String usage;
    private String description;
    private String[] args; //args се променят при всяко извикване - същият обект се използва, но с различни аргументи

    public Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public abstract void execute();
}
