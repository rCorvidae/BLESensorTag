package pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.abstracts;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import java.util.UUID;

import pl.projektorion.krzysztof.blesensortag.bluetooth.interfaces.BLeGattIO;
import pl.projektorion.krzysztof.blesensortag.bluetooth.commands.BLeCharacteristicWriteCommand;
import pl.projektorion.krzysztof.blesensortag.bluetooth.commands.BLeNotificationDisableWriteCommand;
import pl.projektorion.krzysztof.blesensortag.bluetooth.commands.BLeNotificationEnableWriteCommand;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces.NotifyGattProfileInterface;

/**
 * Created by krzysztof on 06.11.16.
 */

public abstract class AbstractNotifyGattProfile implements NotifyGattProfileInterface {
    protected static final byte[] CONFIG_ENABLE = {0x01};
    protected static final byte[] CONFIG_DISABLE = {0x00};

    protected BLeGattIO gattClient;
    private BluetoothGattService service;

    private boolean isNotifying;
    private boolean isMeasuring;

    private double notifyPeriodMs;

    public AbstractNotifyGattProfile(BLeGattIO gattClient, double defaultNotifyPeriodMs) {
        this.gattClient = gattClient;
        this.isMeasuring = false;
        this.isNotifying = false;
        this.notifyPeriodMs = defaultNotifyPeriodMs;
    }

    /**
     * Enable notification. The request is sent according to the documentation
     * of SensorTag (data UUID). This method also configures a descriptor 0x2902 specified
     * at https://www.bluetooth.com/specifications/gatt/viewer?attributeXmlFile=org.bluetooth.descriptor.gatt.client_characteristic_configuration.xml
     * @param state True to enable notification. False otherwise.
     */
    @Override
    public void enableNotification(boolean state) {
        if (isNotifying == state) return;
        isNotifying = state;

        service = get_service();
        if (service == null) return;
        final BluetoothGattCharacteristic notify = service.getCharacteristic(get_data_uuid());

        if (state)
            gattClient.addWrite(new BLeNotificationEnableWriteCommand(gattClient, notify));
        else
            gattClient.addWrite(new BLeNotificationDisableWriteCommand(gattClient, notify));
    }

    @Override
    public boolean isNotifying() {
        return isNotifying;
    }

    /**
     * Enable remote SensorTag sensor.
     * @param state True to enable sensor. False otherwise.
     */
    @Override
    public void enableMeasurement(int state) {
        final boolean requestState = state == ENABLE_ALL_MEASUREMENTS;
        if( isMeasuring == requestState ) return;
        isMeasuring = requestState;

        service = get_service();
        if( service == null ) return;

        final byte[] cmd = requestState ? CONFIG_ENABLE : CONFIG_DISABLE;
        final BluetoothGattCharacteristic measure = service.getCharacteristic(get_config_uuid());
        measure.setValue(cmd);
        gattClient.addWrite(new BLeCharacteristicWriteCommand(gattClient, measure));
    }

    @Override
    public int isMeasuring() {
        return isMeasuring ? ENABLE_ALL_MEASUREMENTS : DISABLE_ALL_MEASUREMENTS;
    }

    /**
     * Configure update time span of the notification. If a value is invalid or
     * the method cannot get a current service then returns.
     * @param input Byte value. Minimum: 10 (100ms), Maximum 255 (2.55sec)
     */
    @Override
    public void configurePeriod(int input) {
        service = get_service();
        byte bValue = (byte) input;
        if( service == null ) return;
        if( (input&0xff) < 0xA) return;

        notifyPeriodMs = computeMillisecondsForPeriod(input);

        final BluetoothGattCharacteristic period = service.getCharacteristic(get_period_uuid());
        period.setValue(new byte[] { bValue });
        gattClient.addWrite(new BLeCharacteristicWriteCommand(gattClient, period));
    }

    @Override
    public double getPeriod() {
        return notifyPeriodMs;
    }

    @Override
    public UUID getDataUuid() {
        return get_data_uuid();
    }

    @Override
    public boolean isService(UUID serviceUuid) {
        final UUID instanceService = get_service_uuid();
        return instanceService.equals(serviceUuid);
    }

    /**
     * Get {@link BluetoothGattService}
     * @return {@link BluetoothGattService}, may return null.
     * See the reference for more details.
     */
    protected abstract BluetoothGattService get_service();

    /**
     * Get {@link UUID} of the service
     * @return Service UUID
     */
    protected abstract UUID get_service_uuid();

    /**
     * Get UUID that is responsible for reading/writing values
     * from the device
     * @return {@link UUID} Return UUID that contains data.
     */
    protected abstract UUID get_data_uuid();

    /**
     * Get UUID that is responsible for starting/stopping the cycle
     * of measurement. The UUID should be SensorTag specific.
     * @return Return configuraion {@link UUID}
     */
    protected abstract UUID get_config_uuid();

    /**
     * Get UUID that is responsible for configuration of the
     * update time span. The UUID should be SensorTag specific.
     * The period value is written directly to {@link BluetoothGattCharacteristic}.
     * @return Return {@link UUID} responsible for configuration update time span.
     */
    protected abstract UUID get_period_uuid();


    private double computeMillisecondsForPeriod(double value)
    {
        return 10.0f * value;
    }
}
