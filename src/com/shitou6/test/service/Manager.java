//package com.baimiaojunye.work.service;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Manager {
//
//    /**
//     * 作者：石宗昊 2020-9-8  http://www.1234i.com/p.php
//     * 功能：Java读取txt文件的内容
//     * 步骤：
//     * 1：先获得文件句柄
//     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
//     * 3：读取到输入流后，需要读取生成字节流
//     * 4：一行一行的输出。readline()。
//     */
//    /**
//     * @param number      号段
//     * @param cityPath    城市  用来得到区号
//     * @param capitalPath 省会  得到id号
//     */
//    public static List<String> sqlManager(List<String> number, String cityPath, String capitalPath) {
//        //引入  截取的方法
//        String1 xx = new String1();
//        try {
//            //设置编码格式
//            String encoding = "UTF-8";
//            //读取两个文件 城市  省份
//            File cityFile = new File(cityPath);
//            File capitalFile = new File(capitalPath);
//            //判断两个文件是否都在
//            if (cityFile.isFile() &&
//                    cityFile.exists() && capitalFile.isFile() && capitalFile.exists()) {
//                //考虑到编码格式转码
//                InputStreamReader read1 = new InputStreamReader(
//                        new FileInputStream(cityPath), encoding);
//                BufferedReader bufferedReader1 = new BufferedReader(read1);
//                InputStreamReader read2 = new InputStreamReader(
//                        new FileInputStream(capitalPath), encoding);
//                BufferedReader bufferedReader2 = new BufferedReader(read2);
//                //两个String用来取值
//                String cityTxt = null;
//                String capitalTxt = null;
//                //对 城市和区号映射文件  放到map里面
//                Map<String, String> cityMap = new HashMap<String, String>();
//                while ((cityTxt = bufferedReader1.readLine()) != null) {
//                    // 之前截取  得到城市
//                    String a = cityTxt.substring(0, cityTxt.indexOf(":"));
//                    // 之后截取   得到区号
//                    String b = cityTxt.substring(cityTxt.lastIndexOf(":") + 1);
//                    // 获取区号
//                    cityMap.put(b, a);
//
//                }
//                //  读取文件  截取  得到一个id和省对应的map
//                Map<String, String> capitalMap = new HashMap<String, String>();
//                while ((capitalTxt = bufferedReader2.readLine()) != null) {
//                    //之前截取  得到id
//                    String a = capitalTxt.substring(0, capitalTxt.indexOf(":"));
//                    //之后截取   得到省会
//                    String b = capitalTxt.substring(capitalTxt.lastIndexOf(":") + 1);
//                    //获取区号
//                    capitalMap.put(b, a);
//                }
//                //用来接  最后提交的sql
//                List<String> sqlList = new ArrayList<String>();
//                for (String numberTxt : number) {
//                    String iphone = xx.subString(numberTxt, "<u>", "</u>");//电话
//                    String city = xx.subString(numberTxt, "color=blue>", ",");//城市
//                    city.replace("省", "");
//                    String city1 = city;
//                    //供应商
//                    String yunying = numberTxt.substring(numberTxt.indexOf(","), numberTxt.indexOf("</font>") + 3);
//                    yunying = yunying.substring(1, 3);
//                    //得到省会
//                    String sheng = null;
////                        排除了直辖市
//                    if (city1.startsWith("北京") || city1.startsWith("上海") || city1.startsWith("天津") || city1.startsWith("重庆")) {
//                        //                        如果是这两个省会  就从第三个开始截取
//                        sheng = city;
//                    } else if (city1.startsWith("黑龙江") || city1.startsWith("内蒙古")) {
//                        sheng = city.substring(0, 3);
//                        city1 = city1.substring(3);
//                    } else {
//                        sheng = city.substring(0, 2);
//                        city1 = city1.substring(2);
//                    }
//                    if (city1.contains("/")) {
//                        sheng = city1.substring(0, city1.indexOf("/"));
//                        city = city.substring(0, city.indexOf("/"));
//                    }
//                    String qvhao = null;
//                    //循环 区号
//                    for (Map.Entry<String, String> cityMap1 : cityMap.entrySet()) {
//                        if (city1.equals(cityMap1.getKey())) {
//                            qvhao = cityMap1.getValue();
//                        }
//                    }
//                    int sheng1 = 0;
//                    //循环判断得出  对应 省会的id
//                    for (Map.Entry<String, String> capitalMap1 : capitalMap.entrySet()) {
//                        if (sheng.equals(capitalMap1.getKey())) {
//                            sheng1 = Integer.parseInt(capitalMap1.getValue());
//                        }
//                    }
//                    int yunying1 = 0;
//                    if (yunying.equals("电信")) {
//                        yunying1 = 3;
//                    } else if (yunying.equals("联通")) {
//                        yunying1 = 2;
//                    } else if (yunying.equals("移动")) {
//                        yunying1 = 1;
//                    }
//                    sqlList.add("insert into T_PROVIDER_MOBILE (CODEFIELD, CITY, AREA, MOBILECARD, PROVINCE, PROV_ID, CREATE_TIME, ID, PROVIDER_ID)\n" +
//                            "values (" + iphone + ",'" + city + "','" + qvhao + "','"
//                            + yunying + "','" + sheng + "'," + sheng1 + ",sysdate" + ",null," + yunying1 + ");");
//                }
//                System.out.println("执行完成");
//                return sqlList;
//            }
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
