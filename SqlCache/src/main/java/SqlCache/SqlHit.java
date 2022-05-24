package SqlCache;

import Bean.CacheBean;
import Bean.SqlBean;
import Utils.ColumnAttr;
import com.zhibei.otldb.api.Api;

import java.util.ArrayList;

public class SqlHit {
    public static  String hit(String sql){
        SqlBean sqlBean = new SqlAst().selectStatement(sql);
        SqlBean otlSqlBean = new SqlBean();
//        Api.convent("张三", 1 << ColumnAttr.ENCRYPT_BIT);
        otlSqlBean.setSql(CacheBean.getSqlCache(sqlBean.getSql()));
        ArrayList<Object> otlParameter = new ArrayList<Object>();
        for (Object object:sqlBean.getParameter()) {
            otlParameter.add(Api.convent(object.toString(),1<<ColumnAttr.ENCRYPT_BIT));
        }
        otlSqlBean.setParameter(otlParameter);
        return otlSqlBean.toString();
    }
}
