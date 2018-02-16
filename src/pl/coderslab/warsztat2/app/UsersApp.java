package pl.coderslab.warsztat2.app;

import pl.coderslab.warsztat2.model.Users;

public class UsersApp {

	public static void main(String[] args) {
		test();

	}

	static void test() {
		Users user = new Users("Maciek", "mkotek@gmail.pl", "kotek");
		System.out.println(user.getPassword());
		System.out.println(user.isPasswordCorrect("kote"));
		System.out.println(user.isPasswordCorrect("kotek"));
	}
}
