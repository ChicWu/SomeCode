package Ast4Druid2;


public class AnalyzeSQL {

    public static String mysqlExecute(String sql,String database,String ip) {
        System.out.println("sqlAst start"+System.currentTimeMillis());
        String astSql = "{\"data\":{},\"flag\":0,\"sql\":\""+sql+"\"}";
        System.out.println("sqlAst end"+System.currentTimeMillis());
        return astSql;
    }
    public static String mysqlExecute(String sql){
        System.out.println("sqlAst start"+System.currentTimeMillis());
        String astSql = "{\"data\":{},\"flag\":0,\"sql\":\""+sql+"\"}";
        System.out.println("sqlAst end"+System.currentTimeMillis());
        return astSql;
    }


}


