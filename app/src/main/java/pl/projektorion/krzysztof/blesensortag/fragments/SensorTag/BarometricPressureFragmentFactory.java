package pl.projektorion.krzysztof.blesensortag.fragments.SensorTag;

import android.app.Fragment;

import java.util.Observable;

import pl.projektorion.krzysztof.blesensortag.fragments.AbstractFragmentFactory;

/**
 * Created by krzysztof on 06.11.16.
 */

public class BarometricPressureFragmentFactory extends AbstractFragmentFactory {
    public BarometricPressureFragmentFactory(Observable observable) {
        super(observable);
    }

    @Override
    protected Fragment create_new_fragment() {
        return new BarometricPressureFragment();
    }
}