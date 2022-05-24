//package Utils;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.logging.*;
//
//public class AstLog {
//    private static Logger logger = Logger.getLogger(AstLog.class.getName());
//    private static String FILE_PATH;
//
//    /**
//     * 创建日志
//     * @author chicwu
//     */
//    static {
//        try {
//                FILE_PATH = "/home/otl/log/outlog.log";
//            if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
//                File directory = new File("");// 参数为空
//                String path = directory.getCanonicalPath();
//                FILE_PATH = path + "\\logs\\outlog.log";
//            }
////            System.out.println(FILE_PATH);
//            if (!new File(FILE_PATH).getParentFile().exists()) {
//                new File(FILE_PATH).getParentFile().mkdirs();
//            }
//            FileHandler fileHandler = new FileHandler(FILE_PATH, true);
//            fileHandler.setEncoding("utf-8");
//            LogFormatter sf = new LogFormatter();
//            fileHandler.setFormatter(sf);
//            logger.addHandler(fileHandler);
//            try {
//                File sout = new File("/home/otl/log/system.log");
//                if (System.getProperty("os.name").toLowerCase().startsWith("win")){
//                    sout = new File("F:\\com.zhibei\\log\\system.log");
//                }
//                if(!sout.exists()){
//                    sout.createNewFile();
//                }
//                FileWriter fw = new FileWriter(sout.getAbsoluteFile(),true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write("日志文件创建成功FILE_PATH:" + FILE_PATH+"\n");
//                bw.close();
//            }catch (Exception e){
//
//            }
//        } catch (Exception e) {
//            System.out.println("日志文件创建失败FILE_PATH:" + FILE_PATH);
//            try {
//                File sout = new File("/home/otl/log/system.log");
//                if (System.getProperty("os.name").toLowerCase().startsWith("win")){
//                    sout = new File("F:\\com.zhibei\\log\\system.log");
//                }
//                if(!sout.exists()){
//                    sout.createNewFile();
//                }
//                FileWriter fw = new FileWriter(sout.getAbsoluteFile(),true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write("日志文件创建失败FILE_PATH:" + FILE_PATH+"\n");
//                bw.close();
//            }catch (IOException e1 ){
//
//            }
//        }
//    }
//
//    /**
//     * 编写写入日志的方法
//     * @param obj
//     */
//    public static void warn(Object obj) {
//        LogRecord lr = new LogRecord(Level.WARNING, obj.toString());
//        logger.log(lr);
//    }
//
//    public static void info(Object obj) {
//        LogRecord lr = new LogRecord(Level.INFO, obj.toString());
//        logger.log(lr);
//    }
//    public static void error(Object obj) {
//        LogRecord lr = new LogRecord(Level.SEVERE, obj.toString());
//        logger.log(lr);
//    }
//    public static void main(String[] args) {
//        AstLog.info("666666");
//    }
//
//
//}
//
//class LogFormatter extends Formatter {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//    @Override
//    public String format(LogRecord record) {
//        return "[" + sdf.format(new Date()) + "] [" + record.getLevel() + "] "
//                + record.getMessage().replace("\r", " ").replace("\n", " ") + "\r\n";
//    }
//}