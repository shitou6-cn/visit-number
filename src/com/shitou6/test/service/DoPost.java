//package com.baimiaojunye.work.service;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DoPost {
//    //爬虫   传入  要循环的号段
//    public static List<String> Post(List<Integer> xx9) throws Exception {
//        //声明List集合,封装表单中的参数
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        //工具方法  可以截取两个字符之间的值
//        // 用来接取所有爬到的数据
//        List<String> set = new ArrayList<String>();
//        String1 xx = new String1();
//        //为防止 网站访问过多禁用  采用sleep
//        int xx8 = 0;
//        for (Integer s : xx9) {
//            ++xx8;
//            if (xx8 % 5 == 0) {
//                Thread.sleep(1000);
//            }
//            Thread.sleep(100);
//            //将post 请求中的  前台标签name  要查询的号段  放入对象中
//            params.add(new BasicNameValuePair("haomas", s+""));
//            //打开浏览器创建HttpClient对象
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            //创建HttpPost请求,设置URL访问路径
//            HttpPost httpPost = new HttpPost("http://www.1234i.com/p.php");
//            //创建表单的Entity对象,第一个参数是封装好的表单数据,第二个是参数是编码
//            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
//            //设置表单的Entity对象到Post请求中
//            httpPost.setEntity(formEntity);
//            //按回车发起请求,返回响应,使用HttpClient对象发起请求
//            CloseableHttpResponse response = null;
//            try {
//                response = httpClient.execute(httpPost);
//                //解析响应,
//                if (response.getStatusLine().getStatusCode() == 200) {
//                    String content = EntityUtils.toString(response.getEntity(), "GBK");
//                    String xx1 = xx.subString(content, "</font><hr><p align=\"left\">", "<center><p>&nbsp;</p>");
//                    System.out.println("得到一个   "+xx1);
//                    if (xx1.indexOf("未知")!=-1){
////                        System.out.println(xx1);
//
//
//                    }else{
//                        set.add(xx1);
//                    }
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                //释放资源
//                if (response == null) {
//                    try {
//                        response.close();
//                        httpClient.close();//关闭浏览器
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        System.out.println("Post执行结束");
//        return set;
//    }
//}
//
