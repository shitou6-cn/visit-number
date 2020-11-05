//package com.baimiaojunye.work.factory.data.dataSource;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//    /**
//     * oracle 调用存储过程
//     *
//     * @author Mr.hu
//     * @date 2018/01/15 下午14:23:25
//     *
//     */
//    public class JdbcData {
//        public static void main(String[] args) throws Exception {
//
//            String driver = "oracle.jdbc.OracleDriver";
//            String strUrl = "jdbc:oracle:thin:@192.168.11.112:1521:oracle11";
//
//            String username = "test";
//            String password = "test";
//            Statement stmt = null;
//            ResultSet rs = null;
//            Connection conn = null;
//            CallableStatement cstmt = null;
//            CallableStatement proc = null; // 创建执行存储过程的对象
//            try {
//                // 加载驱动
//                Class.forName(driver);
//                // 获取连接
//                conn = DriverManager.getConnection(strUrl, username, password);
//                String xx="{ call "+username+".PROC5(?,?) }";
//                System.out.println(xx);
//                proc = conn.prepareCall(xx); // 设置存储过程// call为关键字.
//                // 设置输入参数
//                proc.setString(1, "91199"); // 设置第一个输入参数
//                proc.setString(2, "0");
//                proc.execute();// 执行
//                System.out.println("完成-----");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(stmt!=null){
//                    stmt.close();
//                }
//                if(conn!=null){
//                    conn.close();
//                }
//            }
//
//
//        }
//
//    }
