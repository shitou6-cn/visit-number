package com.shitou6.service;

import com.shitou6.dao.JdbcUtiles;

import static com.shitou6.dao.Reptile.queryLock;
import static com.shitou6.dao.Reptile.queryLock1;

public class Start_now extends JdbcUtiles {

    //初始化时候用
    public static void newInit() {
        // * 1.读取目前数据库已有号段表  3位或4位   number_status=0
        // * 2.循环传入存储过程，循环10000次放入循环表  number_status=1
        init("ROTATE_INIT", 10000);
        //调用存储过程 循环号段表  T_PROVIDER_ROTATE表减去表T_PROVIDER_MOBILE 缺省值放入T_PROVIDER_MOBILE_LACK表
        //传入存储过程名字
        System.out.println("调用存储过程 循环号段表  T_PROVIDER_ROTATE表减去表T_PROVIDER_MOBILE 缺省值放入T_PROVIDER_MOBILE_LACK表");
        storage1("LACK_INIT");
        System.out.println("初始化操作结束");
    }

    //传入  运营商   3电信/2联通/1移动    传入 号段  163/1701  此方法用来新增号段
    public static void insertNumber(Integer operator, String number) {

        //传入运营商和新增的号段  3位或者4位
        insert(operator, number);
        // * 1.读取目前数据库已有号段表  3位或4位   number_status=0
        // * 2.循环传入存储过程，循环10000次放入循环表  number_status=1
        init("ROTATE_INIT", 10000);
        //循环表数据同步到缺失表
        transfer("LACK_ROTATE");
        //开始爬虫  爬虫lock的为0的
        queryLock();

    }

    public static void main(String[] args) {
        queryLock();
//        newInit();
    }


    public static synchronized void reptile0() {
        System.out.println("开始爬虫0");
        queryLock();
    }


    public static synchronized void reptile3() {
        //直接开始爬虫  爬虫lock为3的
        System.out.println("立即开始爬虫3");
        queryLock1();
    }


}
