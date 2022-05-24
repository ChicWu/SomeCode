package Ast4Druid;


import Utils.Log;
import duridast.SqlAst;
import duridastimpl.MysqlSqlAst;

public class Test {

    public static void main(String[] args) {
        SqlAst sqlAst = new MysqlSqlAst();
        Log.start = System.currentTimeMillis();
        String string = sqlAst.sqlExecute("insert into emp values ('2'),('2'),('2'),('2'),('2'),('2'),('2');");
        Log.debug(string);
        Log.debug(System.currentTimeMillis()-Log.start);
    }
}
