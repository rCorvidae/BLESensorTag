package pl.projektorion.krzysztof.blesensortag.database.commands;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by krzysztof on 01.12.16.
 */

public class DBRowWriter {
    private ConcurrentLinkedQueue<DBRowWriteInterface> writeCmds;
    private SQLiteDatabase db;
    private long rootRowId;

    public DBRowWriter(SQLiteDatabase db, long rootRowId) {
        this.db = db;
        this.rootRowId = rootRowId;
        this.writeCmds = new ConcurrentLinkedQueue<>();
    }

    public void add(DBRowWriteInterface writeCmd)
    {
        writeCmds.add(writeCmd);
    }

    public boolean isEmpty() { return writeCmds.isEmpty(); }

    public void write()
    {
        db.beginTransaction();
        try {
            while (!writeCmds.isEmpty()) {
                DBRowWriteInterface dbWrite = writeCmds.poll();
                if (dbWrite == null) return;
                dbWrite.execute();
                Log.i("WRITE", "Data Written");
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public SQLiteDatabase getWritableDatabase()
    {
        return db;
    }

    public long getRootRowId()
    {
        return rootRowId;
    }
}
