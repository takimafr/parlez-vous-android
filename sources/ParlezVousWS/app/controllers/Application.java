package controllers;

import java.util.List;

import models.Message;
import models.User;
import play.mvc.Controller;

public class Application extends Controller {

	private static final String ACCESS_DENIED = "access denied";

	public static void index() {
		render();
	}

	public static void subscribe() {
		String message;
		boolean ok = true;
		String username = request.params.get("username");
		String password = request.params.get("password");

		if (!User.find("byName", username).fetch().isEmpty()) {
			message = "Ce nom d'utilisateur est déjà pris.";
			ok = false;
			render(ok, message);
		}

		User user = new User(username, password);
		user.save();
		render(ok);
	}

	public static void connect(String username, String password) {
		List<User> users = User.find("byName", username).fetch();
		if (users.isEmpty()) {
			System.out.println(username + " ne s'est pas incrit! ");
			renderText(false);
		}

		User user = users.get(0);
		if (user.password.equals(password)) {
			System.out.println(user.name + " vient de se connecter! ");
			renderText(true);
		} else {
			System.out.println(user.name + " s'est trompé de mot de passe! " );
			renderText(false);
		}
	}

	public static void message(String username, String password, String message) {
		List<User> users = User.find("byName", username).fetch();
		if (users.isEmpty()) {
			renderText(ACCESS_DENIED);
		}

		//Suppress forbidden character ":" and ";"
		message = message.replaceAll(";", "");
		message = message.replaceAll(":", "");
		
		User user = users.get(0);
		if (user.password.equals(password)) {
			Message aMessage = new Message(message, user);
			aMessage.save();
		}
	}

	public static void list(String username, String password) {
		List<User> users = User.find("byName", username).fetch();
		if (users.isEmpty()) {
			renderText(ACCESS_DENIED);
		}

		User user = users.get(0);
		if (user.password.equals(password)) {
			List<Message> messages = Message.all().fetch();
			String result = "";
			for (Message message : messages) {
				result+=message+";";
			}
			renderText(result);
		}
	}

}