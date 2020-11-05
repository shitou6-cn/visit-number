package com.shitou6.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtiles {
    //数据库url
    public static String url = "jdbc:oracle:thin:@192.168.11.112:1521:oracle11";
    //数据库连接驱动
    public static String driver = "oracle.jdbc.OracleDriver";
    //用户名
    public static String username = "test";
    //密码
    public static String password = "test";


    public static Connection connect = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;

    //此方法用来获得数据库链接
    public static Connection newJdbc() throws ClassNotFoundException, SQLException {
        //类加载
        Class.forName(driver);
        //利用DriverManager
        connect = DriverManager.getConnection(url, username, password);
        return connect;
    }

    //此方法用来调用存储过程  传入存储过程名  和传入的字段
    public static void storage(String storage, Integer one, Integer two, Integer size) {
        try {
            //得到连接
            Connection connection = newJdbc();
            CallableStatement cstmt = null;
            CallableStatement proc = null; // 创建执行存储过程的对象
            String x = "{ call " + username + "." + storage + "(?,?,?) }";
            proc = connection.prepareCall(x); // 设置存储过程// call为关键字.
            // 设置输入参数
//            System.out.println("one = " + one+";"+two+";"+size);
//            System.out.println(username+"---"+storage);
            proc.setInt(1, one); // 设置第一个输入参数
            proc.setInt(2, two); // 设置第二个输入参数
            proc.setInt(3, size); // 设置第三个输入参数  循环的长度
            proc.execute();// 执行
            System.out.println("初始化循环表完成-----");
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

    //调用存储过程     T_PROVIDER_ROTATE表减去表T_PROVIDER_MOBILE 缺省值放入T_PROVIDER_MOBILE_LACK表  传入参数：存储过程名字 LACK_INIT
    public static void storage1(String storage) {
        try {
            Connection connection = newJdbc();
            CallableStatement proc = null; // 创建执行存储过程的对象
            String x = "{ call " + username + "." + storage + "}";
            proc = connection.prepareCall(x); // 设置存储过程
            proc.execute();// 执行
            System.out.println("初始化缺省表完成-----");
            proc = connection.prepareCall("TRUNCATE TABLE T_PROVIDER_ROTATE");
            proc.execute();
            System.out.println("清空循环表完成----");
//            TRUNCATE TABLE T_PROVIDER_ROTATE;
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

    //循环表转缺失表
    public static void transfer(String storage) {
        try {
            Connection connection = newJdbc();
            CallableStatement proc = null; // 创建执行存储过程的对象
            String x = "{ call " + username + "." + storage + "}";
            proc = connection.prepareCall(x); // 设置存储过程
            proc.execute();// 执行
            System.out.println("缺省表移植完成 且删除循环表数据-----");
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

    ///**
    // * 用于首次建立爬虫 完善数据
    // * 步骤：
    // * 1.读取目前数据库已有号段表  3位或4位   number_status=0
    // * 2.循环传入存储过程，循环10000次放入循环表  number_status=1
    // */
    public static void init(String proc, Integer size) {
        try {
            //得到连接
            Connection connection = null;
            connection = newJdbc();
            //sql语句 查询状态为0的 0为未初始化
                PreparedStatement preState = connection.prepareStatement("select MOBILE_PREFIX FROM T_PROVIDER_MOBILE_PREFIX WHERE NUMBER_STATUS=0");
            //执行查询语句
            resultSet = preState.executeQuery();
            //用来接查询的号段
            List<Integer> list = new ArrayList<>();
            //用来处理值
            Integer prefix = null;
            //循环 最后得到每个号段的0号开始
            while (resultSet.next()) {
                prefix = resultSet.getInt("MOBILE_PREFIX");
                list.add(prefix);
            }
            //调用存储过程 传入   三个参数   调用存储过程名字   号段（1630000） 循环次数  1630000~1639999  传入10000
            for (Integer integer : list) {
                System.out.println("新增号段：" + integer);
                if (integer.toString().length() == 3) {

                    storage(proc, integer * 10000, integer, size);
                    System.out.println(integer * 10000);

                } else if (integer.toString().length() == 4) {
                    storage(proc, integer * 1000, integer, size / 10);
                    System.out.println(integer * 1000);
                }


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
    //传入三位或四位号段
    public static void insert(Integer number, String call) {
        try {
            Connection connection = newJdbc();
            String sql = "insert into T_PROVIDER_MOBILE_PREFIX (MOBILE_PREFIX，PROVIDER_ID,NUMBER_STATUS,REVERSION) VALUES (" + call + "," + number + ",0,sysdate) ";
            System.out.println("插入语句是 = " + sql);
            PreparedStatement preState = connection.prepareStatement(sql);
            //执行插入语句
            preState.execute();
            preState.close();
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
