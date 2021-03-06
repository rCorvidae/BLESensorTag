package pl.projektorion.krzysztof.blesensortag.database.selects.sensors.Movement;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pl.projektorion.krzysztof.blesensortag.AppContext;
import pl.projektorion.krzysztof.blesensortag.R;
import pl.projektorion.krzysztof.blesensortag.database.selects.DBSelectInterface;
import pl.projektorion.krzysztof.blesensortag.database.selects.abstracts.DBSelectSensorParamAbstract;
import pl.projektorion.krzysztof.blesensortag.database.tables.sensors.Movement.DBTableMovementParam;

/**
 * Created by krzysztof on 20.12.16.
 */

public class DBSelectMovementParam extends DBSelectSensorParamAbstract {
    private DBSelectMovementParamData movementRecord;

    public DBSelectMovementParam(DBSelectInterface rootRecord) {
        super(rootRecord);
        this.movementRecord = new DBSelectMovementParamData();
    }

    @Override
    protected String column_id() {
        return DBTableMovementParam.COLUMN_ID;
    }

    @Override
    protected String column_root_ref() {
        return DBTableMovementParam.COLUMN_ROOT_REF;
    }

    @Override
    protected String column_notify_interval() {
        return DBTableMovementParam.NOTIFY_INTERVAL;
    }

    @Override
    protected String table_name() {
        return DBTableMovementParam.TABLE_NAME;
    }

    @Override
    public void onQueryExecuted(Cursor cursor) {
        movementRecord = new DBSelectMovementParamData(cursor);
    }

    @Override
    public DBSelectInterface getRecord() {
        return movementRecord;
    }

    @Override
    public List<? extends DBSelectInterface> getRecords() {
        List<DBSelectInterface> records = new ArrayList<>();
        records.add(movementRecord);
        return records;
    }

    @Override
    public String getLabel() {
        return AppContext.getContext().getString(R.string.label_acc_sensor);
    }
}
