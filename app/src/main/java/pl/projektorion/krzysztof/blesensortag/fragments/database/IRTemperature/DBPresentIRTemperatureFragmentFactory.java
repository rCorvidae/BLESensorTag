package pl.projektorion.krzysztof.blesensortag.fragments.database.IRTemperature;

import android.app.Fragment;

import pl.projektorion.krzysztof.blesensortag.database.selects.DBSelectInterface;
import pl.projektorion.krzysztof.blesensortag.fragments.FragmentFactory;

/**
 * Created by krzysztof on 26.12.16.
 */

public class DBPresentIRTemperatureFragmentFactory implements FragmentFactory {
    private DBSelectInterface rootRecord;
    private DBSelectInterface sensorRecord;

    public DBPresentIRTemperatureFragmentFactory(DBSelectInterface rootRecord, DBSelectInterface sensorRecord) {
        this.rootRecord = rootRecord;
        this.sensorRecord = sensorRecord;
    }

    @Override
    public Fragment create() {
        return DBPresentIRTemperatureFragment.newInstance(rootRecord, sensorRecord);
    }
}
