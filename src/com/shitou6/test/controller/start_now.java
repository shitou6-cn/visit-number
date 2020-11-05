//package com.baimiaojunye.work.controller;
//import com.baimiaojunye.Utiles.JdbcUtiles;
//import com.baimiaojunye.Utiles.Reptile;
//
//public class start_now extends JdbcUtiles   {
//
//    //初始化时候用
//    public static void newInit() {
//        // * 1.读取目前数据库已有号段表  3位或4位   number_status=0
//        // * 2.循环传入存储过程，循环10000次放入循环表  number_status=1
//        init("ROTATE_INIT", 10000);
//        //调用存储过程 循环号段表  T_PROVIDER_ROTATE表减去表T_PROVIDER_MOBILE 缺省值放入T_PROVIDER_MOBILE_LACK表
//        //传入存储过程名字
//        System.out.println("调用存储过程 循环号段表  T_PROVIDER_ROTATE表减去表T_PROVIDER_MOBILE 缺省值放入T_PROVIDER_MOBILE_LACK表");
//        storage1("LACK_INIT");
//    }
//
//    //传入  运营商   电信/联通/移动    传入 号段  163/1701  此方法用来新增号段
//    public static void insertNumber(String operator, String number) {
//        int operator1 = 0;
//        if (operator.equals("电信")) {
//            operator1 = 3;
//        } else if (operator.equals("联通")) {
//            operator1 = 2;
//        } else if (operator.equals("移动")) {
//            operator1 = 1;
//        }
//        //传入运营商和新增的号段  3位或者4位
//        insert(operator1, number);
//        // * 1.读取目前数据库已有号段表  3位或4位   number_status=0
//        // * 2.循环传入存储过程，循环10000次放入循环表  number_status=1
//        init("ROTATE_INIT", 10000);
//        //循环表数据同步到缺失表
//        transfer("LACK_ROTATE");
//    }
//
//
//    public static void main(String[] args) {
//        newInit();
//    }
//
//
//
//}
