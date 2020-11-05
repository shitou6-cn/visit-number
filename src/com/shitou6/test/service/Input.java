//package com.baimiaojunye.work.service;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//public class Input {
//    //将文件写入本地用的     写入内容   写入地址
//    public static synchronized void Input(String work, String url) {
//        {
//
//
//            FileWriter fw = null;
//            try {
//        //如果文件存在，则追加内容；如果文件不存在，则创建文件
//                File f = new File(url);
//                fw = new FileWriter(f, true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            PrintWriter pw = new PrintWriter(fw);
//            //得到所有sql语句 将其建成sql
////            for (String s : work) {
//                pw.println(work);
////            }
//            pw.flush();
//            try {
//                fw.flush();
//                pw.close();
//                fw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//}
