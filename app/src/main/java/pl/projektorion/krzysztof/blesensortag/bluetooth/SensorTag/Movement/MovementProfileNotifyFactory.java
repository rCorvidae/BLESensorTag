package pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.Movement;

import pl.projektorion.krzysztof.blesensortag.bluetooth.BLeGattIO;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notify.GenericGattNotifyProfileInterface;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notify.ProfileNotifyFactory;

/**
 * Created by krzysztof on 07.11.16.
 */

public class MovementProfileNotifyFactory implements ProfileNotifyFactory {
    private BLeGattIO gattIO;

    public MovementProfileNotifyFactory(BLeGattIO gattIO) {
        this.gattIO = gattIO;
    }

    @Override
    public GenericGattNotifyProfileInterface createProfile() {
        return new MovementNotifyProfile(gattIO);
    }
}