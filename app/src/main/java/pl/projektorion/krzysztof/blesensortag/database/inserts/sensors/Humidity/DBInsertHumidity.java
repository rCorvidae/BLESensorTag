package pl.projektorion.krzysztof.blesensortag.database.inserts.sensors.Humidity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Observable;

import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.Humidity.HumidityData;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces.ProfileData;
import pl.projektorion.krzysztof.blesensortag.database.commands.DBRowWriteCommand;
import pl.projektorion.krzysztof.blesensortag.database.commands.DBRowWriter;
import pl.projektorion.krzysztof.blesensortag.database.inserts.abstracts.DBInsertAbstract;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.Humidity.DBTableHumidity;

/**
 * Created by krzysztof on 01.12.16.
 */

public class DBInsertHumidity extends DBInsertAbstract {
    private Observable observable;


    public DBInsertHumidity(DBRowWriter dbWriter, long rootRowId) {
        super(dbWriter, rootRowId, DBTableHumidity.TABLE_NAME);
    }

    public DBInsertHumidity(DBRowWriter dbWriter) {
        super(dbWriter, DBTableHumidity.TABLE_NAME);
    }

    @Override
    public void update(Observable o, Object arg) {
        observable = o;
        ProfileData data = (ProfileData) arg;
        final double humidity = data.getValue(HumidityData.ATTRIBUTE_RELATIVE_HUMIDITY);
        final double temperature = data.getValue(HumidityData.ATTRIBUTE_TEMPERATURE_CELSIUS);

        ContentValues dbValues = new ContentValues();
        dbValues.put(DBTableHumidity.COLUMN_ROOT_REF, rootRowId);
        dbValues.put(DBTableHumidity.COLUMN_REL_HUMIDITY, humidity);
        dbValues.put(DBTableHumidity.COLUMN_TEMPERATURE, temperature);

        final SQLiteDatabase db = dbWriter.getWritableDatabase();
        dbWriter.add(new DBRowWriteCommand(db, getTableName(), dbValues));
    }
}
