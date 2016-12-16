package pl.projektorion.krzysztof.blesensortag.database.tables;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by krzysztof on 02.12.16.
 */

public class DBTableFactory {
    private Map<UUID, DBTableFactoryInterface> tableFactories;

    public DBTableFactory() {
        this.tableFactories = new HashMap<>();
    }

    public void add(UUID serviceUuid, DBTableFactoryInterface tableFactory)
    {
        tableFactories.put(serviceUuid, tableFactory);
    }

    public void extend(Map<UUID, DBTableFactoryInterface> tableFactories)
    {
        this.tableFactories.putAll(tableFactories);
    }

    public DBTableInterface createTable(UUID serviceUuid)
    {
        DBTableFactoryInterface factory =  tableFactories.get(serviceUuid);
        if( factory == null ) return new DBTableNull();
        return factory.createTable();
    }
}