package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public FilmQueryApp() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}


	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		FilmQueryApp app = new FilmQueryApp();
		app.displayMenu();
	}


	public void displayMenu() {
		Scanner sc = new Scanner(System.in);
		int input = 0;

		while (input != 3) {
			System.out.println();
			System.out.println("(1) Look up a film by its id.");
			System.out.println("(2) Look up a film by a search keyword.");
			System.out.println("(3) Exit the application.");
			input = sc.nextInt();

			switch (input) {
			case 1:
				int filmInput = 0;
				System.out.println("Enter a film id to look up.");
				filmInput = sc.nextInt();
				menuOne(filmInput);
				break;

			case 2:
				String filmKeyword = "";
				System.out.println("Search our movie titles by keyword: ");
				filmKeyword = sc.next();
				menuTwo(filmKeyword);
				break;

			default:
				break;
			}
		}
	}

	public void menuOne(int filmInput) {
		Film film = db.findFilmById(filmInput);
		if (film == null) {
			System.out.println("Your movie was not found in our database, please enter a different film id.");
		} else {
			System.out.println(film);
		}
	}

	public void menuTwo(String keyword) {
		List<Film> printableFilms = db.findFilmByKeyword(keyword);
		for (Film film : printableFilms) {
			System.out.println(film);
		}
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
