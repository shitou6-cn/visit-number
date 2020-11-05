//package com.baimiaojunye.work.factory.data.dataSource;
//
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Jdbc {
//    static Connection connect = null;
//    static Statement statement = null;
//    static ResultSet resultSet = null;
//
////    public Mobile query() {
////        Mobile mobile = new Mobile();
////        try {
////            //类加载
////            Class.forName("oracle.jdbc.OracleDriver");
////            //利用DriverManager
////            try {
////                connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.11.112:1521:oracle11", "com/baimiaojunye/test", "com/baimiaojunye/test");
////                System.out.println(connect);
////                // 占位符
////                PreparedStatement preState = connect.prepareStatement("select  * from T_PROVIDER_MOBILE where CITY = ?");
////                //1是指sql语句中第一个？,  2是指第一个？的values值
////                preState.setString(1, "广东湛江");
////                //执行查询语句
////                resultSet = preState.executeQuery();
////                //查询任何语句，如果有结果集，返回true，没有的话返回false,注意如果是插入一条数据的话，
////                // 虽然是没有结果集，返回false，但是却能成功的插入一条数据
//////                boolean execute = preState.execute();
//////                System.out.println(resultSet.toString());
////                while (resultSet.next()) {
//////                    CODEFIELD	NUMBER
//////                    CITY	VARCHAR2
//////                    AREA	VARCHAR2
//////                    MOBILECARD	VARCHAR2
//////                    PROVINCE	VARCHAR2
//////                    PROV_ID	NUMBER
//////                    CREATE_TIME	DATE
//////                    ID	NUMBER
//////                    PROVIDER_ID	NUMBER
////
//////                    mobile.setCODEFIELD(CODEFIELD);
//////                    String name = resultSet.getString("CODEFIELD");
////                    String city = resultSet.getString("CITY");
////                    System.out.println(city);  //打印输出结果集
////                }
////
////
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////
////
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        } finally {
////
////            if (resultSet != null) {
////                try {
////                    resultSet.close();
////                    if (statement != null) statement.close();
////                    if (connect != null) connect.close();
////                    connect = null;
////                    statement = null;
////                    resultSet = null;
////
////                } catch (SQLException e) {
////                    e.printStackTrace();
////                }
////            }
////
////        }
////        return null;
////    }
//
//
//    //查询缺失表  返回需要查询的号段list   定期任务
//    public List<Integer> queryLack() {
//        List<Integer> list = new ArrayList<>();
//        //号码状态只存1个月0未插入1已插入3插入异常
//        try {
//            //类加载
//            Class.forName("oracle.jdbc.OracleDriver");
//            //利用DriverManager
//            try {
//                connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.11.112:1521:oracle11", "test", "test");
//                // 占位符
//                PreparedStatement preState = connect.prepareStatement("select  lack_iphone from T_PROVIDER_LACK where lack_status =0 or lack_status =3");
//                //执行查询语句
//                resultSet = preState.executeQuery();
//
//                //查询任何语句，如果有结果集，返回true，没有的话返回false,注意如果是插入一条数据的话，
//                // 虽然是没有结果集，返回false，但是却能成功的插入一条数据
////                boolean execute = preState.execute();
////                System.out.println(resultSet.toString());
//                while (resultSet.next()) {
////                    CODEFIELD	NUMBER
////                    CITY	VARCHAR2
////                    AREA	VARCHAR2
////                    MOBILECARD	VARCHAR2
////                    PROVINCE	VARCHAR2
////                    PROV_ID	NUMBER
////                    CREATE_TIME	DATE
////                    ID	NUMBER
////                    PROVIDER_ID	NUMBER
//
////                    mobile.setCODEFIELD(CODEFIELD);
////                    String name = resultSet.getString("CODEFIELD");
//                    list.add(Integer.valueOf(resultSet.getString("lack_iphone")));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
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
//            return list;
//        }
//    }
//
//
//    public static void main(String[] args) {
//        try {
//            //类加载
//            Class.forName("oracle.jdbc.OracleDriver");
//            //利用DriverManager
//            try {
//                connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.11.112:1521:oracle11", "com/baimiaojunye/test", "com/baimiaojunye/test");
//                System.out.println(connect);
//                // 占位符
//                PreparedStatement preState = connect.prepareStatement("select  * from T_PROVIDER_MOBILE where CITY = ?");
//                //1是指sql语句中第一个？,  2是指第一个？的values值
//                preState.setString(1, "广东湛江");
//                //执行查询语句
//                resultSet = preState.executeQuery();
//
//
//
//                //查询任何语句，如果有结果集，返回true，没有的话返回false,注意如果是插入一条数据的话，
//                // 虽然是没有结果集，返回false，但是却能成功的插入一条数据
////                boolean execute = preState.execute();
////                System.out.println(resultSet.toString());
//
//                while (resultSet.next()) {
//                    String name = resultSet.getString("CODEFIELD");
//                    String city = resultSet.getString("CITY");
//                    System.out.println(name + "   " + city);  //打印输出结果集
//                }
//
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//        } catch (ClassNotFoundException e) {
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
