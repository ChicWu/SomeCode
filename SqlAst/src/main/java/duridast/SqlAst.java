package duridast;

public interface SqlAst {
    public String sqlExecute(String sql,String database,String ip);
    public String sqlExecute(String sql);
}
