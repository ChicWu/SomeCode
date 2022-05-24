package Test;

import SqlCache.SqlOtl;

import java.util.concurrent.*;


public class SqlTest1 implements Callable {
    final static  ExecutorService pool = Executors.newFixedThreadPool(20);
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 1 ;i<= 20;i++) {
            Future submit = pool.submit(new SqlTest1());
            try {
                System.out.println(submit.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }
    @Override
    public Object call() throws Exception {
        return SqlOtl.sqlotl("select NAME,TELEPHONE,TAXCODE from COMPANY where NAME = '张三'");
    }
}
