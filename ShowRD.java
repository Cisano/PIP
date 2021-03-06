/************************************************************/
/* Author cisano - cisano.appdev@gmail.com                  */
/* Run this class on the PC you use for connect to          */
/* another PC, the output.txt in the Dropbox folder         */
/* is decrypted and the Microsoft Remote Desktop is called  */
/* Remember to open the port 3389 in the firewall           */
/* The AES.class is necessary                               */
/************************************************************/
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
 
public class ShowRD {
    private ShowRD() {
                     
    }
 
    public static void main(String[] args) {
    String content="";
    try {
      content = new String(Files.readAllBytes(Paths.get(System.getProperty("user.home")+"\\Dropbox\\"+"output.txt")));
      //the output.txt is decrypted here
      content = AES.decrypt(content, PIP.secretKey);  
      }
      catch(IOException e) {
      e.printStackTrace();
      }
    
    
    try{
      //Microsoft Remote Desktop is called here
      //for other OS, modify this line
      Runtime.getRuntime().exec("mstsc.exe /v:"+content+":3389");
      }
        catch(IOException e) {
      e.printStackTrace();
      }
    }
}//end of the class
