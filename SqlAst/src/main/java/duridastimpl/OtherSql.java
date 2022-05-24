package duridastimpl;

import com.alibaba.druid.sql.ast.SQLStatement;
import duridast.SqlAstStatement;

public class OtherSql implements SqlAstStatement {
    private SQLStatement sqlStatement;
    private SqlObj sqlObj;
    public OtherSql(SQLStatement sqlStatement) {
        this.sqlStatement = sqlStatement;
        sqlObj = new SqlObj();
    }

    @Override
    public SqlObj execute(String databases, String url) {
        sqlObj.setSql(sqlStatement.toString());
        return sqlObj;
    }
}
