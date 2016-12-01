package pl.projektorion.krzysztof.blesensortag.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by krzysztof on 01.12.16.
 */

public class DBRootTableRecord implements DBTableInterface {
    public static final String TABLE_NAME = "Record";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "DATE";

    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DATE + " INTEGER NOT NULL);";

    public DBRootTableRecord() {
    }

    @Override
    public void createTable(SQLiteDatabase db)
    {
        if( db == null ) return;
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}