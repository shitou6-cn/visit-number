//package com.baimiaojunye.Utiles;
//
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//
///**
// * 读文件 生成insert 语句
// */
//public class NewInsert extends JdbcUtiles {
//
//    public static void main(String[] args) {
//        String1 xx = new String1();
//        String cityPath = "C:\\Users\\admin\\Desktop\\test1\\chengshi.txt";
//        try {
//            //设置编码格式
//            String encoding = "UTF-8";
//            //读取两个文件 城市  省份
//            File cityFile = new File(cityPath);
//            //考虑到编码格式转码
//            InputStreamReader read1 = null;
//            try {
//                Connection connection = null;
//                connection = newJdbc();
//
//                read1 = new InputStreamReader(
//                        new FileInputStream(cityPath), encoding);
//
//                BufferedReader bufferedReader1 = new BufferedReader(read1);
//                String cityTxt = null;
//                //对 城市和区号映射文件  放到map里面
//
//                int a = 0;
//                while (true) {
//                    ++a;
//                    try {
//                        if (!((cityTxt = bufferedReader1.readLine()) != null)) break;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    // 之前截取  得到城市
//                    String code = cityTxt.substring(0, cityTxt.indexOf(":"));
//                    // 之后截取   得到区号
//                    String city = cityTxt.substring(cityTxt.lastIndexOf(":") + 1);
//                    String sql = "insert into T_PROVINCE_CITY (id,code,city) VALUES (" + a + ",'" + code + "','" + city + "'); ";
//                    System.out.println(sql);
////                    PreparedStatement preState = connection.prepareStatement("insert into T_PROVINCE_CITY (id,code,city) VALUES (" + a + "," + code + "," + city + ") ");
////                    preState.execute();
//
//
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//            try {
//                if (statement != null) {
//                    try {
//                        resultSet.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (connect != null) {
//                    try {
//                        connect.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}