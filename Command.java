/** From Head First Design Patterns, Freeman & Robson et al. */  
import java.io.*;
public abstract class Command implements Cloneable {
    public abstract void execute();
    public abstract void undo();
    public Object clone() throws CloneNotSupportedException
    {
        try {
            Command c = (Command) super.clone();
            return c; 
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
    void logCommand(String commandName) throws IOException
    {
        Logger logger= Logger.getInstance();
        logger.log(commandName);
        
    }
    void executeWithLog() throws IOException
    {
        this.execute();
        logCommand(this.getClass().getName());
    }
    void undoWithLog() throws IOException
    {
       this.undo();
        logCommand(this.getClass().getName());
    }
}
