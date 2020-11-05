//package com.baimiaojunye.work.factory.data.dataPool;
//
//import java.io.InputStream;
//import java.util.Properties;
//
//public class PropertiesUtils extends Properties {
//
//    private static final long serialVersionUID = 1L;
//
//    //定义属性文件名称
//    private final static String PROPERTY_FILE = "datasource.properties";
//
//    private static PropertiesUtils propertiesHolder = null;
//
//    private PropertiesUtils() {
//        if (propertiesHolder != null) {
//            throw new RuntimeException("此类是单例，已经存在实例化对象了。");
//        }
//    }
//
//    public static PropertiesUtils getInstance() {
//        if (propertiesHolder == null) {
//            synchronized (PropertiesUtils.class) {
//                if (propertiesHolder == null) {
//                    propertiesHolder = new PropertiesUtils();
//                    try (InputStream input = propertiesHolder.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
//                        propertiesHolder.load(input);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return propertiesHolder;
//    }
//}