package pl.projektorion.krzysztof.blesensortag.factories;

import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.BarometricPressure.BarometricPressureProfile;
import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.Humidity.HumidityProfile;
import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.IRTemperature.IRTemperatureProfile;
import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.Movement.MovementProfile;
import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.OpticalSensor.OpticalSensorProfile;
import pl.projektorion.krzysztof.blesensortag.database.tables.DBTableFactory;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.Barometer.DBTableBarometerFactory;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.HeartRate.DBTableHeartRateFactory;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.Humidity.DBTableHumidityFactory;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.IRTemperature.DBTableIRTemperatureFactory;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.Movement.DBTableMovementFactory;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.OpticalSensor.DBTableOpticalSensorFactory;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.Stethoscope.DBTableStethoscopeFactory;

/**
 * Created by krzysztof on 19.12.16.
 */

public class DBFactoryTables extends DBTableFactory {
    public DBFactoryTables() {
        super();

        init_tables();
    }

    private void init_tables()
    {
        add( new DBTableBarometerFactory() );
        add( new DBTableHumidityFactory() );
        add( new DBTableIRTemperatureFactory() );
        add( new DBTableMovementFactory() );
        add( new DBTableOpticalSensorFactory() );
        add( new DBTableStethoscopeFactory() );
        add( new DBTableHeartRateFactory() );
    }
}
