package Bean;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlBean {
    private String sql;
    private List<Object> parameter = new ArrayList<Object>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParameter() {
        return parameter;
    }

    public void setParameter(List<Object> parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        String otlsql = sql;
        int i = 0;
        Pattern pattern = Pattern.compile("\\?OTL\\?");
        Matcher matcher = pattern.matcher(otlsql);
        while (matcher.find()){
            otlsql = otlsql.replace("?OTL?",parameter.get(i++).toString());
        }
        return otlsql;

//        return "SqlBean{" +
//                "sql='" + sql + '\'' +
//                ", parameter=" + parameter +
//                '}';
    }
}
