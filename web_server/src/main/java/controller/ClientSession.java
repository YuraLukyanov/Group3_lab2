package controller;

import view.HttpServerResponse;

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

    @Override
    public void run() {
        BufferedReader bufReader = null;
        try {
            String request = "";
            bufReader = new BufferedReader(new InputStreamReader(in));
            while (bufReader.ready()) {
                request += (char) bufReader.read();
            }
            if (!request.isEmpty()) {
                HttpRequest httpRequest = new HttpRequest(request);
                HttpServerResponse httpServerResponse = new HttpServerResponse(
                        httpRequest.getServiceName(),
                        httpRequest.getServiceParam());
                PrintStream answer = new PrintStream(out, true, "UTF-8");
                answer.print(httpServerResponse.getResponse());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
