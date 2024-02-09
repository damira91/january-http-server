package ru.otus.java.basic.http.server;

public class MainApplication {
    // Домашнее задание:
    // - Добавить логирование
    // - Добавить обработку запросов в параллельных потоках

    public static void main(String[] args) {
        HttpServer server = new HttpServer(Integer.parseInt((String)System.getProperties().getOrDefault("port", "8181")));
        server.start();
    }
}

