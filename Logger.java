
/**
 * Write a description of class Logger here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class Logger
{ 
    private String command;
    private static Logger uniqueInstance;
    private  SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
    private FileWriter fileWriter;
    private Logger() throws IOException
    {
        fileWriter = new FileWriter("log.txt");
    }

    public static Logger getInstance() throws IOException
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new Logger();
        }
        return uniqueInstance;
    }

    public void log(String command) throws IOException
    {
        fileWriter = new FileWriter("log.txt",true);
        Date date = new Date(System.currentTimeMillis());
        fileWriter.write(date+" "+command+"\n");
        fileWriter.close();
    }
}
