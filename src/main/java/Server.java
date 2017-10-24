import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Вова on 17.09.2017.
 */
public class Server implements Callable<Server.Status> {

    Logger LOGGER = Logger.getLogger( Server.class.getName() );
    private Socket clientSocket;
    private int x;
    private String typeOfFunction;

    Server(Socket clientSocket, int x, String typeOfFunction){
        this.clientSocket = clientSocket;
        LOGGER.log( Level.FINE, "Initialization of the server" );
        this.x = x;
        this.typeOfFunction = typeOfFunction;
    }

    @Override
    public Server.Status call() throws Exception {
        InputStream inp;
        BufferedReader brinp ;
        DataOutputStream out;
        Status status = new Status();

        try {
            inp = clientSocket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(clientSocket.getOutputStream());

            long time = System.nanoTime();

            out.writeBytes(typeOfFunction + "\n");
            out.write(x);

            LOGGER.log( Level.FINE, "Sending argument to the client" );

            String input = brinp.readLine();

            if (input.equals("true")) {
                status.setResult(true);
            } else if (input.equals("false")) {
                status.setResult(false);
            }
            time = System.nanoTime() - time;

            LOGGER.log( Level.FINE, "The result value was gained\n" );
            LOGGER.log( Level.FINE, "The execution time is:" + time);

            status.setRunningTime(time);

            return status;


        } catch (IOException e) {
            return null;
        }
    }
    class Status{

        private long RunningTime;
        private boolean result;

        Status(){
            RunningTime = -1;
        }

        public long getRunningTime(){
            return RunningTime;
        }

        public boolean getResult(){
            return result;
        }

        public void setRunningTime(long time){
            RunningTime = time;
        }

        public void setResult(boolean result){
            this.result = result;
        }
    }
}
