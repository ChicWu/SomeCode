package SqlCache;

import Bean.CacheBean;
import Utils.Log;

import java.util.Objects;
import java.util.concurrent.Callable;

public class SqlOtlCall implements Callable {
    private String sql;
    private String databases;
    private String dbtype;
    private String open;

    public SqlOtlCall(String sql, String databases, String dbtype, String open) {
        this.sql = sql;
        this.databases = databases;
        this.dbtype = dbtype;
        this.open = open;
    }

    @Override
    public Object call() throws Exception {
        return sqlotl();
    }

    public String sqlotl(){
        String otlsql = new String();
        Thread currentThread = Thread.currentThread();
        Log.info("sql getCache currentThread id: ["+currentThread.getId()+"]");
        if (!open.equals("1")){
            otlsql =  sqlotl2();
        }else {
            try {
                String absql = new SqlAst().selectStatement(sql).getSql();
                if(!Objects.equals(null, CacheBean.getSqlCache(absql))){
                    Log.info("sql getCache start : sql["+sql+"]");
                    long start = System.currentTimeMillis();
                    otlsql = SqlHit.hit(sql);
                    long userTime = System.currentTimeMillis() - start;
                    Log.info("sql getCache end : sql["+otlsql+"] time:["+userTime+"]");
                }else{
                    Log.info("sql setCache start : sql["+sql+"]");
                    long start = System.currentTimeMillis();
                    otlsql = sqlotl2();
                    if (!Objects.equals(otlsql,null)){
                        String abOtlsql = new SqlAst().selectStatement(otlsql).getSql();
                        SqlCache.cache(absql,abOtlsql);
                        long userTime = System.currentTimeMillis() - start;
                        Log.info("sql setCache end : sql["+otlsql+"] time:["+userTime+"]");
                    }else {
                        Log.error("Get OtlSql Exception , sql:["+sql+"]");
//                        throw new RuntimeException("Get OtlSql Exception");
                    }
                }

            }catch (Exception e){
                otlsql =  sqlotl2();
            }
        }
        return otlsql;
    }

    private String sqlotl2(){
        String otlsql = new String();
        try {
            Log.info("getOtlSql start :sql:[" +sql+"],databases:["+databases+"]");
            long start = System.currentTimeMillis();
//            if (dbtype.equals("Oracle")){
//                otlsql = SqlApi.getOtlSql(sql, databases, OtlEnum.SERVER_TYPE.ORACLE);
//            }else if(dbtype.equals("Mysql")){
//                otlsql = SqlApi.getOtlSql(sql, databases, OtlEnum.SERVER_TYPE.MYSQL);
//            }else {
//                Log.error("dbtype error 使用oracle");
//                otlsql = SqlApi.getOtlSql(sql, databases, OtlEnum.SERVER_TYPE.ORACLE);
//            }
            long userTime = System.currentTimeMillis() - start;
            Log.info("getOtlSql end: otlsql["+otlsql+"] time:["+userTime+"]");
        }catch (Exception e){
            Log.error("SqlApi.getOtlSql Exception:"+e.getMessage());
            Log.error("error sql ["+sql+"]");
            return null;
        }
        return otlsql;
    }
}
