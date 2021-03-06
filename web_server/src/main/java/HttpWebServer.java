
import controller.ClientSession;
import controller.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpWebServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpWebServer.class);
    public static void main(String[] args) {
        int port = 9999;
        //String fileName = "test_beans.xml";
        String fileName = "beans.xml";
        if (!Container.getInstance().init(fileName)) {
            LOGGER.error("Server not started. Can't init container");
            System.out.println("Server not started. Can't init container");
            System.exit(-1);
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port: "
                    + serverSocket.getLocalPort() + "\n");
            LOGGER.info("Server started on port: "+ serverSocket.getLocalPort() + "\n");
        } catch (IOException e) {
            System.out.println("Port " + port + " is blocked.");
            LOGGER.error("Port " + port + " is blocked.");
            throw new RuntimeException("Can't to start a server. Exception: " + e.toString());
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientSession session = new ClientSession(clientSocket);
                session.start();
                Thread.sleep(100);//for more stability
            } catch (IOException e) {
                LOGGER.error("Failed to establish connection",e);
                throw new RuntimeException("Failed to establish connection. Exception: " + e.toString());
            } catch (InterruptedException e) {
                LOGGER.error("Thread error",e);
            }
        }

    }
}
