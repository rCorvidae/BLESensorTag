package pl.projektorion.krzysztof.blesensortag.bluetooth;

import android.bluetooth.BluetoothGattCharacteristic;

import java.util.UUID;

import pl.projektorion.krzysztof.blesensortag.bluetooth.notify.GenericGattNotifyModelInterface;
import pl.projektorion.krzysztof.blesensortag.bluetooth.read.GenericGattReadModelInterface;

/**
 * Created by krzysztof on 12.11.16.
 */

public class GenericNullModel extends AbstractGenericGattModel
        implements GenericGattReadModelInterface, GenericGattNotifyModelInterface {
    public GenericNullModel() {
        super();
    }

    @Override
    protected Object data_to_notify(BluetoothGattCharacteristic characteristic) {
        return null;
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public UUID getDataUuid() {
        return null;
    }

    @Override
    public byte[] getRawData() {
        return new byte[0];
    }

    @Override
    public boolean hasCharacteristic(BluetoothGattCharacteristic characteristic) {
        return false;
    }
}
