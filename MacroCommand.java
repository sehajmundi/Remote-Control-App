
/**
 * Write a description of class f here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MacroCommand extends Command {
    Command[] array;

    public MacroCommand(Command[] commands) 
    {
        this.array = array;
    }

    public void execute() 
    {
        for (int i = 0; i < array.length; i++) 
        {
            array[i].execute();
        }
    }

    public void undo() {
        for (int i = array.length -1; i >= 0; i--) 
        {
            array[i].undo();
        }
    }
}
