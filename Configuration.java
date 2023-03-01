/**
 * Write a description of class Configuration here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Configuration
{
    private int numberOfOptions;
    private int height;
    private static Configuration uniqueInstance;   
    public Configuration() throws FileNotFoundException
    {
        Scanner kb = new Scanner(new File("RemoteConfig.txt"));
        int[] array = new int[2];
        int i =0;
        while(kb.hasNextInt())
        {
            array[i] = kb.nextInt();
            i++;
        }
        numberOfOptions = array[0];
        height = array[1];
    }
    
    public static Configuration getInstance() throws FileNotFoundException
    {
        if (uniqueInstance == null) 
        {
            uniqueInstance = new Configuration() ;
        }
        return uniqueInstance;
    }
    
    public int getNumberOfOptions()
    {
        return numberOfOptions;
    }
    
    public int getHeight()
    {
        return height;
    }
}
