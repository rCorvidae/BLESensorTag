package pl.projektorion.krzysztof.blesensortag.bluetooth.notifications;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces.NotifyGattProfileInterface;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces.ProfileNotifyFactory;

/**
 * Created by krzysztof on 02.11.16.
 */

public class GattProfileFactory {
    private Map<UUID, ProfileNotifyFactory> methodFactories;

    public GattProfileFactory() {
        this.methodFactories = new HashMap<>();
    }

    public void put(UUID serviceUuid, ProfileNotifyFactory factory)
    {
        try {
            this.methodFactories.put(serviceUuid, factory);
        } catch (Exception e){}
    }

    public NotifyGattProfileInterface createProfile(UUID serviceUuid) {
        ProfileNotifyFactory factory = methodFactories.get(serviceUuid);
        if( factory != null )
            return factory.createProfile();
        else
            return new NullProfile();
    }
}
