package com.wbq501.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wbq501.Bean.UserBean;

public class UserDao extends BaseDao {
	public PreparedStatement prepStmt = null;
	public ResultSet rs = null;
	public UserBean getUserByName(String src){
		UserBean userBean = null;
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "select * from users where username = ?";
				prepStmt = conn.prepareStatement(sql);
				prepStmt.setString(1,src);
				rs = prepStmt.executeQuery();
				if(rs.next()){
					userBean = new UserBean(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(3));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(prepStmt!=null)
					prepStmt.close();
				if(conn!=null)
					conn.close();
				super.closeDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userBean;
	}
	public void INSERT(String username,String password,String name){
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "insert INTO users (username,password,name) VALUES ('"
						+username+"','"+password+"','"+name+"');";
				prepStmt = conn.prepareStatement(sql);
				prepStmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(prepStmt!=null)
					prepStmt.close();
				if(conn!=null)
					conn.close();
				super.closeDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<UserBean> SELECT(){
		UserBean userBean = null;
		ArrayList<UserBean> lists = new ArrayList<>();
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "select * from users;";
				prepStmt = conn.prepareStatement(sql);
				rs = prepStmt.executeQuery();
				rs.last();
				int columnCount = rs.getRow();
				rs.first();
				for (int i = 1; i <= columnCount; i++) {   
	               	userBean = new UserBean(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(3));
	               	lists.add(userBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(prepStmt!=null)
					prepStmt.close();
				if(conn!=null)
					conn.close();
				super.closeDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lists;
	}
	public boolean DelMsg(String username){
		boolean succeed = false;
		UserDao userDao = new UserDao();
		UserBean userByName = userDao.getUserByName(username);
		if (userByName == null || userByName.equals(null)) {
			
		}else {
			try {
				conn = super.openDB();
				if(conn!=null){
					String sql = "DELETE FROM users WHERE username = '"+username+"';";
					prepStmt = conn.prepareStatement(sql);
					prepStmt.execute();
					succeed = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(prepStmt!=null)
						prepStmt.close();
					if(conn!=null)
						conn.close();
					super.closeDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return succeed;
	}
	public boolean UpDate(String username,String password){
		boolean succeed = false;
		UserDao userDao = new UserDao();
		UserBean userByName = userDao.getUserByName(username);
		if (userByName == null || userByName.equals(null)) {
			
		}else {
			try {
				conn = super.openDB();
				if(conn!=null){
					String sql = "update users set password = '"+password+"' where username = '"+username+"';";
					prepStmt = conn.prepareStatement(sql);
					prepStmt.executeUpdate();
					succeed = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(prepStmt!=null)
						prepStmt.close();
					if(conn!=null)
						conn.close();
					super.closeDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return succeed;
		
	}
}
