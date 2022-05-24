package SqlCache;


import Utils.Log;

public class SqlOtl {

//    private static String url = "http://127.0.0.1:8090/";
//    private static String databases = "XYAPP1";
//    private static String dbtype = "Oracle";
//    private static String open = "1";
//    private static int ThreadNum = 20;
//    private static ExecutorService pool;
//    static {
//        try {
//            Properties properties = new Properties();
//            InputStream in = SqlOtl.class.getClassLoader().getResourceAsStream("config.properties");
//            properties.load(in);
//            url = properties.getProperty("url");
//            databases = properties.getProperty("databases");
//            dbtype = properties.getProperty("dbtype");
//            open = properties.getProperty("open");
//            ThreadNum = new Integer(properties.getProperty("ThreadNum"));
//            AstLog.info("读取配置文件成功，使用配置文件配置");
//            AstLog.info("url:["+url+"],databases:["+databases+"],dbtype:["+dbtype+"],open:["+open+"],ThreadNum : ["+ThreadNum+"]");
//        }catch (Exception e){
//            AstLog.error("读取配置文件失败，使用默认配置"+e.getMessage());
//            AstLog.info("url:["+url+"],databases:["+databases+"],dbtype:["+dbtype+"],open:["+open+"],ThreadNum : ["+ThreadNum+"]");
//        }
//        try {
//            AstLog.info("Api.init start: url:["+url+"]");
//            long start = System.currentTimeMillis();
//            Api.init(url,false,true);
//            pool = Executors.newFixedThreadPool(ThreadNum);
//            long userTime = System.currentTimeMillis() - start;
//            AstLog.info("Api.init end: url:["+url+"] time:["+userTime+"]");
//        }catch (Exception e){
//            AstLog.warn("Api.init Exception:"+e.getMessage());
//        }
//    }

    public static String sqlotl(String sql){
//        String otlSql = null;
//        try {
//            AstLog.info("SqlOtlCall start sql: ["+sql+"]");
//            Future submit = pool.submit(new SqlOtlCall(sql, databases, dbtype, open));
//            otlSql = submit.get().toString();
//            AstLog.info("SqlOtlCall  end  sql: ["+otlSql+"]");
//        } catch (Exception e) {
//            AstLog.error("SqlOtlCall Exception + ["+e.getMessage()+"]");
//        }
//        return otlSql;
        Log.info("SqlCache return sql sql: ["+sql+"]");
        return sql;
    }




//    public static String sqlotl(String sql){
//        String otlsql = new String();
//        Thread currentThread = Thread.currentThread();
//        AstLog.info("sql getCache currentThread id: ["+currentThread.getId()+"]");
//        if (!open.equals("1")){
//            otlsql =  sqlotl2(sql);
//        }else {
//            try {
//                String absql = new SqlAst().selectStatement(sql).getSql();
//                if(!Objects.equals(null, CacheBean.getSqlCache(absql))){
//                    AstLog.info("sql getCache start : sql["+sql+"]");
//                    long start = System.currentTimeMillis();
//                    otlsql = SqlHit.hit(sql);
//                    long userTime = System.currentTimeMillis() - start;
//                    AstLog.info("sql getCache end : sql["+otlsql+"] time:["+userTime+"]");
//                }else{
//                    AstLog.info("sql setCache start : sql["+sql+"]");
//                    long start = System.currentTimeMillis();
//                    otlsql = sqlotl2(sql);
//                    if (!Objects.equals(otlsql,null)){
//                        String abOtlsql = new SqlAst().selectStatement(otlsql).getSql();
//                        SqlCache.cache(absql,abOtlsql);
//                        long userTime = System.currentTimeMillis() - start;
//                        AstLog.info("sql setCache end : sql["+otlsql+"] time:["+userTime+"]");
//                    }else {
//                        AstLog.error("Get OtlSql Exception , sql:["+sql+"]");
////                        throw new RuntimeException("Get OtlSql Exception");
//                    }
//                }
//
//            }catch (Exception e){
//                otlsql =  sqlotl2(sql);
//            }
//        }
//        return otlsql;
//    }
//
//    private static String sqlotl2(String sql){
//        String otlsql = new String();
//        try {
//            AstLog.info("getOtlSql start :sql:[" +sql+"],databases:["+databases+"]");
//            long start = System.currentTimeMillis();
//            if (dbtype.equals("Oracle")){
//                otlsql = SqlApi.getOtlSql(sql, databases, OtlEnum.SERVER_TYPE.ORACLE);
//            }else if(dbtype.equals("Mysql")){
//                otlsql = SqlApi.getOtlSql(sql, databases, OtlEnum.SERVER_TYPE.MYSQL);
//            }else {
//                AstLog.error("dbtype error 使用oracle");
//                otlsql = SqlApi.getOtlSql(sql, databases, OtlEnum.SERVER_TYPE.ORACLE);
//            }
//            long userTime = System.currentTimeMillis() - start;
//            AstLog.info("getOtlSql end: otlsql["+otlsql+"] time:["+userTime+"]");
//        }catch (Exception e){
//            AstLog.error("SqlApi.getOtlSql Exception:"+e.getMessage());
//            AstLog.error("error sql ["+sql+"]");
//            return null;
//        }
//        return otlsql;
//    }


}