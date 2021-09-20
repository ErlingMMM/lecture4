package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpClient {
    public HttpClient(String host, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(host, port);

        String request = "GET" + requestTarget + "HTTP/1.1\r\n"+
                "Host: "+ host + "\r\n"+
                "Connection: close\r\n"+
                "\r\n";
        socket.getOutputStream().write(request.getBytes());


        String status = readLine(socket);

      

    }

    private String readLine(Socket socket) {
        return new
    }

    public int getStatusCode() {
        return 200;
    }
}
