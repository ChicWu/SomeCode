package duridastimpl;

import Utils.ColumnAttr;
import Utils.HttpUtil;
import Utils.Log;
import Utils.OtlEnum;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import duridast.MysqlStatement;

import java.util.*;

public class MysqlInsert extends SqlObj implements MysqlStatement {
    private SQLStatement sqlStatement;
    private List<SQLInsertStatement.ValuesClause> valueList ;
    private List<SQLExpr> Columns;
    private SQLInsertStatement.ValuesClause value;
    private SqlObj sqlObj;
    private String statementSql;
    private String source;

    public MysqlInsert(SQLStatement sqlStatement) {
        valueList = ((MySqlInsertStatement) sqlStatement).getValuesList();
        value = ((MySqlInsertStatement) sqlStatement).getValues();
        ((MySqlInsertStatement) sqlStatement).getTableSource();
        statementSql = sqlStatement.toString();
        Columns = ((MySqlInsertStatement) sqlStatement).getColumns();
        this.source = ((MySqlInsertStatement) sqlStatement).getTableSource().toString();
        sqlObj = new SqlObj();
        this.sqlStatement = sqlStatement;
    }

    public SqlObj execute(String database, String ip){
        this.addValues();
        this.setFlag();
        try{
            if (!Objects.equals(ip,null)){ settype(database,ip);}
        }catch (Exception e){ Log.warn("SqlAst getDataType Exception"); }
        this.setSql();
        return sqlObj;
    }



    private void addValues(){
        if (valueList.size()>1)
            for(SQLInsertStatement.ValuesClause value:valueList){
                addvaule(value);
            }else if(valueList.size()==0){
            return;
        }else {
            addvaule(value);
        }
        if (sqlObj.getData().size()>0){
            changevalue();
        }
    }

    private void addvaule(SQLInsertStatement.ValuesClause value) {
        if(sqlObj.getData().size()<=0){
            for (int i = 0;i<value.getValues().size();i++) {
                SQLExpr sqlExpr = value.getValues().get(i);
                DataObj dataObj = new DataObj();
                List<Object> valueLists = new ArrayList<Object>();
                valueLists.add(sqlExpr.toString().replaceAll("'","").replaceAll("\"",""));
                dataObj.setCol(valueLists);
                sqlObj.getData().put("col"+i,dataObj);
            }
        }else{
            for (int i = 0;i<value.getValues().size();i++) {
                SQLExpr sqlExpr = value.getValues().get(i);
                DataObj dataObj = sqlObj.getData().get("col"+i);
                List<Object> valueLists = dataObj.getCol();
                valueLists.add(sqlExpr.toString().replaceAll("'","").replaceAll("\"",""));
            }
        }
    }

    private void changevalue() {
        HashMap<String, DataObj> data = sqlObj.getData();
        for (DataObj dataObj:data.values()) {
            List<Object> valueList = dataObj.getCol();
            if (valueList.size()>0&&valueList.get(0).toString().startsWith("to_date(")){
                for(int i = 0;i < valueList.size(); i++){
                    int start = valueList.get(i).toString().indexOf("(");
                    int end = valueList.get(i).toString().indexOf(",");
                    valueList.set(i,valueList.get(i).toString().substring(start+1,end));
                }
                dataObj.setType(1);
            }
        }
    }

    private void settype(String database, String ip) {
        String Database = "";
        String Table = "";
        int index = source.indexOf(".");
        if (database!=null){
            Database = database.toUpperCase().replaceAll("`","");
        }
        if (index >0){
            Database = source.substring(0,index).toUpperCase().replaceAll("`","");
            Table = source.substring(index+1).toUpperCase().replaceAll("`","");
        }else{
            Table = source.toUpperCase().replaceAll("`","");
        }
        String url = "http://"+ip+"/db/metadata/show?database="+Database;
        String str = HttpUtil.get(url);
        JSONObject jsonObject = (JSONObject) JSONObject.parseObject(str, Feature.OrderedField)
                .getJSONObject(Database)
                .getJSONObject(Table);
//        System.out.println(jsonObject.getJSONObject("EMPID").get("status"));
        if (Columns.size()>0){
            for(int i = 0;i < Columns.size();i++){
                Object status = jsonObject.getJSONObject(Columns.get(i).toString().toUpperCase()).get("status");
                if(Objects.equals(ColumnAttr.getDataType((int)status), OtlEnum.DATA_TYPE.DATE)){
                    sqlObj.getData().get("col"+i).setType(1);
                }
            }
        }else {
            int i = -1;
            for (Object map : jsonObject.entrySet()) {
                try {
                    String str1 = ((Map.Entry) map).getValue().toString();
                    Object status =JSONObject.parseObject(str1).get("status");
                    i++;
                    if(Objects.equals(ColumnAttr.getDataType((int)status),OtlEnum.DATA_TYPE.DATE)){
                        sqlObj.getData().get("col"+i).setType(1);
                    }
                }catch(Exception e){
                }
            }
        }

    }

    private void setFlag() {
        if(sqlObj.getData().size()>0&&sqlObj.getData().get("col0").getCol().size()>1){
            sqlObj.setFlag(1);
        }
    }
    private void setSql(){
        StringBuffer sql = new StringBuffer();
        //记录Value后面的字符
        int n = value.getValues().size();
        int valueIndes = statementSql.indexOf("VALUES")>0?statementSql.indexOf("VALUES"):statementSql.indexOf("VALUE");
        if (n>=1){
            //根据占位符的个数来拼接sql语句
            sql.append(statementSql.substring(0,valueIndes) + "VALUES (");
            for (int i = 1 ;i<= n ; i++){
                if (i == n){
                    sql.append("? ) ");
                }else {
                    sql.append("? ,");
                }
            }
        }else {
            sql.append(statementSql);
        }
        sqlObj.setSql(sql.toString().replaceAll("\n"," ").replaceAll("\t"," "));
    }

}
