package com.sap.refactoring.users;

import java.util.ArrayList;

import jakarta.transaction.Transactional;

public class UserDao
{
	public ArrayList<User> users;

	public static UserDao userDao;

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	@Transactional
	public void saveUser(User user) {
		if (users == null) {
			users = new ArrayList<User>();
		}
		users.add(user);
	}

	public ArrayList<User> getUsers() {
		try {
			return users;
		} catch (Throwable e) {
			System.out.println("error");
			return null;
		}
	}

	public void deleteUser(User userToDelete) {
		try {
			for (User user : users) {
				if (user.getName() == userToDelete.getName()) {
					users.remove(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User userToUpdate) {
		try {
			for (User user : users) {
				if (user.getName() == userToUpdate.getName()) {
					user.setEmail(userToUpdate.getEmail());
					user.setRoles(userToUpdate.getRoles());
					this.saveUser(user);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public User findUser(String name) {
		try {
			for (User user : users) {
				if (user.getName() == name) {
					return user;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}}
