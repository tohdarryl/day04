package sendfileinsocket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class ServerOne {

    public static void main(String[] args) {
        System.out.println("SOCKET SERVER : ");
        int port = 12000;

        try {
            ServerSocket server = new ServerSocket(port);
            Socket sc = server.accept();
            

            DataInputStream dis = new DataInputStream(new BufferedInputStream(sc.getInputStream()));

            String line = dis.readUTF(); //read line as String from DataInputStream
            while (!line.equalsIgnoreCase("EOF") && line != null){
                System.out.println("Got : " + line);
                //Read the next line
                try{
                line = dis.readUTF();
                } catch (EOFException e){
                    System.out.println("Reached End Of File");
                    break;
                }
            }
            sc.close();
            server.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        } 


    }
    
}
