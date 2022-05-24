package Bean;

import java.util.concurrent.ConcurrentHashMap;

public class CacheBean {
    private CacheBean() {
    }

    private static ConcurrentHashMap<String,String> sqlCache = new ConcurrentHashMap<String,String>();

    public static String getSqlCache(String sql) {
        return sqlCache.get(sql);
    }

    public static void setSqlCache(String sql ,String otlSql) {
        sqlCache.put(sql,otlSql);
    }

}
