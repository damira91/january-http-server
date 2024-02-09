package ru.otus.java.basic.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;


public class HttpServer {
    static final Logger logger = Logger.getLogger(HttpServer.class.getName());
    private final int port;
    private final Dispatcher dispatcher;
    // для обработки запросов в отдельных потоках
   // private ExecutorService executor;


    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
       // this.executor = Executors.newFixedThreadPool(5);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ConnectionHandler(socket, dispatcher));
                thread.start();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при принятии соединения", e);
        }
    }
    // для обработки запросов в отдельных потоках
   /* public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executor.execute(new ConnectionHandler(socket, dispatcher));
                } catch (IOException e) {
                   logger.log(Level.SEVERE, "Ошибка при принятии соединения", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
