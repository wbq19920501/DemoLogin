package com.wbq501.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wbq501.Bean.UserBean;
import com.wbq501.Dao.UserDao;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		String reqMessage, respMessage;
		JSONArray reqObject = null;
		JSONArray respObject = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			reqMessage = sb.toString();
			System.out.println("请求报文:" + reqMessage);
			reqObject = new JSONArray(reqMessage);
			UserDao userDao = new UserDao();
			UserBean ub = userDao.getUserByName(reqObject.getJSONObject(0)
					.getString("username"));
//			if (ub.getPassword() != null
//					&& ub.getPassword().equals(
//							reqObject.getJSONObject(0).getString("password"))) {
//				respObject = new JSONArray().put(new JSONObject().put("userId",
//						ub.getId()));
//			}
			respObject = new JSONArray();
			for (int i = 0; i < 3; i++) {
				JSONObject object = new JSONObject();
				object.put("name", ub.getName());
				object.put("id", ub.getId());
				object.put("password", ub.getPassword());
				object.put("username", ub.getUsername());
				respObject.put(object);
			}
//			respObject = new JSONArray().put(respObject);
//			respObject = new JSONArray().put(new JSONObject().put("userId",object));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			respMessage = respObject == null ? "" : respObject.toString();
			System.out.println("返回报文:" + respMessage);
			PrintWriter pw = response.getWriter();
			pw.write(respMessage);
			pw.flush();
			pw.close();
		}
	}
}
