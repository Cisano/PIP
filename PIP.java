/*******************************************/
/* Author cisano - cisano.appdev@gmail.com */
/* Run this class on the computer you      */
/* want to connect, the IP address         */
/* is crypted and saved as output.txt      */
/* in the Dropbox folder.                  */
/* On the other computer must be installed */
/* Dropbox with the same account.          */
/* Dropbox is used as a tunnel :)          */
/* It is possible to use Google Drive,     */
/* One Drive, etc.                         */
/* This class can be installed as a service*/
/* The AES.class is necessary              */ 
/*******************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class PIP implements Runnable {
  
    //For your safety, change the secretKey!
    final static String secretKey = "p24QY9uGalFHrJ35uDQm";
  
    public static void main(String args[]) {    
    Thread t = new Thread(new PIP());
    t.start();
    }

    public PIP() {
        
    }
  
    public static void writeFile(String fileName, byte[] b) throws IOException {
        File file = new File(fileName);
 
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));

        writer.write(b);
        writer.flush();
        writer.close();
    }
  
  public void run() {
    URL ipAdress;
    int i=0;
    int j=0;
    String old_ip="";
    boolean semaphore=false;
            //System.out.println(System.getProperty("user.home"));
    while (i==0) { 
    java.util.Date date = new java.util.Date();
      
         //System.out.println(date+" "+Thread.currentThread().getName()+" "+j++);
         //every 10 minute the class gets the IP
         //you can choose other server or more server
         try {
              if(!semaphore) {
                ipAdress = new URL("https://myexternalip.com/raw");
                semaphore=true;
          }
              else {
                ipAdress = new URL("https://checkip.amazonaws.com");
                semaphore=false;
          }
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdress.openStream()));

            String ip = in.readLine();
            
            if(ip.equals(old_ip)) { }
             else {
                //String ip_crypt= cryptIT(ip);
                //System.out.println(ip+" "+old_ip);
                String fileContent = new String(ip);
          
                //remove comment if you don't need the ip encrypted and comment the 2 lines under
                //byte[] b = fileContent.getBytes();
          
          //the IP is crypted - start
          String encryptedString = AES.encrypt(fileContent, secretKey);
          byte[] b = encryptedString.getBytes();
          //the IP is crypted - end
          
                writeFile(System.getProperty("user.home")+"\\Dropbox\\"+"output.txt", b);
          
              old_ip=ip;
              }
        
            // thread to sleep for 10 minutes
            Thread.sleep(600000);
         }  catch (Exception e) { System.out.println(e); }
            //catch (java.net.UnknownHostException e) { System.out.println("Unknown Host Ex"); }
            //catch (MalformedURLException e) { e.printStackTrace(); }
            //catch (IOException e) { e.printStackTrace(); }
    } // end of while
         
      }
  
    /****************/
    /* crypt the ip */
    public String cryptIT(String ip)
    {
     return ip; 
   }  
}//end of the class
