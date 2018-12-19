package com.tjzs.zscms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tjzs.zscms.exception.SysException;

/**
 * 操作数据库的中间件
 * 
 * @author Administrator 
 */
public class DButil {
	// 驱动
	private String driver = "com.mysql.jdbc.Driver";
	// 连接地址，参数是支持中文输入
	private String url = "jdbc:mysql://localhost:3306/zscms?useUnicode=true&characterEncoding=utf-8";
	// 连接用户名
	private String user = "root";
	// 密码
	private String password = "wangxuena123";

	/**
	 * 获得数据库连接的方法，不允许外界调用
	 * 
	 * @return
	 */
	private Connection getConn() {
		Connection conn = null;
		try {
			// 加载并注册驱动
			Class.forName(driver);
			// 获得数据库连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//返回数据库
		return conn;
	}

	/**
	 * 关闭资源的方法
	 * 
	 * @param rs 结果集
	 * @param ps  处理对象       
	 * @param conn 连接            
	 */
	private void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			//结果集不为空时，关闭结果集资源
			if (rs != null) {
				rs.close();
			}
			//处理对象不为空时，关闭处理对象的资源
			if (ps != null) {
				ps.close();
			}
			//连接不为空时，关闭连接的资源
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 增删改的方法
	 * 
	 * @param sql  需要执行的带参SQL语句           
	 * @param objs 带参SQL语句内需要设置的参数           
	 * @return 执行结果
	 * @throws SysException
	 */
	public int execUpdate(String sql, Object[] objs) throws SysException {
		// 获得数据库连接
		Connection conn = this.getConn();
		// 声明预编译对象
		PreparedStatement ps = null;
		// 如果连接为空时不能执行以下内容，返回数据库影响表格记录的个数为0
		if (conn == null) {
			return 0;
		}
		try {
			// 从连接中获得预编译对象
			ps = conn.prepareStatement(sql);
			// 依次将参数set到ps中，参数注入
			if(objs!=null){
				for (int i = 0; i < objs.length; i++) {
					// 索引从1开始
					ps.setObject(i + 1, objs[i]);
				}
				
			}
			// 返回修改数据后影响表格的记录的个数
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			//数据库的异常信息
			throw new SysException(1006, "系统异常，请联系管理员");
		} finally {
			//关闭资源
			this.close(null, ps, conn);
		}
	}

	/**
	 * 查询的方法
	 * 
	 * @param sql  带参的SQL语句           
	 * @param objs  为SQL中的参数，因为不知道是int还是string，所以用object存参数
	 * @return list<map<string,object>>不知道查询得到的数据为哪个Bean类型，所以用map来封装数据
	 * map的key为数据字段名，value为数据库的字段值
	 * @throws SysException
	 */
	public List<Map<String, String>> execQuery(String sql, Object[] objs)
			throws SysException {
		// 获得连接
		Connection conn = this.getConn();
		// 定义对象的变量初始化为null
		PreparedStatement ps = null;
		// 定义结果集初始化为null
		ResultSet rs = null;
		// 定义一个用于存放封装数据的Map集合的List集合
		List<Map<String, String>> maps = new ArrayList<>();
		try {
			// 从数据库连接中获得处理对象
			ps = conn.prepareStatement(sql);
			// 把参数注入到SQL中
			if(objs!=null){
				for (int i = 0; i < objs.length; i++) {
					// 依次把数组内的参数取出set到ps的i+1位置
					// 数组从0开始，参数的位置从1开始，所以是i+1
					ps.setObject(i + 1, objs[i]);
				}
			}
			// 获得结果集
			rs = ps.executeQuery();
			// 获得结果集的结构
			ResultSetMetaData rm = rs.getMetaData();
			// 依次从结果集中取出值
			while (rs.next()) {
				// map集合用于存放一条数据
				Map<String, String> map = new HashMap<>();
				// 把数据封装到map
				for (int i = 1; i <= rm.getColumnCount(); i++) {
					// 结果集结构中获得字段名rm.getColumnName(i),i从1开始
					// 结果集获得字段值rs.getObject(i),i从1开始
					map.put(rm.getColumnName(i), rs.getString(i));
				}
				// 把一条信息放入List集合
				maps.add(map);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new SysException(1006, "系统异常，请联系管理员");
		} finally {
			// 调用本类的关闭资源
			this.close(rs, ps, conn);
			return maps;
		}

	}

	// public static void main(String[] args) {
	// //创建对象
	// DButil db=new DButil();
	// //设置SQL语句，需调用方法内的实参
	// String sql="select * from t_test";
	// //将形参全部存入数组
	// Object[] objs={};
	// List<Map<String, String>> execQuery=db.execQuery(sql, objs);
	// for (Map<String, String> map : execQuery) {
	// Set<String> key=map.keySet();
	// for (String string : key) {
	// System.out.print(string+"="+map.get(string));
	// }
	// System.out.println();
	// }
	//

	// 新增
	// String sql="insert into test values(?,?,?,?,?)";
	// Object[] objs={null,'娜','女',22,'秦'};
	// 修改
	// String sql="update test  set name=?,age=? where id=?";
	// Object[] objs={'旺',24,2};
}
