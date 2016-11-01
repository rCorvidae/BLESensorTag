package pl.projektorion.krzysztof.blesensortag.bluetooth.commands;

import android.bluetooth.BluetoothGattCharacteristic;

import pl.projektorion.krzysztof.blesensortag.bluetooth.BLeGattIO;
import pl.projektorion.krzysztof.blesensortag.utils.Command;
import pl.projektorion.krzysztof.blesensortag.utils.CommandAbstract;

/**
 * Created by krzysztof on 01.11.16.
 */

public class BLeNotificationEnableCommand extends CommandAbstract {
    private BLeGattIO gattClient;
    private BluetoothGattCharacteristic dataCharacteristic;
    private boolean state;

    public BLeNotificationEnableCommand(BLeGattIO gattClient) {
        init(gattClient, null, false);
    }

    public BLeNotificationEnableCommand(BLeGattIO gattClient,
                                        BluetoothGattCharacteristic dataCharacteristic, boolean state) {
        init(gattClient, dataCharacteristic, state);
    }

    public void setData(BluetoothGattCharacteristic dataCharacteristic, boolean state)
    {
        this.dataCharacteristic = dataCharacteristic;
        this.state = state;
    }

    @Override
    public void execute() {
        if( gattClient == null || dataCharacteristic == null )
            return;
        gattClient.setNotificationEnable(dataCharacteristic, state);
    }

    @Override
    public boolean autoContinue() {
        return true;
    }

    private void init(BLeGattIO gattClient, BluetoothGattCharacteristic dataCharacteristic,
                      boolean state)
    {
        this.gattClient = gattClient;
        this.dataCharacteristic = dataCharacteristic;
        this.state = state;
    }
}
