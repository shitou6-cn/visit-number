//package com.baimiaojunye.work.service;
//
//import com.baimiaojunye.Utiles.JdbcUtiles;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * 用于首次建立爬虫 完善数据
// * 步骤：
// * 1.读取目前数据库已有号段表  3位或4位   number_status=0
// * 2.循环传入存储过程，循环10000次放入循环表  number_status=1
// */
//public class Init extends JdbcUtiles {
////    public static void init(String proc, Integer size) {
////        try {
////            //得到连接
////            Connection connection = null;
////            connection = newJdbc();
////            //sql语句 查询状态为0的 0为未初始化
////            PreparedStatement preState = connection.prepareStatement("select MOBILE_PREFIX FROM T_PROVIDER_MOBILE_PREFIX WHERE NUMBER_STATUS=0");
////            //执行查询语句
////            resultSet = preState.executeQuery();
////            //用来接查询的号段
////            List<Integer> list = new ArrayList<>();
////            //用来处理值
////            Integer prefix = null;
////            //循环 最后得到每个号段的0号开始
////            while (resultSet.next()) {
////                prefix = resultSet.getInt("MOBILE_PREFIX");
////                list.add(prefix);
////            }
////            //调用存储过程 传入   三个参数   调用存储过程名字   号段（1630000） 循环次数  1630000~1639999  传入10000
////            for (Integer integer : list) {
////                if (prefix.toString().length() == 3) {
////                    storage(proc, integer * 10000,integer,size);
////
////                } else if (prefix.toString().length() == 4) {
////                    storage(proc, integer * 1000,integer,size);
////                }
////                System.out.println("新增号段：" + integer);
////
////            }
////
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        } finally {
////
////            try {
////                if (statement != null) {
////                    try {
////                        resultSet.close();
////                    } catch (SQLException e) {
////                        e.printStackTrace();
////                    }
////                }
////                if (statement != null) {
////                    try {
////                        statement.close();
////                    } catch (SQLException e) {
////                        e.printStackTrace();
////                    }
////                }
////                if (connect != null) {
////                    try {
////                        connect.close();
////                    } catch (SQLException e) {
////                        e.printStackTrace();
////                    }
////                }
////
////
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////    }
//}
