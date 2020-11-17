package Model;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author zach.hendrix
 */
public class Logging 
{
    Logger logger = Logger.getLogger("log.txt");
    public static void main(String[] args) 
    {
        
        Logger logger = Logger.getLogger("log.txt");
        
        try {
            //The following four lines write the log text to a file. Otherwise it will print only to the console. 
            FileHandler fileHandler = new FileHandler("log.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
            //change the following line to change what gets logged.
            // Here is the descending list:
//        SEVERE (highest)
//        WARNING
//        INFO
//        CONFIG
//        FINE
//        FINER
//        FINEST
//      So if you set the following line to log.setLevel(Level.INFO), only logs that have levels SEVERE, WARNING, or INFO will actually get logged.
//      Great for debugging! You could set it to FINEST, and then when you put the code into production, set it to INFO or WARNING, for instance, so that you
//      don't get the debugging log info in your text file
        } 
        
        catch (IOException ex) 
        {
            Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        catch (SecurityException ex) 
        {
            Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void SevereWarning()
    {
        Logger logger = Logger.getLogger("log.txt");
        logger.setLevel(Level.CONFIG); //change this line to see how the output changes!
     
        logger.severe("Username or Password is incorrect");
        logger.log(Level.SEVERE,"Incorrect Username or Password submitted");
    }
        
   
}
