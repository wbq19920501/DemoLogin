package com.wbq501.Dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddSql {

	public static void main(String[] args) throws Exception{
//		UserDao userDao = new UserDao();
//		UserBean userByName = userDao.getUserByName("666666");
//		if (userByName == null || userByName.equals(null)) {
//			System.out.println("可以注册");
//		}else {
//			System.out.println(userByName.toString());
//		}
		
//		boolean delMsg = userDao.DelMsg("aaa");
//		if (delMsg) {
//			System.out.println("删除成功");
//		}else {
//			System.out.println("删除失败");
//		}
		
//		boolean upDate = userDao.UpDate("fffffffff", "1234567");
//		if (upDate) {
//			System.out.println("修改密码成功");
//		}else {
//			System.out.println("修改密码失败");
//		}
//		String url = "http://localhost:8080/DemoLogin/Servlet/LoginServlet";
		String url = "http://119.29.100.81:8080/DemoLogin/Servlet/LoginServlet";
	    JSONObject params = new JSONObject();
	    params.put("username", "dsdfdsf");
	    params.put("password", "dsfdfsghdsfg");
	    
	    JSONArray array = new JSONArray();
	    array.put(params);
	    
	    String ret = doPost(url, array).toString();
	    System.out.println(ret);
		
	}
	/**
	   * post请求
	   * @param url
	   * @param json
	   * @return
	   */
	  
	  public static JSONArray doPost(String url,JSONArray json){
	    DefaultHttpClient client = new DefaultHttpClient();
	    HttpPost post = new HttpPost(url);
	    JSONArray response = null;
	    try {
	      StringEntity s = new StringEntity(json.toString());
	      s.setContentEncoding("UTF-8");
	      s.setContentType("application/json");//发送json数据需要设置contentType
	      post.setEntity(s);
	      HttpResponse res = client.execute(post);
	      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        HttpEntity entity = res.getEntity();
	        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	        System.out.println(result.toString());
	        response = new JSONArray(result);
	      }
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	    return response;
	  }
}
