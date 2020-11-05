package com.shitou6.dao;

import com.shitou6.dao.tools.String1;
import com.shitou6.dao.tools.Input;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reptile extends JdbcUtiles {
    //引入格式化sql的类
    static SqlManager a = new SqlManager();

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    //爬虫   传入  要循环的号段  得到未结取的原始数据
    public static Map<String, Integer> Post(Map<Integer, Integer> xx9) throws Exception {
        Connection connection = newJdbc();

        //声明List集合,封装表单中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //工具方法  可以截取两个字符之间的值
        // 用来接取所有爬到的数据
        Map<String, Integer> map = new HashMap<>();
        String1 xx = new String1();
        //为防止 网站访问过多禁用  采用sleep
        int xx8 = 0;
        for (Integer integer : xx9.keySet()) {
            ++xx8;
            if (xx8 % 2 == 0) {
                Thread.sleep(2000);
            }
            if (xx8 % 3 == 0) {
                Thread.sleep(2000);
            }if (xx8 % 5 == 0) {
                Thread.sleep(2000);
            }
            Thread.sleep(1000);
            if (xx8 % 10 == 0) {
                Thread.sleep(10000);
            }
            if (xx8 % 16 == 0) {
                Thread.sleep(20000);
            }
            if (xx8 % 20 == 0) {
                Thread.sleep(20000);
            }
            System.out.println("要查询的号段是 = " + integer);
            //将post 请求中的  前台标签name  要查询的号段  放入对象中
            params.add(new BasicNameValuePair("haomas", integer + ""));
            //打开浏览器创建HttpClient对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建HttpPost请求,设置URL访问路径
            HttpPost httpPost = new HttpPost("http://www.1234i.com/p.php");
            //创建表单的Entity对象,第一个参数是封装好的表单数据,第二个是参数是编码
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
            //设置表单的Entity对象到Post请求中
            httpPost.setEntity(formEntity);


            //添加请求头
            httpPost.addHeader("Upgrade-Insecure-Requests","1");
            httpPost.addHeader("Accept-Language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
//            httpPost.addHeader("Content-Length","0");
//            httpPost.addHeader("Host","<calculated when request is sent>");
            httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
//            httpPost.addHeader("Accept","*/*");
//            httpPost.addHeader("Accept-Encoding","gzip, deflate, br");
//            httpPost.addHeader("Connection","keep-alive");


            //按回车发起请求,返回响应,使用HttpClient对象发起请求
            CloseableHttpResponse response = null;
            try {
                try {
                    response = httpClient.execute(httpPost);
                } catch (HttpHostConnectException a) {
                }

                //解析响应,
                try {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        String content = EntityUtils.toString(response.getEntity(), "GBK");
//                        System.out.println("--"+content+"---");
                        String xx1 = xx.subString(content, "</font><hr><p align=\"left\">", "<br><hr>");
                        System.out.println("得到一个   " + xx1);
                        if (xx1.indexOf("未知") != -1 || xx1.indexOf("#") != -1) {
                            String test = "update T_PROVIDER_LACK set LACK_STATUS=3 , LACK_DATE=SYSDATE WHERE LACK_IPHONE=" + xx9.get(integer);
                            PreparedStatement preState = connection.prepareStatement(test);
                            //执行查询语句
                            preState.execute();
                            preState.close();
                            System.out.println("更改状态为3");

                        } else if (xx1.indexOf("请不要反复刷新") != -1) {
                            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                            return map;
                        } else if(xx1!=null) {
                            String test = "update T_PROVIDER_LACK set LACK_STATUS=1 , LACK_DATE=SYSDATE WHERE LACK_IPHONE=" + xx9.get(integer);
                            PreparedStatement preState = connection.prepareStatement(test);
                            //执行查询语句
                            preState.execute();
                            preState.close();
                            System.out.println("更改状态为1");

                            map.put(xx1, xx9.get(integer));

                        }


                    }
                } catch (NullPointerException e) {

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            if (statement != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("爬虫执行结束");
        return map;


    }


    //查询缺失表 得到list<intger>集合  每次查询1万条
    public static synchronized void queryLock() {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        try {
            //得到连接
            Connection connection = null;
            connection = newJdbc();
            //得到lack表 所有状态为0的   0未初始化  1已新增 3扫描过了，查无此号
            PreparedStatement preState = connection.prepareStatement("select count(1) from T_PROVIDER_LACK where lack_status =0");
            //执行查询语句
            resultSet = preState.executeQuery();
            //用来处理值
            Integer prefix = null;
            //循环
            while (resultSet.next()) {
                //接到lack表 状态为0的总数量
                prefix = resultSet.getInt("count(1)");
            }
            System.out.println("缺省表共有 " + prefix);
            List<String> iphoneList = new ArrayList<>();
            Integer size = 0;
            //逻辑：循环，每次查询100条数据 给爬虫查询，然后写入数据库
            while (prefix / 100 > 0) {
                int xxz=prefix;
                prefix = prefix - 100;

                System.out.println("查询" + xxz + "条数据");
                System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                //第一次时候 只查100条  之后查N-100的数据
                if (size == 0) {
                    preState = connection.prepareStatement("" +
                            "select lack_iphone ,lack_status from T_PROVIDER_LACK  where lack_status=0 and rownum<=100");
                } else {
                    preState = connection.prepareStatement("" +
                            "SELECT  lack_iphone ,lack_status FROM T_PROVIDER_LACK WHERE  lack_status=0 and ROWNUM<" + size + 100 +
                            " minus SELECT  lack_iphone ,lack_status FROM T_PROVIDER_LACK WHERE  lack_status=0 and ROWNUM<" + size);
                }


                size = size + 100;
                //执行查询语句
                resultSet = preState.executeQuery();

                while (resultSet.next()) {
                    //接到lack表 状态为0的数据放到map中
                    map.put(resultSet.getInt("lack_iphone"), resultSet.getInt("lack_iphone"));

                }
                //爬虫爬到数据 放入数据库中   核心！！！！！
                try {
                    //爬虫
                    insertLack(a.sqlManager(Post(map), queryCity(), queryProvince()));

//                        List<String> sqlList1=sqlList.sqlManager(iphoneList,cityPath,capitalPath);
//                        System.out.println("sqlList1 = " + sqlList1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
//查询缺失表 得到list<intger>集合  每次查询1万条
    public static void queryLock1() {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        try {
            //得到连接
            Connection connection = null;
            connection = newJdbc();
            //得到lack表 所有状态为0的   0未初始化  1已新增 3扫描过了，查无此号
            PreparedStatement preState = connection.prepareStatement("select count(1) from T_PROVIDER_LACK where lack_status =0");
            //执行查询语句
            resultSet = preState.executeQuery();
            //用来处理值
            Integer prefix = null;
            //循环
            while (resultSet.next()) {
                //接到lack表 状态为0的总数量
                prefix = resultSet.getInt("count(1)");
            }
            System.out.println("缺省表共有 " + prefix);
            List<String> iphoneList = new ArrayList<>();
            Integer size = 0;
            //逻辑：循环，每次查询100条数据 给爬虫查询，然后写入数据库
            while (prefix / 100 > 0) {
                int xxz=prefix;
                prefix = prefix - 100;
                System.out.println("查询" + prefix + "条数据");
                System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
                //第一次时候 只查100条  之后查N-100的数据
                if (size == 0) {
                    preState = connection.prepareStatement("" +
                            "select lack_iphone ,lack_status from T_PROVIDER_LACK  where lack_status=3 and rownum<=100");
                } else {
                    preState = connection.prepareStatement("" +
                            "SELECT  lack_iphone ,lack_status FROM T_PROVIDER_LACK WHERE  lack_status=3 and ROWNUM<" + size + 100 +
                            " minus SELECT  lack_iphone ,lack_status FROM T_PROVIDER_LACK WHERE  lack_status=3 and ROWNUM<" + size);
                }


                size = size + 100;
                //执行查询语句
                resultSet = preState.executeQuery();

                while (resultSet.next()) {
                    //接到lack表 状态为0的数据放到map中
                    map.put(resultSet.getInt("lack_iphone"), resultSet.getInt("lack_iphone"));

                }
                //爬虫爬到数据 放入数据库中   核心！！！！！
                try {
                    //爬虫
                    insertLack(a.sqlManager(Post(map), queryCity(), queryProvince()));

//                        List<String> sqlList1=sqlList.sqlManager(iphoneList,cityPath,capitalPath);
//                        System.out.println("sqlList1 = " + sqlList1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    //读取省份表  得到省份和对应ID
    public static Map<String, String> queryProvince() {
        Map<String, String> map = new HashMap<>();
        try {
            //得到连接
            Connection connection = null;
            connection = newJdbc();
            //sql语句 查询状态为0的 0为未初始化
            PreparedStatement preState = connection.prepareStatement("select PROV_NAME,PROV_ID FROM T_PROVINCE");
            //执行查询语句
            resultSet = preState.executeQuery();

            //用来处理值
            Integer prefix = null;
            //得到 省份ID和名字  放入map
            while (resultSet.next()) {
                map.put(resultSet.getString("PROV_NAME"), resultSet.getString("PROV_ID"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }
    }


    //得到城市和区号
    public static Map<String, String> queryCity() {
        Map<String, String> map = new HashMap<>();
        try {
            //得到连接
            Connection connection = null;
            connection = newJdbc();
            //sql语句 查询状态为0的 0为未初始化
            PreparedStatement preState = connection.prepareStatement("select CODE,CITY from T_PROVINCE_CITY");
            //执行查询语句
            resultSet = preState.executeQuery();

            //用来处理值
            Integer prefix = null;
            //得到 省份ID和名字  放入map
            while (resultSet.next()) {
                map.put(resultSet.getString("CITY"), resultSet.getString("CODE"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }
    }

    //传入sql 运行
    public static void insertLack(Map<String, Integer> sql) {
        try {
            //得到连接
            Connection connection = null;
            connection = newJdbc();
            System.out.println(sql);
            Input input = new Input();
            //sql语句 查询状态为0的 0为未初始化
            for (String s : sql.keySet()) {
                System.out.println("----------" + s);
                PreparedStatement preState = connection.prepareStatement(s);
                //执行查询语句
                preState.execute();
                preState.close();
                System.out.println("sql——test");
//                input.Input(s, "C:\\Users\\admin\\Desktop\\giao.sql");
                //更新状态
                String test = "update T_PROVIDER_LACK set LACK_STATUS=1 , LACK_DATE=SYSDATE WHERE LACK_IPHONE=" + sql.get(s);
                System.out.println("test = " + test);
                preState = connection.prepareStatement(test);
                //执行查询语句
                preState.execute();
                preState.close();
                System.out.println("sql-update-1");

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
