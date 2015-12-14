package control.command;

public class ExitCommand implements OperationCommand {
    @Override
    public void execute() {
        System.exit(0);
    }
}
