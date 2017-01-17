package pl.projektorion.krzysztof.blesensortag.math.algorithms;

import pl.projektorion.krzysztof.blesensortag.math.interfaces.MAlgorithm;
import pl.projektorion.krzysztof.blesensortag.math.interfaces.MDerivativeMask;
import pl.projektorion.krzysztof.blesensortag.math.MSignalVector;

/**
 * Created by krzysztof on 17.01.17.
 */

public class MAlgorithmForwardDifference implements MAlgorithm {
    private MSignalVector data;

    public MAlgorithmForwardDifference() {
    }

    public MAlgorithmForwardDifference(MSignalVector data) {
        this.data = data;
    }

    @Override
    public void setData(MSignalVector data) {
        this.data = data;
    }

    @Override
    public boolean hasData() {
        return data != null && !data.getList().isEmpty();
    }

    @Override
    public MSignalVector compute() {
        return new MSignalVector(data.convolve(MDerivativeMask.FORWARD_DIFFERENCE));
    }
}