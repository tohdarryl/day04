import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

public static void main(String[] args) {
    int port = 12345;
    try {
        Socket cs = new Socket("localhost", port); //connects to application on localhost on port 12345

        //Get the I/O streams
        OutputStream os = cs.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        // dos.writeUTF("Hello world from CLient"); //write data intended for server
        // dos.flush();//flush output stream and  send the data
        // System.out.println("Message sent to server");

        Scanner inputSc = new Scanner(System.in);
        String line;
        while ((line = inputSc.nextLine()) !=null) { //if input into Client is not null
            if(line.equalsIgnoreCase("close")){//exit condition
                System.out.println("Exit from shell");
                dos.writeUTF("close"); //"close" written for server
                dos.flush(); //flush "close" to server
                break;
            }

            dos.writeUTF(line); //line written other than "close"
            dos.flush(); //flush line to server
            System.out.println("MSG sent to client : " + line);

        }   
            cs.close(); //close socket from client, at end of try{}
            inputSc.close(); //close scanner, at end of try{}


    } catch (UnknownHostException e) {
        System.out.println("Unable to reach the HOST");
    } catch (IOException e) {
        System.out.println("IO Error");
        
    }
}

    
}