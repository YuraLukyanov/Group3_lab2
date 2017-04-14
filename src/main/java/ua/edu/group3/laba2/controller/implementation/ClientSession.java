package ua.edu.group3.laba2.controller.implementation;

import java.io.*;
import java.net.Socket;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class ClientSession extends Thread {
    private Socket socket;
    private InputStream in = null;
    private OutputStream out = null;

    public ClientSession(Socket socket) throws IOException {
        this.socket = socket;
        initialize();
    }

    protected void initialize() throws IOException {
        //Get input stream to get request
        in = socket.getInputStream();
        //Get output stream to send response
        out = socket.getOutputStream();

    }

    public void run() {
        try {
            String request = "";
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(in));
            while (bufReader.ready()) {
                request += (char) bufReader.read();
            }
            bufReader.close();
            
            HttpRequest httpRequest = new HttpRequest(request);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
