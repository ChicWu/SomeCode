package SqlCache;

import Bean.CacheBean;

public class SqlCache {
    public static void cache(String absql,String abotlsql){
        CacheBean.setSqlCache(absql,abotlsql);
    }
}
