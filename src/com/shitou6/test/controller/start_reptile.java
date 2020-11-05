//package com.baimiaojunye.work.controller;
//
//
//import com.baimiaojunye.Utiles.Reptile;
//import com.baimiaojunye.Utiles.SqlManager;
//
//
//
////启动爬虫
//public class start_reptile  extends Reptile {
//
//    //引入格式化sql的类
//    static SqlManager a=new SqlManager();
//
//    public static void main(String[] args) {
//
//        //查询缺失表 得到状态为0  也就是需要查询的号段  得到list
//        //启动爬虫  传入要查询的号段  得到待格式化的list
//        //格式化 变成insert语句
//        try {
//            //格式化sql  传入需要格式化的String,城市对照表，省会对照表  返回sql语句集合
////           List<String> sql= a.sqlManager(Post(queryLock()),queryCity(),queryProvince());
////            List<String> sql= Post(queryLock());
////            System.out.println("sql = " + sql);
//            queryLock();
////           insertLack(a.sqlManager(queryLock(),queryCity(),queryProvince()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
