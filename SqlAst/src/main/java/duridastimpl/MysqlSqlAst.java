package duridastimpl;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.fastjson.JSON;
import duridast.SqlAst;

public class MysqlSqlAst implements SqlAst {
    private StringBuffer endBuffer;

    static {
        JSON.toJSONString(new SqlObj());
    }
    @Override
    public String sqlExecute(String sql, String database, String ip) {
        String sqlBuffer = "记录当前sql";
        String sqlJson = "";
        try {
            sqlBuffer = sql;
            sql = changeSql1(sql);
            SQLStatement sqlStatement = new  MySqlStatementParser(sql).parseStatement();
            SqlObj sqlObj = excuteStatement(sqlStatement,database,ip);
            sqlObj.setSql(changeSql2(sqlObj.getSql()));
            sqlJson = JSON.toJSONString(sqlObj);
        }catch (Exception e){
            sqlJson="{\"data\":{},\"flag\":0,\"sql\":\""+sqlBuffer+"\"}";
        }
        return sqlJson;
    }

    @Override
    public String sqlExecute(String sql) {
        return sqlExecute(sql,null,null);
    }

    private String changeSql1(String sql){
        int RETURNING = sql.indexOf("RETURNING")>0?sql.indexOf("RETURNING"):sql.indexOf("returning");
        if (RETURNING>0){
            endBuffer = new StringBuffer(sql.substring(RETURNING));
            sql = sql.substring(0,RETURNING);
        }
        return sql;
    }

    private String changeSql2(String sql ){
        if(endBuffer != null){
            sql = sql + endBuffer.toString();
        }
        return sql;
    }

    private SqlObj excuteStatement(SQLStatement sqlStatement, String database, String ip){
        SqlObj sqlObj;
        if (sqlStatement instanceof SQLInsertStatement){
            sqlObj = new MysqlInsert(sqlStatement).execute(database,ip);
        } else{
            sqlObj = new OtherSql(sqlStatement).execute(database,ip);
        }
        return sqlObj;
    }
}
