package com.tilchina.edi.util;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Access数据库操作
 */
public class AccessDBUtil {
    private static final Logger logger=LoggerUtil.getLogger(AccessDBUtil.class);
    //配置文件管理器对象
    public static ConfigUtil configer=ConfigUtil.getInstance();
    //private static final String dbURL="jdbc:ucanaccess://"+"E:\\til\\报关系统\\关检合一\\20180731测试\\CustomData2004.accdb";
    //private static final String dbPassword="09E08AD93B08B00A72FB";
    /**
     * 加载驱动
     */
    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            logger.error("Problem in loading or registering MS Access JDBC driver");
            e.printStackTrace();
        }
    }


    //建立连接
    public static Connection getConn(String url,String password){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+url+";memory=false;jackcessOpener=com.tilchina.edi.util.CryptCodecOpener","",password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public static int update(String sql, List<Object> params) throws SQLException{
        int result = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String url=configer.getDBUrl();
            String password=configer.getDBPassword();
            conn = getConn(url,password);
            assert conn != null;//直接抛异常
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (params != null && !params.isEmpty()) {
                for (Object param : params) {
                    ps.setObject(index++, param);
                }
            }
            logger.info(sql+"::"+params.toString());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw e;
        } finally {
            close(conn, ps, null);
        }
        return result ;

    }

    /**
     * 查询多条记录
     *
     * @param sql    sql
     * @param params 参数
     * @return 查询结果
     */
    public static List<Map<String, Object>> select(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        int index = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String url=configer.getDBUrl();
            String password=configer.getDBPassword();
            conn = getConn(url,password);
            assert conn != null;
            ps = conn.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (Object param : params) {
                    ps.setObject(index++, param);
                }
            }
            logger.info(sql+"::"+params.toString());
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int col_len = metaData.getColumnCount();
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<>();
                for (int i = 0; i < col_len; i++) {
                    String cols_name = metaData.getColumnName(i + 1);
                    Object cols_value = rs.getObject(cols_name);
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    map.put(cols_name, cols_value);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn, ps, rs);
        }
        return list;
    }

    public static void main(String[] args){
        try {
            List<Map<String,Object>> result=AccessDBUtil.select("SELECT * from form_head where cop_no <> '' ",null);
            System.out.println(result.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
