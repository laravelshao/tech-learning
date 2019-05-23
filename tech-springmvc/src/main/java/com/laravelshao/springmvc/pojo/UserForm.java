package com.laravelshao.springmvc.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserForm {
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserForm [users=" + users + "]";
	}

}
