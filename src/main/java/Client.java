import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Created by Вова on 17.09.2017.
 */

class Client {

    private int portNumber;
    Client(String typeOfFunction){

        if( typeOfFunction.equals("F")) portNumber = 4444; else if( typeOfFunction.equals("G")) portNumber = 1234;

        try (
                Socket echoSocket = new Socket(InetAddress.getLocalHost().getHostAddress(), portNumber);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in));
        ){
            Boolean result = null;

            int X = in.read();

            if(typeOfFunction.equals("F")){
                result = someFunction0(X);
            }
            else if(typeOfFunction.equals("G")){
                result = someFunction1(X);
            }


            if(result){
                out.println("true");
            }
            else if(!result){
                out.println("false");
            }

            echoSocket.close();
        }
        catch(IOException | InterruptedException  | NullPointerException e) {
            e.printStackTrace();
        }
    }


    private static boolean someFunction0(int x)throws InterruptedException{

        switch(x){
            case 1: sleep(1000); return true;
            case 2: sleep(4000); return true;
            case 3: sleep(1000); return false;
            case 4: sleep(Long.MAX_VALUE);
            case 5: sleep(2000); return true;
            case 6: sleep(Long.MAX_VALUE);
        }
        sleep(5000);
        return true;
    }

    private static boolean someFunction1(int x)throws InterruptedException{

        switch(x){
            case 1: sleep(4000); return true;
            case 2: sleep(1000); return true;
            case 3: sleep(Long.MAX_VALUE);
            case 4: sleep(6000); return false;
            case 5: sleep(Long.MAX_VALUE);
            case 6: sleep(2000); return true;
        }
        sleep(15000);
        return false;
    }
}
