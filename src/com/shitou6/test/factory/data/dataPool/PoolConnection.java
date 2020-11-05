//package com.baimiaojunye.work.factory.data.dataPool;
//
//import java.sql.Connection;
//
///**
// *
// */
//public class PoolConnection {
//
//    private Connection connect;
//    //false 繁忙，true 空闲
//    private boolean status;
//
//    public PoolConnection() {
//    }
//
//    public PoolConnection(Connection connect, boolean status) {
//        this.connect = connect;
//        this.status = status;
//    }
//
//    public Connection getConnect() {
//        return connect;
//    }
//
//    public void setConnect(Connection connect) {
//        this.connect = connect;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    //释放连接池中的连接对象
//    public void releaseConnect(){
//        this.status = true;
//    }
//}
