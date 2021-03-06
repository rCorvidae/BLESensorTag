package pl.projektorion.krzysztof.blesensortag.factories;

import java.util.ArrayList;
import java.util.List;

import pl.projektorion.krzysztof.blesensortag.database.commands.DBQueryListenerInterface;
import pl.projektorion.krzysztof.blesensortag.database.selects.sensors.Barometer.DBSelectBarometerParam;
import pl.projektorion.krzysztof.blesensortag.database.selects.DBSelectInterface;
import pl.projektorion.krzysztof.blesensortag.database.selects.sensors.HeartRate.DBSelectHeartRateParam;
import pl.projektorion.krzysztof.blesensortag.database.selects.sensors.Humidity.DBSelectHumidityParam;
import pl.projektorion.krzysztof.blesensortag.database.selects.sensors.IRTemperature.DBSelectIRTemperatureParam;
import pl.projektorion.krzysztof.blesensortag.database.selects.sensors.Movement.DBSelectMovementParam;
import pl.projektorion.krzysztof.blesensortag.database.selects.sensors.OpticalSensor.DBSelectOpticalSensorParam;
import pl.projektorion.krzysztof.blesensortag.database.selects.sensors.Stethoscope.DBSelectStethoscopeParam;

/**
 * Created by krzysztof on 20.12.16.
 */

public class DBFactoryParamSelects {
    private DBSelectInterface rootRecord;
    private List<DBQueryListenerInterface> queryListeners;

    public DBFactoryParamSelects(DBSelectInterface rootRecord) {
        this.rootRecord = rootRecord;
        init_query_listeners();
    }

    public List<DBQueryListenerInterface> getQueryListeners()
    {
        return queryListeners;
    }

    private void init_query_listeners()
    {
        queryListeners = new ArrayList<>();

        queryListeners.add(new DBSelectBarometerParam(rootRecord));
        queryListeners.add(new DBSelectHumidityParam(rootRecord));
        queryListeners.add(new DBSelectIRTemperatureParam(rootRecord));
        queryListeners.add(new DBSelectMovementParam(rootRecord));
        queryListeners.add(new DBSelectOpticalSensorParam(rootRecord));
        queryListeners.add(new DBSelectStethoscopeParam(rootRecord));
        queryListeners.add(new DBSelectHeartRateParam(rootRecord));
    }
}
