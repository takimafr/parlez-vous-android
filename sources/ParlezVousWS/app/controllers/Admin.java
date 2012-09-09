package controllers;

import java.util.List;

import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Admin extends Controller {

	public static void list(String password) {
		List<User> users = User.findAll();
		render(users);
	}
}