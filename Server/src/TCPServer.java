import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
 
/**
 * The class extends the Thread class so we can receive and send messages at the same time
 */
public class TCPServer extends Thread {
 
    public static final int SERVERPORT = 7123;
    private boolean running = false;
   // private PrintWriter mOut;
    private OnMessageReceived messageListener;
    public static List<Socket> loggedUsers; 
 
    public static void main(String[] args) {
    	
        //opens the window where the messages will be received and sent
        ServerBoard frame = new ServerBoard();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
 
    }
 
    /**
     * Constructor of the class
     * @param messageListener listens for the messages
     */
    public TCPServer(OnMessageReceived messageListener) {
        this.messageListener = messageListener;
        //outStream = new HashMap<Socket, PrintWriter>();
        loggedUsers = new ArrayList<Socket>();
    }
 
 
    /**
     * Method to send the messages from server to client
     * @param message the message sent by the server
     */
    public void sendMessage(String message, Socket client){
    	PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }
 
    @Override
    public void run() {
        super.run();
 
        running = true;
 
        try {
            System.out.println("S: Connecting...");
 
            //create a server socket. A server socket waits for requests to come in over the network.
            ServerSocket serverSocket = new ServerSocket(SERVERPORT);
 
            while(true) {
	            //create client socket... the method accept() listens for a connection to be made to this socket and accepts it.
	            Socket client = serverSocket.accept();
	            loggedUsers.add(client);
	            System.out.println("S: Receiving...");
	            
	          //Create a new custom thread to handle the connection
                ClientThread cT = new ClientThread(client);
                 
                //Start the thread!
                new Thread(cT).start();
            }
 
        } catch (Exception e) {
            System.out.println("S: Error");
            e.printStackTrace();
        }
 
    }
 
 
    //Declare the interface. The method messageReceived(String message) will must be implemented in the ServerBoard
    //class at on startServer button click
    public interface OnMessageReceived {
        public void messageReceived(String message, Socket client);
    }
    
    class ClientThread implements Runnable
    {
        Socket client;
         
        //This constructor will be passed the socket
        public ClientThread(Socket client)
        {
            //Here we set the socket to a local variable so we can use it later
            this.client = client;
        }
         
        public void run()
        {
        	try {
        		 
                //sends the message to the client
                //mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
                
                //add client in hashmap
                //outStream.put(client, arg1)
                
                //read the message received from client
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
 
                //in this while we wait to receive messages from client (it's an infinite loop)
                //this while it's like a listener for messages
                while (running) {
                    String message = in.readLine();
 
                    if (message != null && messageListener != null) {
                        //call the method messageReceived from ServerBoard class
                        messageListener.messageReceived(message, client);
                    }
                }
 
            } catch (Exception e) {
                System.out.println("S: Error");
                e.printStackTrace();
            } finally {
                System.out.println("S: Done.");
            }
        }
    }
 
}