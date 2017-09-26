/**
 * Created by Вова on 09.09.2017.
 */
import org.jnativehook.NativeHookException;

import java.lang.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {

    private static int portNumber = 4444;
    public static void main(String[] args) throws IOException{

        // Clients' processes
        if (args.length > 0) {
            new Client(args[0]); // args[0] - type of function
        }
        // Server process
        else {
            new Controller(portNumber); // server
        }
    }
}
