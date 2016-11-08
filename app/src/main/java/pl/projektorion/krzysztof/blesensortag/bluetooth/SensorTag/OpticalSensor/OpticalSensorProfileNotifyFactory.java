package pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.OpticalSensor;

import pl.projektorion.krzysztof.blesensortag.bluetooth.BLeGattIO;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notify.GenericGattNotifyProfileInterface;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notify.ProfileNotifyFactory;

/**
 * Created by krzysztof on 07.11.16.
 */

public class OpticalSensorProfileNotifyFactory implements ProfileNotifyFactory {
    private BLeGattIO gattIO;

    public OpticalSensorProfileNotifyFactory(BLeGattIO gattIO) {
        this.gattIO = gattIO;
    }

    @Override
    public GenericGattNotifyProfileInterface createProfile() {
        return new OpticalSensorNotifyProfile(gattIO);
    }
}