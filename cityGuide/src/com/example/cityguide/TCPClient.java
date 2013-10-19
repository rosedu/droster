package com.example.cityguide;
import android.util.Log;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
 
 
public class TCPClient {
    private String serverMessage;
    public static final String SERVERIP = "141.85.229.152";
    public static final int SERVERPORT = 7121;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;
 
    PrintWriter out;
    BufferedReader in;

    public TCPClient(OnMessageReceived listener) {
        mMessageListener = listener;
    }
 
    public void sendMessage(String message){
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
            Log.e("DEBUG", "Message sended!\n" + message);
        }
    }
 
    public void stopClient(){
        mRun = false;
    }
 
    public void run() {
        mRun = true;
 
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
 
            Log.e("DEBUG", "Connecting...");
 
            Socket socket = new Socket(serverAddr, SERVERPORT);
            Log.e("DEBUG", "Connection established!");
 
            try {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
                while (mRun) {
                    serverMessage = in.readLine();
 
                    if (serverMessage != null && mMessageListener != null) {
                        mMessageListener.messageReceived(serverMessage);
                    }
                    serverMessage = null;
                }
 
            } catch (Exception e) {
                Log.e("DEBUG", "SERVER connection error!", e);
            } finally {
                socket.close();
            }
 
        } catch (Exception e) {
            Log.e("DEBUG", "Can't go over ip!", e);
        }
    }
 
    //Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity
    //class at on asynckTask doInBackground
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}