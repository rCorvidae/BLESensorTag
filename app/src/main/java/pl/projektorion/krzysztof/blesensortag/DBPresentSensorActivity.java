package pl.projektorion.krzysztof.blesensortag;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import pl.projektorion.krzysztof.blesensortag.database.selects.DBSelectInterface;
import pl.projektorion.krzysztof.blesensortag.fragments.database.DBPresentBarometerFragment;
import pl.projektorion.krzysztof.blesensortag.fragments.database.DBPresentHumidityFragment;


public class DBPresentSensorActivity extends Activity {

    public static final String EXTRA_ROOT_RECORD =
            "pl.projektorion.krzysztof.blesensortag.extra.ROOT_RECORD";

    public static final String EXTRA_SENSOR_RECORD =
            "pl.projektorion.krzysztof.blesensortag.extra.SENSOR_RECORD";

    public static final String EXTRA_SENSOR_LABEL =
            "pl.projektorion.krzysztof.blesensortag.extra.SENSOR_LABEL";

    public static final String NEGOTIATE_FRAGMENT_TAG =
            "pl.projektorion.krzysztof.blesensortag.tag.NEGOTIATE_FRAGMENT";

    private DBSelectInterface rootRecord;
    private DBSelectInterface sensorRecord;
    private String sensorLabel;

    private TextView sensorPresentationLabel;
    private FrameLayout fragmentSink;

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbpresent_sensor);

        if( !restore_saved_instance(savedInstanceState) )
            acquire_data();
        init_widgets();

        negotiate_fragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_ROOT_RECORD, rootRecord);
        outState.putParcelable(EXTRA_SENSOR_RECORD, sensorRecord);
        outState.putString(EXTRA_SENSOR_LABEL, sensorLabel);
    }

    private boolean restore_saved_instance(Bundle savedInstanceState)
    {
        if( savedInstanceState == null )
            return false;
        rootRecord = savedInstanceState.getParcelable(EXTRA_ROOT_RECORD);
        sensorRecord = savedInstanceState.getParcelable(EXTRA_SENSOR_RECORD);
        sensorLabel = savedInstanceState.getString(EXTRA_SENSOR_LABEL);
        return true;
    }

    private void acquire_data() throws NullPointerException
    {
        final Intent intent = getIntent();
        rootRecord = intent.getParcelableExtra(EXTRA_ROOT_RECORD);
        sensorRecord = intent.getParcelableExtra(EXTRA_SENSOR_RECORD);
        sensorLabel = intent.getStringExtra(EXTRA_SENSOR_LABEL);

        if( rootRecord == null || sensorRecord == null || sensorLabel == null)
            throw new NullPointerException("No data acquired. Pass EXTRAS!");

        Log.i("Data", rootRecord.toString());
        Log.i("Sensor", sensorRecord.toString());
        Log.i("TableName", sensorLabel);
    }

    private void init_widgets()
    {
        sensorPresentationLabel = (TextView) findViewById(R.id.sensor_presentation_label);
        fragmentSink = (FrameLayout) findViewById(R.id.db_presentation_container);

        sensorPresentationLabel.setText(sensorLabel);
    }

    private void negotiate_fragment()
    {
        FragmentManager fm = getFragmentManager();
        fragment = fm.findFragmentByTag(NEGOTIATE_FRAGMENT_TAG);
        if( fragment == null )
        {
            fragment = DBPresentHumidityFragment.newInstance(rootRecord, sensorRecord);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(fragment, NEGOTIATE_FRAGMENT_TAG);
            ft.replace(R.id.db_presentation_container, fragment);
            ft.commit();
        }
    }
}