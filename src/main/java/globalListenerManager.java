import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.jnativehook.GlobalScreen.registerNativeHook;
import static org.jnativehook.GlobalScreen.unregisterNativeHook;

/**
 * Created by Вова on 20.09.2017.
 */
public class globalListenerManager {
    public globalListenerManager(){
        try {

            LogManager.getLogManager().reset();
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
            registerNativeHook();
        }

        catch(NativeHookException e){
            e.printStackTrace();
        }
    }

    public void globalListenerManagerShutDown(){
        try {
            unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}
