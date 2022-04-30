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

	// Menu option 1
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "select f.id, f.title, f.rating, f.description, l.name \n"
					+ "FROM film f JOIN language l ON l.id = f.language_id where f.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film();
				// Here is our mapping of query columns to our object fields:
				film.setId(filmId);
				film.setTitle(rs.getString("title"));
				film.setRating(rs.getString("rating"));
				film.setDesc(rs.getString("description"));
				film.setLanguage(rs.getString("name"));
				film.setActors(findActorsByFilmId(filmId));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	// Add cast to selected film
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name \n"
					+ "FROM film_actor  JOIN film ON film.id = film_actor.film_id\n"
					+ "          		JOIN actor on film_actor.actor_id = actor.id where film.id = ?";
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

	// Menu option 2 WORKING
	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> filmList = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

//			String sql = "select id, title, rating, description \n" + "from film "
//					+ "where title like ? or description like ?";

			String sql = "select f.id, f.title, f.rating, f.description, l.name, a.first_name, a.last_name \n"
					+ "FROM film f   JOIN film_actor fa ON fa.actor_id = f.id\n"
					+ "              JOIN actor a ON a.id = fa.film_id \n"
					+ "              JOIN language l ON l.id = f.language_id where f.title like ? or f.description like ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				film = new Film();
				// Here is our mapping of query columns to our object fields:
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setRating(rs.getString("rating"));
				film.setDesc(rs.getString("description"));
//				film.setLanguage(rs.getString("name"));
				filmList.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}
}
