package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
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
    public static void main(String[] args) 
    {
        
    }
    
    public static void badLogin()
    {
         try 
        {
            PrintWriter printwriter = new PrintWriter(new FileOutputStream(new File("login_activity.txt"),true));
            printwriter.append("Unsuccessful Login Detected on " + Instant.now() +"\n");
            System.out.println("Bad Login Detected");
            printwriter.close();
        } 
        
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void goodLogin()
    {
         try 
        {
            PrintWriter printwriter = new PrintWriter(new FileOutputStream(new File("login_activity.txt"),true));
            
            printwriter.append("Successful Login Detected on "+ Instant.now() +"\n");
            System.out.println("Approved Login Detected");
            printwriter.close();
        } 
        
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Logging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
   
}
