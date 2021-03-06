package pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces;

import java.util.UUID;

import pl.projektorion.krzysztof.blesensortag.bluetooth.interfaces.GenericGattProfileInterface;

/**
 * Created by krzysztof on 02.11.16.
 */

public interface NotifyGattProfileInterface extends GenericGattProfileInterface {
    int ENABLE_ALL_MEASUREMENTS = Integer.MAX_VALUE;
    int DISABLE_ALL_MEASUREMENTS = Integer.MIN_VALUE;

    /**
     * Enables notifications on a BLE device. See child classes for
     * implementation details
     * @param state True to enable notification. False otherwise.
     */
    void enableNotification(boolean state);

    /**
     * Return value whether device is notifying
     * @return Boolean, true if it is notifying, false otherwise
     */
    boolean isNotifying();

    /**
     * Enable sensor measurements.
     * @param state ENABLE_ALL_MEASUREMENTS to enable sensor. DISABLE_ALL_MEASUREMENTS otherwise.
     *              Other values may be specify if the device needs it.
     */
    void enableMeasurement(int state);

    /**
     * Return value whether device is measuring
     * @return Integer, usually ENABLE_ALL_MEASUREMENTS/DISABLE_ALL_MEASUREMENTS value.
     * Other values may be specified in child classes
     */
    int isMeasuring();

    /**
     * Configure a notification update time period.
     * @param input See child classes.
     */
    void configurePeriod(int input);

    /**
     * Get notify period in milliseconds
     * @return integer in milliseconds
     */
    double getPeriod();

    /**
     * Get UUID that contains data
     * @return {@link} UUID for comparison
     */
    UUID getDataUuid();
}
