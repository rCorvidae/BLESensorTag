package pl.projektorion.krzysztof.blesensortag.database.selects.sensors.Barometer;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import pl.projektorion.krzysztof.blesensortag.database.selects.DBSelectInterface;

/**
 * Created by krzysztof on 22.12.16.
 */

public class DBSelectBarometerData implements DBSelectInterface {
    public static final int ATTRIBUTE_TIME = 0x10;
    public static final int ATTRIBUTE_BAROMETRIC_PRESSURE = 0x11;
    public static final int ATTRIBUTE_TEMPERATURE = 0x12;

    public static final String CSV_HEADER = "time,pressure,temperature";

    private double time = 0;
    private double barometricPressure = 0.0f;
    private double temperature = 0.0f;

    public DBSelectBarometerData(double sensorTime, Cursor cursor) {
        this.time = sensorTime;
        parse(cursor);
    }

    public DBSelectBarometerData(Parcel in)
    {
        time = in.readDouble();
        barometricPressure = in.readDouble();
        temperature = in.readDouble();
    }

    @Override
    public Object getData(int recordAttribute) {
        switch (recordAttribute)
        {
            case ATTRIBUTE_TIME:
                return time;
            case ATTRIBUTE_BAROMETRIC_PRESSURE:
                return barometricPressure;
            case ATTRIBUTE_TEMPERATURE:
                return temperature;
            case ATTRIBUTE_CSV_HEADER:
                return CSV_HEADER;
            default:
                return null;
        }
    }

    private void parse(Cursor cursor)
    {
        barometricPressure = cursor.getDouble(0);
        temperature = cursor.getDouble(1);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(time);
        dest.writeDouble(barometricPressure);
        dest.writeDouble(temperature);
    }

    @Override
    public String toString() {
        return "" + time +
                "," + barometricPressure +
                "," + temperature;
    }

    public static final Parcelable.Creator<DBSelectBarometerData> CREATOR =
            new Creator<DBSelectBarometerData>() {
        @Override
        public DBSelectBarometerData createFromParcel(Parcel source) {
            return new DBSelectBarometerData(source);
        }

        @Override
        public DBSelectBarometerData[] newArray(int size) {
            return new DBSelectBarometerData[size];
        }
    };
}
