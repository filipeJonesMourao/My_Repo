import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaWebServer {

    private static int portnum = 8080;

    private ServerSocket socket;
    private Socket clientSocket;
    private BufferedReader input;
    private DataOutputStream output;
    private String[] processedMessage;
    private String directoryPath = "/Users/codeforallsintra/Desktop/Projects/Repositories/personal/JavaWebServer/src/main/resources";
    private String path;
    private File file;
    private byte[] buffer;

    private String headerOkFile;
    private String httpOkImage;
    private String headerNotFound;


    public static void main(String[] args) {

        JavaWebServer server = new JavaWebServer();
        server.start();
    }

    private void start() { // initializes server program and runs the server functions (read/write)

        try {

            listen();

            while (true) {

                connect();
                readAndReturn();
                close();
            }

        } catch (IOException e) {

            System.err.println(e.getMessage());

        } /*finally {

            try {

                if (socket != null) {

                    socket.close();
                }

            } catch (IOException e) {

                System.err.println(e.getMessage());
            }
        }*/
    }

    private void listen() throws IOException { // generating socket (by also accepting client connection) and Reader/Writer

        socket = new ServerSocket(portnum);
    }

    private void connect() throws IOException {

        clientSocket = socket.accept();
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        output = new DataOutputStream(clientSocket.getOutputStream());
    }


    private void readAndReturn() throws IOException {

        String message = input.readLine();
        System.out.println(message);

        processedMessage = message.split(" ");

        if (!processedMessage[0].equals("GET")) {

            output.writeBytes("HTTP/1.0 405 Method Not Allowed\r\n");
            output.flush();
            return;
        }

        FileInputStream fileReader;

        if (!fileExists()) {

            buffer = new byte[1024];

            try {

                fileReader = new FileInputStream(file);

                headerNotFound = "HTTP/1.0 404 Not Found\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + file.length() + " \r\n \r\n";

                output.writeBytes(headerNotFound);

                int num;

                while ((num = fileReader.read(buffer)) != -1) {

                    output.write(buffer, 0, num);
                    output.flush();
                }

                fileReader.close();

            } catch (IOException e) {

                System.err.println(e.getMessage());
            }

            return;
        }


        if (fileExists() && processedMessage[1].endsWith("png")) {

            try {

                buffer = new byte[1024];

                httpOkImage = "HTTP/1.0 200 OK\r\n" +
                        "Content-Type: image/.png \r\n" +
                        "Content-Length: " + file.length() + " \r\n \r\n";

                fileReader = new FileInputStream(file);

                output.writeBytes(httpOkImage);

                int num;

                while ((num = fileReader.read(buffer)) != -1) {

                    output.write(buffer, 0, num);
                    output.flush();
                }
                fileReader.close();

            } catch (IOException e) {

                System.err.println(e.getMessage());
            }
            return;
        }

        try {

            buffer = new byte[1024];

            headerOkFile = "HTTP/1.0 200 OK\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: " + file.length() + " \r\n \r\n";


            output.writeBytes(headerOkFile);
            fileReader = new FileInputStream(file);

            int num;

            while ((num = fileReader.read(buffer)) != -1) {
                //output.flush().sout
                output.write(buffer, 0, num);
                output.flush();
            }
            fileReader.close();

        } catch (IOException e) {

            System.err.println(e.getMessage());
        }
    }


    private boolean fileExists() {

        path = directoryPath + processedMessage[1];
        file = new File(path);

        if (!file.exists()) {

            file = new File(directoryPath + "/404FileNotFound.html");
            return false;
        }

        return true;
    }


    private void close() throws IOException {

        clientSocket.close();
        input.close();
        output.close();
    }
}