package no.kristiania.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;


public class HttpServer {
    private final ServerSocket serverSocket;
    private Path rootDirectory;
    //private final Thread thread;




    public HttpServer(int serverPort) throws IOException {
         serverSocket = new ServerSocket(serverPort);



        /*thread = new Thread(this::handleClients);
                thread.start();*/

        new Thread(this::handleClients).start();


    }

    private void handleClients() {
        try {
            Socket clientSocket = serverSocket.accept();
            String[] requestLine = HttpClient.readLine(clientSocket).split(" ");
            String requestTarget = requestLine[1];





            if (requestTarget.equals("/hello")){
                String responseText = "Hello world";
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Length: " + responseText.getBytes(StandardCharsets.UTF_8).length +
                        "\r\n\r\n"+
                        responseText;
                clientSocket.getOutputStream().write(response.getBytes(StandardCharsets.UTF_8));
            }



            else{
                String responseText = "File not found: " + requestTarget;
                String response = "HTTP/1.1 404 Not found\r\n" +
                        "Content-Length: " + responseText.getBytes(StandardCharsets.UTF_8).length +
                        "\r\n\r\n"+
                        responseText;
                clientSocket.getOutputStream().write(response.getBytes(StandardCharsets.UTF_8));
            }
            }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) throws IOException {
        new HttpServer(1962);


        /*ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();

        //String requestLine = HttpClient.readLine(clientSocket);
        //System.out.println(requestLine);


        String headerLine;
        while(!(headerLine = HttpClient.readLine(clientSocket)).isBlank()){
            System.out.println(headerLine);

        }


        String messageBody = "<h1>Hello æøå world</h1>";
        String contentType = "text/html; charset=utf-8";

        String responseMessage = "HTTP/1.1 200 OK\r\n"+
                "Content-Length: "+ messageBody.getBytes().length + "\r\n"+
                "Content-Type: " + contentType + "\r\n"+
                "Connection: close\r\n"+
                "\r\n"+
                messageBody;
        clientSocket.getOutputStream().write((responseMessage).getBytes());*/

          /*clientSocket.getOutputStream().write((
                "HTTP/1.1 200 OK\r\n"+
                "Content-Length: "+ messageBody.length() + "\r\n"+
                "Connection: close\r\n"+
                "\r\n"+
                messageBody
        ).getBytes());*/

    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public void setRoot(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
    }
}
