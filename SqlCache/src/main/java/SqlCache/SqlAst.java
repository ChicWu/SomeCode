package SqlCache;

import Bean.SqlBean;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SqlAst {
    private SqlBean sqlBean = new SqlBean();
    private String newsql = sqlBean.getSql();
    private int i = 0;
    private void setSql(String sql){
        if (i++ == 0 ){
            newsql = sql.replaceAll("\n"," ").replaceAll("\t","");
        }
    }

    public SqlBean selectStatement(String sql){
        selectStatementTest(sql);
        return sqlBean;
    }

    private void selectStatementTest(String sql){
        //解析select查询
        SQLStatement sqlStatement =  new MySqlStatementParser(sql).parseStatement();
        setSql(sqlStatement.toString());
        if (sqlStatement instanceof SQLSelectStatement){
            sqlStatement = (SQLSelectStatement)sqlStatement;
        }else {
            return;
        }
        SQLSelect sqlSelect = ((SQLSelectStatement) sqlStatement).getSelect();
        SQLSelectQueryBlock sqlSelectQuery = (SQLSelectQueryBlock)sqlSelect.getQuery() ;
        StringBuffer out = new StringBuffer() ;
        SQLASTOutputVisitor sqlastOutputVisitor = SQLUtils.createFormatOutputVisitor(out , null , JdbcUtils.MYSQL) ;

        //解析from
        try {
            out.delete(0, out.length()) ;
            sqlSelectQuery.getFrom().accept(sqlastOutputVisitor) ;
            String Recursive = new String(out.toString().replaceAll("\n",""));
            try {
                //进行递归
                selectStatementTest(Recursive);
            }catch (Exception e){}
        }catch (NullPointerException e){
        }

        //解析where
        try {
            out.delete(0, out.length()) ;
            sqlSelectQuery.getWhere().accept(sqlastOutputVisitor) ;
            String where = out.toString()+" ";
            if (!Objects.equals(where," ")){
                Pattern pattern = Pattern.compile("(=\\s.*?\n)|(=\\s.*?\\s)");
                Matcher matcher = pattern.matcher(where);
                while (matcher.find()){
                    String matcherWhere = matcher.group();
                    where = where.replaceAll(matcherWhere,"= ?OTL? ");
                    sqlBean.getParameter().add(matcherWhere.subSequence(2,matcherWhere.length()-1));
                }
                newsql = newsql.replaceAll(out.toString().replaceAll("\n","").replaceAll("\t",""),
                        where.replaceAll("\n","").replaceAll("\t",""));
                sqlBean.setSql(newsql);
            }
        }catch (NullPointerException e){
        }



    }
}
