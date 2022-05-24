package Test;

import SqlCache.SqlOtl;


public class SqlTest2 {
    public static void main(String[] args) {
        for (int i = 1 ;i<= 20;i++) {
            System.out.println(SqlOtl.sqlotl("select NAME,TELEPHONE,TAXCODE from COMPANY where NAME = '张三'"));
        }
    }
}
