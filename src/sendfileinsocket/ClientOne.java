package sendfileinsocket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class ClientOne {

    public static void main(String[] args) {
        
    try {
    Socket cs = new Socket("localhost", 12000);

    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(cs.getOutputStream()));

    //Read a file
    FileReader fr = new FileReader("src/sendfileinsocket/demo.txt"); //Reads file specified as filename
    BufferedReader bfr = new BufferedReader(fr);
    String line;
    while (null != (line = bfr.readLine())) { //if line is not empty,
        dos.writeUTF(line); //lines written for the server
        dos.flush(); //flush output stream to send the data
    }

    //Send an EOF identifier to server
    // dos.writeUTF("EOF");
    // dos.flush();

    bfr.close(); //close the file
    cs.close();

    } catch (UnknownHostException e) { //must be above of IOException
        e.printStackTrace(); //print out error
    } catch (IOException e) {
        e.printStackTrace(); //print out error
    }
}

}
