//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Server {
//
//    public static void main(String[] args) {
//	// write your code here
//        try {
//            ServerSocket serverSocket = new ServerSocket(8088);
//            Socket socket = serverSocket.accept();
//            System.out.println("connected");
//            if (socket.isConnected()){
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}

import java.net.*;
import java.io.*;

public class Server extends Thread {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                out.writeUTF("OKK");
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread t = new Server(8088);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}