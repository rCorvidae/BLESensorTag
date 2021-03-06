package pl.projektorion.krzysztof.blesensortag.database.inserts.sensors.IRTemperature;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Observable;

import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.IRTemperature.IRTemperatureData;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notifications.interfaces.ProfileData;
import pl.projektorion.krzysztof.blesensortag.database.commands.DBRowWriteCommand;
import pl.projektorion.krzysztof.blesensortag.database.commands.DBRowWriter;
import pl.projektorion.krzysztof.blesensortag.database.inserts.abstracts.DBInsertAbstract;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.IRTemperature.DBTableIRTemperature;

/**
 * Created by krzysztof on 01.12.16.
 */

public class DBInsertIRTemperature extends DBInsertAbstract {
    private Observable observable;


    public DBInsertIRTemperature(DBRowWriter dbWriter, long rootRowId) {
        super(dbWriter, rootRowId, DBTableIRTemperature.TABLE_NAME);
    }

    public DBInsertIRTemperature(DBRowWriter dbWriter) {
        super(dbWriter, DBTableIRTemperature.TABLE_NAME);
    }

    @Override
    public void update(Observable o, Object arg) {
        observable = o;
        ProfileData data = (ProfileData) arg;
        final double object_temperature = data.getValue(IRTemperatureData.ATTRIBUTE_OBJECT_TEMPERATURE);
        final double ambient_temperature = data.getValue(IRTemperatureData.ATTRIBUTE_AMBIENT_TEMPERATURE);

        ContentValues dbValues = new ContentValues();
        dbValues.put(DBTableIRTemperature.COLUMN_ROOT_REF, rootRowId);
        dbValues.put(DBTableIRTemperature.COLUMN_OBJ_TEMP, object_temperature);
        dbValues.put(DBTableIRTemperature.COLUMN_AMBIENT_TEMP, ambient_temperature);

        final SQLiteDatabase db = dbWriter.getWritableDatabase();
        dbWriter.add(new DBRowWriteCommand(db, getTableName(), dbValues));
    }
}
