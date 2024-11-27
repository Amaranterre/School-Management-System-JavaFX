package home.controllers;

import home.controllers.DataBase;
import home.model.UserModel;

public class Authenticator {
	
	private static boolean is_legal(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		return !str.contains(" ");
	}
	
	
	public static boolean login(String username, String password) {
		username = username.trim();
		password = password.trim();
		if (!is_legal(username) || !is_legal(password)) {
			return false;
		}

		// TODO: optimize the checking procedure
		for (UserModel user: DataBase.getAllUsers()) {
			if(username.equals(user.getName()) && password.equals(user.getPwd())) {
				return true;
			}
		}
		
		return false;
	}
}
