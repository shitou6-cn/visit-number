//package com.baimiaojunye;
//
//import com.baimiaojunye.dao.JdbcUtiles;
//import com.baimiaojunye.dao.tools.Input;
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.baimiaojunye.dao.JdbcUtiles.newJdbc;
//
//public class test1 extends JdbcUtiles {
//    static Input a=new Input();
//    public static void main(String[] args) {
//        Connection connection = null;
//        List<Integer> list = new ArrayList<>();
//
//        //读文件路径
//        String xx1="C:\\Users\\admin\\Desktop\\1\\167.sql";
//        try {
//            connection = newJdbc();
//
//            //sql语句 查询状态为0的 0为未初始化
//            PreparedStatement preState = connection.prepareStatement("select CODEFIELD from T_PROVIDER_MOBILE WHERE  CODEFIELD like  '167%'");
//            //执行查询语句
//            resultSet = preState.executeQuery();
//            //用来处理值
//            Integer prefix = null;
//            //循环 最后得到所有162的集合
//            while (resultSet.next()) {
//                prefix = resultSet.getInt("CODEFIELD");
//                list.add(prefix);
//            }
//
////            System.out.println("list = " + list);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("数据库取完值了");
//
//        String cityPath = xx1;
//        try {
//            //设置编码格式
//            String encoding = "UTF-8";
//            File cityFile = new File(cityPath);
//            //判断两个文件是否都在
//            if (cityFile.isFile() &&
//                    cityFile.exists()) {
//                //考虑到编码格式转码
//                InputStreamReader read1 = null;
//                try {
//                    try {
//                        read1 = new InputStreamReader(
//                                new FileInputStream(cityPath), encoding);
//
//
//                        BufferedReader bufferedReader1 = new BufferedReader(read1);
//                        //两个String用来取值
//                        String cityTxt = null;
//                        //对 城市和区号映射文件  放到map里面
//                        List<String> list1=new ArrayList<>();
//                        List<Integer> list2=new ArrayList<>();
//
//                            while ((cityTxt = bufferedReader1.readLine()) != null) {
//                                for (Integer integer : list) {
//                                // 存在
//                                if ((cityTxt.indexOf(integer + "") != -1)) {
//
//
//                                }else {
//                                    list1.add(cityTxt);
//                                    list2.add(integer);
//                                }
//                            }
//                            // 获取区号
////                                cityMap.put(b, a);
//
//                        }
//                        for (String s : list1) {
//                          a.Input(s,"C:\\Users\\admin\\Desktop\\167.sql");
//                        }
//                        for (Integer integer : list2) {
//                            a.Input(integer+"","C:\\Users\\admin\\Desktop\\167.txt");
//                        }
//
//
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } finally {
//
//        }
//    }
//}
