package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	String user = "student";
	String pass = "student";

	// Menu option 1, display error if film id not present
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select film.id, film.title, film.rating, film.description, language.name from film join language on film.language_id = language.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				// Here is our mapping of query columns to our object fields:
				film.setId(filmId);
				film.setTitle(rs.getString("title"));
				film.setRating(rs.getString("rating"));
				film.setDesc(rs.getString("description"));
				film.setLanguage(rs.getString("name"));
			}
			if (film == null) {
				System.out.println("Your movie was not found in our database, please enter a different film id.");
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	// Menu option 2
	@Override
	public Film findFilmByKeyword(String keyword) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, title, description FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDesc(rs.getString("description"));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}


//	public List<Film> findFilmsByActorId(int actorId) {
//		List<Film> films = new ArrayList<>();
//		try {
//			Connection conn = DriverManager.getConnection(url, user, pass);
//			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
//			sql += " rental_rate, length, replacement_cost, rating, special_features "
//					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, actorId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String title = rs.getString(2);
//				String desc = rs.getString(3);
//				Integer releaseYear = rs.getInt(4);
//				int langId = rs.getInt(5);
//				int rentDur = rs.getInt(6);
//				double rate = rs.getDouble(7);
//				Integer length = rs.getInt(8);
//				double repCost = rs.getDouble(9);
//				String rating = rs.getString(10);
//				String features = rs.getString(11);
//				List<Actor> actors = rs.getObject(12);
//				Film film = new Film(id, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
//						features, actors);
//				films.add(film);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return films;
//	}


	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, first_name, last_name "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);

				Actor actor = new Actor(id, first_name, last_name);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}
}



