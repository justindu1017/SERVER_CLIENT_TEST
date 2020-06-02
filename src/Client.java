import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        // write your code here
        try {
            Socket socket = new Socket("127.0.0.1", 8088);
            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("yo");
            out.flush();
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println(dataInputStream.readUTF());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}