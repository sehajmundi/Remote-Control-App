/** From Head First Design Patterns, Freeman & Robson et al. */  
import java.util.*;
import java.io.*;
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Stack<Command> undo = new Stack<>(); 
    private Stack<Command> redo = new Stack<>();
    public RemoteControl() 
    {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) 
    {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot)  
    {
        try
        {
            onCommands[slot].executeWithLog();
            undo.push((Command)onCommands[slot]);
        }
        catch(IOException e){}

    }

    public void offButtonWasPushed(int slot)  
    {
        try
        {
            offCommands[slot].executeWithLog();
            undo.push((Command)offCommands[slot]);
        }
        catch(IOException e) {}
    }

    public void undoButtonWasPushed() 
    {
        if (undo.empty()) 
        {
            return;
        }
        Command undoCommand = undo.pop();
        try
        {
            undoCommand.undoWithLog();
        }
        catch(IOException e){}
        redo.push(undoCommand);
    }

    public void redoButtonWasPushed() 
    {
        if (redo.empty())
        {
            return;
        }
        Command redoCommand = redo.pop();

        try
        {
            redoCommand.executeWithLog();
        }
        catch(IOException e){}
        undo.push(redoCommand);
    }

    /* for debugging / testing */
    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName()
                + "    " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuff.toString();
    }
} 