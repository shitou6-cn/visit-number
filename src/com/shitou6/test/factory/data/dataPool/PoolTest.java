//package com.baimiaojunye.work.factory.data.dataPool;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//public class PoolTest
//{
//    public static void main( String[] args )
//    {
//
//        final DataSource source = new DataSourceImpl();
//        final CountDownLatch latch = new CountDownLatch(3);
//        int i = 0;
//
//                    PreparedStatement pre = null;
//                    ResultSet result = null;
//                    try {
//                        PoolConnection connect = source.getDataSource();
//                        System.out.println("connect = " + connect);
//                        String sql = "select * from T_PROVIDER_LACK where lack_status= 0 or lack_status =3";
//                        pre = connect.getConnect().prepareCall(sql);
////                        pre.setString(1, "%3AL34812ABAA%");
//                        // 执行查询，注意括号中不需要再加参数
//                        result = pre.executeQuery();
//                        while (result.next()) {
//                            // 当结果集不为空时
//                            System.out.println("LEVEL4_CODE: " + result.toString());
//                        }
//
//                        TimeUnit.SECONDS.sleep(1);
//                        connect.releaseConnect();
//                        latch.countDown();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//
//            System.out.println("-------结束-----------");
//
//
//    }
//}
