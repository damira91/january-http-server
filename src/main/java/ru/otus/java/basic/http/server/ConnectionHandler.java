package ru.otus.java.basic.http.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;


import static ru.otus.java.basic.http.server.HttpServer.logger;

public class ConnectionHandler implements Runnable {
    private Socket socket;
    private Dispatcher dispatcher;

    public ConnectionHandler(Socket socket, Dispatcher dispatcher) {
        this.socket = socket;
        this.dispatcher = dispatcher;
    }
    @Override
    public void run() {
        try {
            byte[] buffer = new byte[8192];
            int n = socket.getInputStream().read(buffer);
            String rawRequest = new String(buffer, 0, n);
            HttpRequest httpRequest = new HttpRequest(rawRequest);
            dispatcher.execute(httpRequest, socket.getOutputStream());
        } catch (IOException e) {
            logger.log (Level.SEVERE, "Ошибка при обработке запроса", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка при закрытии сокета", e);
            }
        }
    }
}

