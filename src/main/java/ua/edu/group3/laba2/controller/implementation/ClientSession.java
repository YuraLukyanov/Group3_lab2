package ua.edu.group3.laba2.controller.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    }

    @Override
    public void run() {
        super.run();
    }
}
