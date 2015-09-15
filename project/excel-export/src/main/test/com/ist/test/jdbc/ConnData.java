package com.ist.test.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class ConnData {
	/**数据库用户名**/
	public static String DBUSERNAME = "awp4";
	/**数据库密码**/
	public static String DBPASSWORD = "awp4";
	/**数据库IP**/
	public static String DBIP = "10.6.50.110";
	/**数据库名称**/
	public static String DBNAME = "oradt";
	
	public static String DBTYPE = "oracle";
	
	public static Connection getConnection() throws SQLException {
		//
		Connection conn = null;
		try { 
			
			String DBUser = DBUSERNAME;
			String DBPassword = DBPASSWORD;
			String DBDriver = "oracle.jdbc.driver.OracleDriver";
			String DBUrl = "jdbc:oracle:thin:@"+DBIP+":1521:"+DBNAME; 
		 
			Class.forName(DBDriver);
			Properties myprop = System.getProperties();

			myprop.setProperty("user", DBUser);
			myprop.setProperty("password", DBPassword);

			conn = DriverManager.getConnection(DBUrl, myprop);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static ArrayList executeQuery(Connection conn, String sql) throws java.sql.SQLException {
		return executeQuery(conn, sql, null);
	}
	
	public static ArrayList executeQuery(Connection conn, String sql,
			ArrayList list) throws java.sql.SQLException {

		ArrayList returnList = new ArrayList();

		if (sql == null) {
			return null;
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TreeMap hLine = null;
		int i = 1;

		String dbUseCode = "UTF-8";

		try {
            sql=replaceSql(sql);
			stmt = conn.prepareStatement(sql);
			if (list != null && list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					stmt.setObject(j + 1, (Object) list.get(j));
				
				}
			}
			rs = stmt.executeQuery();

			while (rs.next()) {
				hLine = new TreeMap();
				for (int ii = 1; ii <= rs.getMetaData().getColumnCount(); ii++) {

					String colName = rs.getMetaData().getColumnName(ii)
							.toUpperCase();// .toUpperCase();

					int colType = rs.getMetaData().getColumnType(ii);

					if (colType == java.sql.Types.TIME
							|| colType == java.sql.Types.DATE
							|| colType == java.sql.Types.TIMESTAMP) {
						java.sql.Timestamp dd = rs.getTimestamp(ii);
						long values = 0;
						if (dd != null) {
							values = dd.getTime();
						}
						java.util.Date d = new java.util.Date();
						d.setTime(values);
						String v = "";
						if(colType == java.sql.Types.DATE){
							v = dateToString10(d);
						}else{
							v = dateToString(d);
						}  
						if(StringUtils.isNotBlank(v)){
							hLine.put(colName, v);
						}
						
					} else {
						String values = rs.getString(ii);

						if (values == null) {
							values = "";
						}

						if(StringUtils.isNotBlank(values)){
							hLine.put(colName, values);
						}
						
					}
				}
				returnList.add(hLine);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {

			}
		}
		return returnList;
	}
	
	/**
	 * 把本地的字符串，转化为数据库内部的字符串
	 */
	public static String interlize(String ori, String dbUseCode) {
		if (ori == null || ori.length() == 0) {
			return "";
		}
		if (dbUseCode == null || dbUseCode.length() == 0) {
			return ori;
		}
		try {
			byte[] byte1 = ori.getBytes("GBK");
			return new String(byte1, dbUseCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ori;
		}
	}
	
	public  static String replaceSql(String sql){
		String sql_low=sql.toLowerCase();
	    int start_index=sql_low.indexOf("'");
		while(start_index>=0){
			String oldStr=sql_low.substring(start_index+1);
			int last_index=oldStr.indexOf("'");
			oldStr=oldStr.substring(0,last_index);
			String newStr=oldStr.toUpperCase();
			if(newStr.equals("TT")){
				newStr=oldStr.toLowerCase();
			}
			sql_low=sql_low.replaceAll("'"+oldStr+"'", "&"+newStr+"&");
			start_index=sql_low.indexOf("'");
		}
		sql_low=sql_low.replaceAll("&", "'");
		sql_low=sql_low.replaceAll("%Y-%M-%D", "%Y-%m-%d");
		

		return sql_low;
	}
	
    /**
     * 日期转化为字符串
     * @param date 时间
     * @return yyyy-MM-dd HH:mm:ss 格式化的时间字符串
     */
    public static String dateToString(Date date) {
        if(date==null) return "";
        return FormatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 日期转化为字符串
     * @param date 时间
     * @return yyyy-MM-dd HH:mm:ss 格式化的时间字符串
     */
    public static String dateToString10(Date date) {
        if(date==null) return "";
        return FormatDate(date, "yyyy-MM-dd");
    }
    
    /**
     * 对日期进行格式化
     * @param date 日期
     * @param sf 日期格式
     * @return 字符串
     */
    public static String FormatDate(Date date, String sf) {
        if(date==null) return "";
        SimpleDateFormat dateformat = new SimpleDateFormat(sf);
        return dateformat.format(date);
    }
    
	/**
	 * 把数据库内部的字符串，转化为本地的字符串
	 */

	public static String localize(String ori) {

		if (ori == null || ori.length() == 0) {
			return "";
		}
		String dbUseCode = "GBK";
		if (dbUseCode == null || dbUseCode.length() == 0) {
			return ori;
		}
		try {
			byte[] byte1 = ori.getBytes(dbUseCode);
			return new String(byte1, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return ori;
		}

	}
	
	/**
	 * 把数据库内部的字符串，转化为本地的字符串
	 */

	public static String localize(String ori, String dbUseCode) {

		if (ori == null || ori.length() == 0) {
			return "";
		}
		if (dbUseCode == null || dbUseCode.length() == 0) {
			return ori;
		}
		try {
			byte[] byte1 = ori.getBytes("utf-8");
			String msg = new String(byte1, "GBK");
			if(msg.indexOf("?")!= -1){
				return "测试.." + RandomUtils.nextDouble();
			}
			return msg;
			/*String msg = new String(ori.getBytes(),"utf-8");
			return msg;*/
		} catch (Exception e) {
			e.printStackTrace();
			return ori;
		}

	}

}
