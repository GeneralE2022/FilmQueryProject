package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	public FilmQueryApp() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.displayData(app.getUserInput());
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private int getUserInput() {
		Scanner sc = new Scanner(System.in);
		int input = 0;
		System.out.print("1. Enter an actor's ID to display it's data ");
		input = sc.nextInt();
		sc.close();
		return input;
	}

	public void displayData(int input) {
		DatabaseAccessorObject databaseAccessorObject = new DatabaseAccessorObject();
//		System.out.println(databaseAccessorObject.findFilmById(input));
		System.out.println(databaseAccessorObject.findFilmsByActorId(input));

	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load database driver:");
			e.printStackTrace();
			System.err.println("Exiting.");
			System.exit(1); // No point in continuing.
		}
	}
}
