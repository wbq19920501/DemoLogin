package com.wbq501.Bean;

public class UserBean {
	private int id;
	private String username;
	private String name;
	private String password;
	
	public UserBean() {
		
	}

	public UserBean(int id, String username, String name, String password) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + "]";
	}
	
}
