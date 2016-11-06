package pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import pl.projektorion.krzysztof.blesensortag.bluetooth.GenericGattObserverInterface;
import pl.projektorion.krzysztof.blesensortag.bluetooth.GenericGattProfileInterface;

/**
 * Created by krzysztof on 02.11.16.
 */

public class GattProfileFactory {
    private Map<UUID, ProfileFactory> methodFactories;

    public GattProfileFactory() {
        this.methodFactories = new HashMap<>();
    }

    public void put(UUID serviceUuid, ProfileFactory factory)
    {
        try {
            this.methodFactories.put(serviceUuid, factory);
        } catch (Exception e){}
    }

    public GenericGattProfileInterface createProfile(UUID serviceUuid) {
        ProfileFactory factory = methodFactories.get(serviceUuid);
        if( factory != null )
            return factory.createProfile();
        else
            return new NullProfile();
    }

    public GenericGattObserverInterface createObserver(UUID serviceUuid)
    {
        ProfileFactory factory = methodFactories.get(serviceUuid);
        if( factory != null )
            return factory.createObserver();
        else
            return new NullProfile();
    }
}