
/**
 *
 * Code adapted from code available at The Pi4J Project on GitHub:
 * https://github.com/Pi4J/pi4j-example-components
 * 
 */

package com.mycompany.mavenproject1;

import com.pi4j.context.Context;
import java.util.logging.Logger;

/**
 * This interface should be implemented by each CrowPi example / application
 */

public interface IApplicationCamera {
    /**
     * Represents the main application entrypoint called by the launcher.
     * This should execute whatever the application is supposed to do.
     *
     * @param pi4j Pi4J context
     */
    void execute(Context pi4j);

    /**
     * Returns a unique name which is used to determine how the application can be called.
     * This uses the simple class name by default, so that the application has the same name as its class.
     *
     * @return Unique application name
     */
    default String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns a human-readable description what this application is doing.
     * This returns a short explanation together with the class FQDN by default.
     *
     * @return Human-readable application description
     */
    default String getDescription() {
        return this.getClass().getName();
    }

    /**
     * Utility function to sleep for the specified amount of milliseconds.
     * An {@link InterruptedException} will be catched and ignored while setting the interrupt flag again.
     *
     * @param milliseconds Time in milliseconds to sleep
     */
    default void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Logger instance
     */
    Logger logger = Logger.getLogger("Pi4J-App");

    default void logInfo(String msg){
        logger.info(() -> msg);
    }
    default void logError(String msg){
        logger.severe(() -> msg);
    }
}
