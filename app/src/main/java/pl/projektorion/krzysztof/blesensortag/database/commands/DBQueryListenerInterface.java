package pl.projektorion.krzysztof.blesensortag.database.commands;

import android.database.Cursor;

import pl.projektorion.krzysztof.blesensortag.database.selects.DBSelectInterface;

/**
 * Created by krzysztof on 20.12.16.
 */

public interface DBQueryListenerInterface {

    /**
     * Get string to be queried.
     * @return Get a string that is to be queried to the database
     */
    String getQuery();

    /**
     * Array of parameters if any needed.
     * @return Array of parameters
     */
    String[] getQueryData();

    /**
     * Command response. The data from cursor can be extracted directly.
     * No need to call {@link Cursor#moveToFirst()}.
     * @param cursor {@link Cursor} that contains data
     */
    void onQueryExecuted(Cursor cursor);

    /**
     * Table data representation as parsed object.
     * @return {@link DBSelectInterface} and its children as an output
     * of the query.
     */
    DBSelectInterface getRecord();
}