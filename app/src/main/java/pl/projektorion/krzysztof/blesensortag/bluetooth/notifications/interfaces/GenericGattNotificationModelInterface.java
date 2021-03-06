package pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces;

import android.bluetooth.BluetoothGattCharacteristic;

import java.util.UUID;

import pl.projektorion.krzysztof.blesensortag.bluetooth.interfaces.GenericGattModelInterface;

/**
 * Created by krzysztof on 02.11.16.
 */


/**
 * Observer pattern for SensorTag profiles. Here: interface for an observer
 */
public interface GenericGattNotificationModelInterface extends GenericGattModelInterface {

    /**
     * Update Characteristic
     * @param characteristic {@link BluetoothGattCharacteristic} data changed
     */
    void updateCharacteristic(BluetoothGattCharacteristic characteristic);

    /**
     * Get UUID that contains data
     * @return {@link} UUID for comparison
     */
    UUID getDataUuid();

    /**
     * Get unprocessed data received from the BLE device
     * @return Byte array data set.
     */
    byte[] getRawData();
}
