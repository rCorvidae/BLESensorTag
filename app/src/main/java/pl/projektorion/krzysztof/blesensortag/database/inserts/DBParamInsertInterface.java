package pl.projektorion.krzysztof.blesensortag.database.inserts;

/**
 * Created by krzysztof on 16.12.16.
 */

public interface DBParamInsertInterface extends DBInsertInterface {
    void insert(DBInsertParamDataInterface data);
}