
import controller.ClientSession;
import controller.Container;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpWebServer {
    public static void main(String[] args) {
        int port = 9999;
        String fileName = "test_beans.xml";
        //String fileName = "beans.xml";
        if (!Container.getInstance().init(fileName)) {
            System.out.println("Server not started. Can't init container");
            System.exit(-1);
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port: "
                    + serverSocket.getLocalPort() + "\n");
        } catch (IOException e) {
            System.out.println("Port " + port + " is blocked.");
            System.exit(-1);
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientSession session = new ClientSession(clientSocket);
                session.start();
                Thread.sleep(100);//for more stability
            } catch (IOException e) {
                System.out.println("Failed to establish connection.");
                System.out.println(e.getMessage());
                System.exit(-1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
