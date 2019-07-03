
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class InetAddressTestMain {

    public static void main(String[] args) {

        //String test = "www.stallman.com";
        String host = getHost();

        try {

            InetAddress inetAddress = InetAddress.getByName(host);
            System.out.println("Testing reachability for " + inetAddress.getHostAddress() + " :");
            System.out.println(inetAddress.isReachable(149)? "OK" : "FAIL");

        } catch (UnknownHostException e) {

            System.err.println("Invalid host name: " + host);

        } catch (IOException e) {

            System.err.println(e.getMessage());

        }
    }

    private static String getHost() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Hostname? ");
        return reader.nextLine();
    }
}

