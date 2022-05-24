//package Utils;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class FileRead {
//    public static ArrayList getStringist(String fileName){
//        // 使用ArrayList来存储每行读取到的字符串
//        ArrayList<String> arrayList = new ArrayList<String>();
//        try {
//            FileReader fr = new FileReader(fileName);
//            BufferedReader bf = new BufferedReader(fr);
//            String str;
//            // 按行读取字符串
//            while ((str = bf.readLine()) != null) {
//                arrayList.add(str);
//            }
//            bf.close();
//            fr.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return arrayList;
//    }
//
//    public static void main(String[] args) {
//        ArrayList<String> arrayList = getStringist("F:\\com.zhibei\\ReadFile\\tmp(1).log");
//        for (String str:arrayList) {
//            System.out.println(str);
//        }
//    }
//}
//
