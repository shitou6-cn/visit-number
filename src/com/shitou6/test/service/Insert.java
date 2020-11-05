//package com.baimiaojunye.work.service;
//
//
//import com.baimiaojunye.Utiles.JdbcUtiles;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
////新增号段
//public class Insert extends JdbcUtiles {
//    //传入三位或四位号段
//    public static void  Insert(Integer number,String call){
//        try {
//            Connection connection =  newJdbc();
//            String sql="insert into T_PROVIDER_MOBILE_PREFIX (MOBILE_PREFIX，PROVIDER_ID,NUMBER_STATUS,REVERSION) VALUES ("+call+","+number+",0,sysdate) ";
//            //sql语句
////
////            insert into T_PROVIDER_MOBILE_PREFIX (MOBILE_PREFIX，PROVIDER_ID,NUMBER_STATUS) VALUES ("123",1,0)
//
//            System.out.println("sql = " + sql);
//            PreparedStatement preState = connection.prepareStatement(sql);
//            //执行查询语句
//            preState.execute();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        Insert a=new Insert();
//        //传入运营商和新增的号段  3位或者4位
//        a.Insert(1,"1199");
//    }
//}
