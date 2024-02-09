package ru.otus.java.basic.http.server.processors;

import com.nimbusds.jose.shaded.json.JSONObject;
import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.entities.Product;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class JsonResponseRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Milk");

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("id", product.getId());
        jsonResponse.put("title", product.getTitle());


        String responseHeaders = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + jsonResponse.toString().getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                "Connection: close\r\n\r\n";
        output.write(responseHeaders.getBytes(StandardCharsets.UTF_8));
        output.write(jsonResponse.toString().getBytes(StandardCharsets.UTF_8));
        output.flush();
    }
}