package ruslan.kovshar.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class Mapper<T> {

    private Map<Long, T> cache = new HashMap<>();

    public abstract T extractFromResultSet(ResultSet rs) throws SQLException;

    T makeUnique(T obj, Long objId) {
        cache.putIfAbsent(objId, obj);
        return cache.get(objId);
    }
}
