package pl.projektorion.krzysztof.blesensortag.fragments.presentation.SensorTag;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import pl.projektorion.krzysztof.blesensortag.R;
import pl.projektorion.krzysztof.blesensortag.bluetooth.SensorTag.BarometricPressure.BarometricPressureData;
import pl.projektorion.krzysztof.blesensortag.bluetooth.notify.ProfileData;
import pl.projektorion.krzysztof.blesensortag.fragments.presentation.DoubleChartFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarometricPressureFragment extends DoubleChartFragment
    implements Observer {

    private View view;
    private Observable observable;
    private Handler handler;

    private static final float FONT_SIZE = 14.5f;

    public BarometricPressureFragment() {
        super();
    }

    @Override
    public void update(Observable o, Object arg) {
        observable = o;
        ProfileData data = (ProfileData) arg;
        final float pressure = (float) data.getValue(BarometricPressureData.ATTRIBUTE_PRESSURE_hPa);
        final float temperature = (float) data.getValue(BarometricPressureData.ATTRIBUTE_CENTIGRADE);

        handler.post(new Runnable() {
            @Override
            public void run() {
                update_upper_chart(pressure);
                update_lower_chart(temperature);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        handler = new Handler();
        return view;
    }

    @Override
    public void onDestroy() {
        if( observable != null )
            observable.deleteObserver(this);

        super.onDestroy();
    }

    @Override
    protected String get_upper_title() {
        return getString(R.string.label_barometric_pressure);
    }

    @Override
    protected String get_lower_title() {
        return getString(R.string.label_temperature);
    }

    @Override
    protected int get_upper_chart_color() {
        return Color.BLUE;
    }

    @Override
    protected int get_lower_chart_color() {
        return Color.RED;
    }

    @Override
    protected String get_upper_measure_unit() {
        return getString(R.string.label_barometric_pressure_unit);
    }

    @Override
    protected String get_lower_measure_unit() {
        return getString(R.string.label_temperature_unit);
    }

    @Override
    protected float get_description_font_size() {
        return FONT_SIZE;
    }
}
