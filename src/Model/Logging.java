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
            FileHandler fileHandler = new FileHandler("log.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
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
