package duridast;

import duridastimpl.SqlObj;

public interface SqlAstStatement {
    public SqlObj execute(String databases, String url);
}
