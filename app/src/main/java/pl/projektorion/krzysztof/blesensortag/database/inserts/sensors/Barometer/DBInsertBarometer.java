package pl.projektorion.krzysztof.blesensortag.database.inserts.sensors.Barometer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Observable;

import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.BarometricPressure.BarometricPressureData;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces.ProfileData;
import pl.projektorion.krzysztof.blesensortag.database.commands.DBRowWriteCommand;
import pl.projektorion.krzysztof.blesensortag.database.commands.DBRowWriter;
import pl.projektorion.krzysztof.blesensortag.database.inserts.abstracts.DBInsertAbstract;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.Barometer.DBTableBarometer;

/**
 * Created by krzysztof on 01.12.16.
 */

public class DBInsertBarometer extends DBInsertAbstract {
    private Observable observable;

    public DBInsertBarometer(DBRowWriter dbWriter, long rootRowId) {
        super(dbWriter, rootRowId, DBTableBarometer.TABLE_NAME);
    }

    public DBInsertBarometer(DBRowWriter dbWriter) {
        super(dbWriter, DBTableBarometer.TABLE_NAME);
    }

    @Override
    public void update(Observable o, Object arg) {
        observable = o;
        ProfileData data = (ProfileData) arg;
        final double pressure = data.getValue(BarometricPressureData.ATTRIBUTE_PRESSURE_hPa);
        final double temperature = data.getValue(BarometricPressureData.ATTRIBUTE_CENTIGRADE);

        ContentValues dbRowValues = new ContentValues();
        dbRowValues.put(DBTableBarometer.COLUMN_ROOT_REF, rootRowId);
        dbRowValues.put(DBTableBarometer.COLUMN_PRESSURE, pressure);
        dbRowValues.put(DBTableBarometer.COLUMN_TEMPERATURE, temperature);

        final SQLiteDatabase db = dbWriter.getWritableDatabase();
        dbWriter.add(new DBRowWriteCommand(db, getTableName(), dbRowValues));
    }
}
