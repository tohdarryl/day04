import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("SOCKET SERVER :");
        int port = 12345;
        
        //create the server socket
        try {
            ServerSocket server = new ServerSocket(port); //creates server and opens a port to accept connection

            //get the socket object 
            Socket socket = server.accept(); //blocks until a connection arrives

            //new connection from a client. (we just accepted it)

            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            // String msg = dis.readUTF(); //if there is any msg from client. It will be here
            // System.out.println("MSG received -> " + msg);
            // socket.close();
            String fromClient = dis.readUTF(); //Read content from DataInputStram
            while (!fromClient.equalsIgnoreCase("close") && fromClient != null){ //If input not equals to 'close' or null

                //process the msg
                System.out.println("Received msg from client: " + fromClient);

                //read the next line from the input steam
                fromClient = dis.readUTF();

            }   

            socket.close(); //close socket at the end of try{}
            server.close(); //close server at the end of try{}

        } catch (IOException e){
            System.out.println("IO error");
        }

        } 


    }



