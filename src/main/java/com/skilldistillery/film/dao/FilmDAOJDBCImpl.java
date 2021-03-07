package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOJDBCImpl implements FilmDAO {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// URL for connection to database
	private String URL = "jdbc:mysql://localhost:3306/sdvid";

////////
// Architecture for this class
// General procedure:
	// Create connection --> create String for SQL query -->
	// create PreparedStatement --> create ResultSet
	// Compute with ResultSet
	// Close resources (ResultSet, PreparedStatement, and Connection)
	//
// Implementation:
	// Connection con = DriverManager.getConnection(URL, [user_id],
	// [user_password]);
	// PreparedStatement ps = con.prepareStatement(sql);
	// ResultSet rs = ps.executeQuery();
	//
// Conventions: String for SQL query: sql[QueryName]

////////
// SQL queries for use in methods
	private String sqlActorsByFilmId = "SELECT a.* FROM actor a JOIN film_actor fa "
			+ "ON a.id = fa.actor_id JOIN film f ON f.id = fa.film_id WHERE f.id = ?" + " ORDER BY a.last_name";

	private String sqlCategoriesByFilmId = "SELECT c.* FROM category c "
			+ "JOIN film_category fc ON c.id = fc.category_id " + "JOIN film f ON f.id = fc.film_id WHERE f.id = ?"
			+ " ORDER BY c.name";

	private String sqlFilmById = "SELECT id, " // query index 1
			+ "title, " // 2
			+ "description, " // 3
			+ "language_id, " // 4
			+ "rental_duration, " // 5
			+ "rental_rate, " // 6
			+ "length, " // 7
			+ "replacement_cost, " // 8
			+ "rating, " // 9
			+ "special_features, " // 10
			+ "release_year " // 11
			+ "FROM film WHERE id = ?"; // bind var is film.id

	private String sqlActorById = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

	private String sqlFilmsByKeyword = "SELECT id, " // query index 1
			+ "title, " // 2
			+ "description, " // 3
			+ "language_id, " // 4
			+ "rental_duration, " // 5
			+ "rental_rate, " // 6
			+ "length, " // 7
			+ "replacement_cost, " // 8
			+ "rating, " // 9
			+ "special_features, " // 10
			+ "release_year " // 11
			+ "FROM film WHERE title LIKE ? OR description LIKE ?";

	private String sqlLanguageOfFilm = "SELECT l.name FROM language l JOIN film f "
			+ "ON l.id = f.language_id WHERE f.id = ?";

	private String sqlCreateFilmCheckId = "SELECT id FROM film WHERE title=? AND description=?";

	private String sqlDeleteFilm = "DELETE FROM film WHERE id = ?";

	private String sqlChangeTitle = "UPDATE film SET title=? WHERE id=?";

////////

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String lang = "";
		List<Actor> actorsList = new ArrayList<>();
		List<Category> categories = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(URL, "student", "student")) {

			PreparedStatement ps = con.prepareStatement(sqlFilmById);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setLanguageId(rs.getInt(4));
				film.setRentalDuration(rs.getInt(5));
				film.setRentalRate(rs.getDouble(6));
				film.setLength(rs.getInt(7));
				film.setReplacementCost(rs.getDouble(8));
				film.setRating(rs.getString(9));
				film.setSpecialFeatures(rs.getString(10));
				film.setReleaseYear(rs.getInt(11));
				// Pass Connection object to overloaded methods to avoid making
				// multiple Connections per query
				lang = findLanguageOfFilm(con, film);
				film.setLanguagePlainText(lang);
				actorsList = findActorsByFilmId(filmId, con);
				film.setActorsList(actorsList);
				categories = findCategoriesByFilmId(filmId, con);
				film.setCategories(categories);

			}
			rs.close();
			ps.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String URL = "jdbc:mysql://localhost:3306/sdvid";

		try (Connection con = DriverManager.getConnection(URL, "student", "student")) {
			PreparedStatement ps = con.prepareStatement(sqlActorById);
			ps.setInt(1, actorId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));
			}
			rs.close();
			ps.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorsList = null;

		try (Connection con = DriverManager.getConnection(URL, "student", "student")) {

			PreparedStatement ps = con.prepareStatement(sqlActorsByFilmId);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();

			boolean firstIt = true;
			while (rs.next()) {
				// keep actorsList null if no results were returned
				if (firstIt) {
					actorsList = new ArrayList<>();
					firstIt = false;
				}

				int actorId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Actor actor = new Actor(actorId, firstName, lastName);
				actorsList.add(actor);
			}
			rs.close();
			ps.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return actorsList;
	}

	private List<Actor> findActorsByFilmId(int filmId, Connection con) throws SQLException {
		List<Actor> actorsList = new ArrayList<>();

		PreparedStatement ps = con.prepareStatement(sqlActorsByFilmId);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int actorId = rs.getInt(1);
			String firstName = rs.getString(2);
			String lastName = rs.getString(3);
			Actor actor = new Actor(actorId, firstName, lastName);
			actorsList.add(actor);
		}
		rs.close();
		ps.close();

		return actorsList;
	}

	@Override
	public List<Category> findCategoriesByFilmId(int filmId) {
		List<Category> categoriesList = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(URL, "student", "student")) {

			PreparedStatement ps = con.prepareStatement(sqlCategoriesByFilmId);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int categoryId = rs.getInt(1);
				String categoryName = rs.getString(2);
				Category cat = new Category(categoryId, categoryName);
				categoriesList.add(cat);
			}
			rs.close();
			ps.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return categoriesList;
	}

	private List<Category> findCategoriesByFilmId(int filmId, Connection con) throws SQLException {
		List<Category> categoriesList = new ArrayList<>();

		PreparedStatement ps = con.prepareStatement(sqlCategoriesByFilmId);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int categoryId = rs.getInt(1);
			String categoryName = rs.getString(2);
			Category cat = new Category(categoryId, categoryName);
			categoriesList.add(cat);

		}

		rs.close();
		ps.close();

		return categoriesList;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> searchResults = new ArrayList<>();
		Film film = null;
		String lang = "";
		List<Actor> actorsList = new ArrayList<>();
		List<Category> categories = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(URL, "student", "student")) {

			PreparedStatement ps = con.prepareStatement(sqlFilmsByKeyword);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				film = new Film();

				Integer filmKey = rs.getInt(1);
				film.setId(filmKey);

				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setLanguageId(rs.getInt(4));
				film.setRentalDuration(rs.getInt(5));
				film.setRentalRate(rs.getDouble(6));
				film.setLength(rs.getInt(7));
				film.setReplacementCost(rs.getDouble(8));
				film.setRating(rs.getString(9));
				film.setSpecialFeatures(rs.getString(10));
				film.setReleaseYear(rs.getInt(11));

				// Pass Connection object to overloaded methods to avoid making
				// multiple Connections per query
				actorsList = findActorsByFilmId(filmKey, con);
				film.setActorsList(actorsList);
				lang = findLanguageOfFilm(con, film);
				film.setLanguagePlainText(lang);
				categories = findCategoriesByFilmId(filmKey, con);
				film.setCategories(categories);

				searchResults.add(film);
			}
			rs.close();
			ps.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return searchResults;
	}

	@Override
	public String findLanguageOfFilm(Film f) {
		String lang = "";

		try (Connection con = DriverManager.getConnection(URL, "student", "student")) {

			PreparedStatement ps = con.prepareStatement(sqlLanguageOfFilm);
			ps.setInt(1, f.getId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				lang = rs.getString(1);
			}
			rs.close();
			ps.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return lang;
	}

	private String findLanguageOfFilm(Connection con, Film f) throws SQLException {
		String lang = "";

		PreparedStatement ps = con.prepareStatement(sqlLanguageOfFilm);
		ps.setInt(1, f.getId());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			lang = rs.getString(1);
		}
		rs.close();
		ps.close();

		return lang;
	}

	/*
	 * String title = scanner.nextLine(); String description = scanner.nextLine();
	 * String rating = scanner.nextLine();
	 * 
	 * 
	 */
	@Override
	public Film createFilm(Film f) {
		Connection conn = null;
		Film resultFilm = null;

//		public Film cleanUserFilm(String newTitle, String newDescription, String newLanguagePlainText,
//				String newRentalDuration, String newRentalRate, String newLength, String newReplacementCost,
//				String newRating, String newSpecialFeatures, String newReleaseYear) {

		// TODO update query with other fields
		String sqlCreateFilm = "INSERT INTO film " 
				+ "(title, description, rental_duration, "
				+ "rental_rate, length, replacement_cost, "
				+ "rating, special_features, release_year, language_id) " 				
				+ "VALUES "
				+ "(?, ?, ?, "
				+ "?, ?, ?, "
				+ "?, ?, ?, 1)";
		
		// putting a bunch of fields in the query

		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION

			PreparedStatement stmtCreateFilm = conn.prepareStatement(sqlCreateFilm);
			stmtCreateFilm.setString(1, f.getTitle());
			stmtCreateFilm.setString(2, f.getDescription());
			stmtCreateFilm.setInt(3, f.getRentalDuration());
			stmtCreateFilm.setDouble(4, f.getRentalRate());
			stmtCreateFilm.setInt(5, f.getLength());
			stmtCreateFilm.setDouble(6, f.getReplacementCost());
			stmtCreateFilm.setString(7, f.getRating());
			stmtCreateFilm.setString(8, f.getSpecialFeatures());
			stmtCreateFilm.setInt(9, f.getReleaseYear());
			// binding the variables

			// Do the update and see how many rows it returns
			int updateCount = stmtCreateFilm.executeUpdate();

			// we should only have added 1 row
			if (updateCount == 1) {
				conn.commit(); // COMMIT TRANSACTION

			} else {
				resultFilm = null;
			}

			// if insert succeeds: return Film
			return resultFilm;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			// if insert fails: return null
			resultFilm = null;
			return resultFilm;
		}
	}

	public Film deleteFilm(int filmId) {
		Film f = findFilmById(filmId);
		Film returnFilm = deleteFilm(f);
		return returnFilm;
	}

	public Film deleteFilm(Film film) {
		Film returnFilm = film;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION

			PreparedStatement stmtDeleteFilm = conn.prepareStatement(sqlDeleteFilm);
			stmtDeleteFilm.setInt(1, film.getId());
			stmtDeleteFilm.executeUpdate();

			conn.commit(); // COMMIT TRANSACTION
			return returnFilm;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			returnFilm = null;
			return returnFilm;
		}

	}

	// Limited build as proof of concept:
	public Film updateFilm(int filmId, String title) {
		Film toPass = findFilmById(filmId);
		toPass.setTitle(title);
		Film returnFilm = updateFilm(toPass);
		return returnFilm;
	}

	// Full build:
	public Film updateFilm(int filmId, String newTitle, String newDescription, String newLanguagePlainText,
			Integer newRentalDuration, double newRentalRate, Integer newLength, double newReplacementCost,
			String newRating, String newSpecialFeatures, Integer newReleaseYear) {
		Film toPass = findFilmById(filmId);
		toPass.setTitle(newTitle);
		toPass.setDescription(newDescription);
		toPass.setLanguagePlainText(newLanguagePlainText);
		toPass.setRentalDuration(newRentalDuration);
		toPass.setRentalRate(newRentalRate);
		toPass.setLength(newLength);
		toPass.setReplacementCost(newReplacementCost);
		toPass.setRating(newRating);
		toPass.setSpecialFeatures(newSpecialFeatures);
		toPass.setReleaseYear(newReleaseYear);
		toPass.setLanguageId(1); // HARDCODING LANGUAGE ID AS 1

		Film returnFilm = updateFilm(toPass);
		return returnFilm;
	}

	// Full build:

	public Film cleanUserFilm(String newTitle, String newDescription, String newLanguagePlainText,
			String newRentalDuration, String newRentalRate, String newLength, String newReplacementCost,
			String newRating, String newSpecialFeatures, String newReleaseYear) {

		Film toPass = new Film();

		// CHECK USER INPUT FOR NULL OR INVALID
		// Title
		if (newTitle != "") {
			toPass.setTitle(newTitle);
		} else {
			if (toPass.getTitle() == null) {
				toPass.setTitle("");
			}
		}

		// Description
		if (newDescription != "") {
			toPass.setDescription(newDescription);
		} else {
			if (toPass.getDescription() == null) {
				toPass.setDescription("");
			}
		}

		// LanguagePlainText
		if (newLanguagePlainText != "") {
			toPass.setLanguagePlainText(newLanguagePlainText);
		} else {
			if (toPass.getLanguagePlainText() == null) {
				toPass.setLanguagePlainText("");
			}
		}

		// RentalDuration
		// TODO: deal with user passing in non-numeric info
		if (newRentalDuration != "") {
			toPass.setRentalDuration(Integer.parseInt(newRentalDuration));
		} else {
			if (toPass.getRentalDuration() == null) {
				toPass.setRentalDuration(0);
			}
		}

		// RentalRate
		// TODO: deal with user passing in non-numeric info
		if (newRentalRate != "") {
			toPass.setRentalRate(Double.parseDouble(newRentalRate));
		} else {
			// primitive double cannot be null so do not need this logic
//			if (toPass.getRentalRate() == null) {
//				toPass.setRentalDuration(0);
		}

		// Length
		// TODO: deal with user passing in non-numeric info
		if (newLength != "") {
			toPass.setLength(Integer.parseInt(newLength));
		} else {
			if (toPass.getLength() == null) {
				toPass.setLength(0);
			}
		}

		// ReplacementCost
		// TODO: deal with user passing in non-numeric info
		if (newReplacementCost != "") {
			toPass.setReplacementCost(Double.parseDouble(newReplacementCost));
		} else {
			// primitive double cannot be null so do not need this logic
//					if (toPass.getRentalRate() == null) {
//						toPass.setRentalDuration(0);
		}

		// Rating
		if (newRating != "") {
			toPass.setRating(newRating);
		} else {
			if (toPass.getRating() == null) {
				toPass.setRating("G");
			}
		}

		// SpecialFeatures
		if (newSpecialFeatures != "") {
			toPass.setSpecialFeatures(newSpecialFeatures);
		} else {
			if (toPass.getSpecialFeatures() == null) {
				toPass.setSpecialFeatures("");
			}
		}

		// ReleaseYear
		// TODO: deal with user passing in non-numeric info
		if (newReleaseYear != "") {
			toPass.setReleaseYear(Integer.parseInt(newReleaseYear));
		} else {
			if (toPass.getReleaseYear() == null) {
				toPass.setReleaseYear(0);
			}
		}

		toPass.setLanguageId(1); // HARDCODING LANGUAGE ID AS 1

		
		return toPass;
	}

	public Film cleanUserFilm(int filmId, String newTitle, String newDescription, String newLanguagePlainText,
			String newRentalDuration, String newRentalRate, String newLength, String newReplacementCost,
			String newRating, String newSpecialFeatures, String newReleaseYear) {

		Film toPass = findFilmById(filmId);

		// CHECK USER INPUT FOR NULL OR INVALID
		// Title
		if (newTitle != "") {
			toPass.setTitle(newTitle);
		} else {
			if (toPass.getTitle() == null) {
				toPass.setTitle("");
			}
		}

		// Description
		if (newDescription != "") {
			toPass.setDescription(newDescription);
		} else {
			if (toPass.getDescription() == null) {
				toPass.setDescription("");
			}
		}

		// LanguagePlainText
		if (newLanguagePlainText != "") {
			toPass.setLanguagePlainText(newLanguagePlainText);
		} else {
			if (toPass.getLanguagePlainText() == null) {
				toPass.setLanguagePlainText("");
			}
		}

		// RentalDuration
		// TODO: deal with user passing in non-numeric info
		if (newRentalDuration != "") {
			toPass.setRentalDuration(Integer.parseInt(newRentalDuration));
		} else {
			if (toPass.getRentalDuration() == null) {
				toPass.setRentalDuration(0);
			}
		}

		// RentalRate
		// TODO: deal with user passing in non-numeric info
		if (newRentalRate != "") {
			toPass.setRentalRate(Double.parseDouble(newRentalRate));
		} else {
			// primitive double cannot be null so do not need this logic
//			if (toPass.getRentalRate() == null) {
//				toPass.setRentalDuration(0);
		}

		// Length
		// TODO: deal with user passing in non-numeric info
		if (newLength != "") {
			toPass.setLength(Integer.parseInt(newLength));
		} else {
			if (toPass.getLength() == null) {
				toPass.setLength(0);
			}
		}

		// ReplacementCost
		// TODO: deal with user passing in non-numeric info
		if (newReplacementCost != "") {
			toPass.setReplacementCost(Double.parseDouble(newReplacementCost));
		} else {
			// primitive double cannot be null so do not need this logic
//					if (toPass.getRentalRate() == null) {
//						toPass.setRentalDuration(0);
		}

		// Rating
		if (newRating != "") {
			toPass.setRating(newRating);
		} else {
			if (toPass.getRating() == null) {
				toPass.setRating("G");
			}
		}

		// SpecialFeatures
		if (newSpecialFeatures != "") {
			toPass.setSpecialFeatures(newSpecialFeatures);
		} else {
			if (toPass.getSpecialFeatures() == null) {
				toPass.setSpecialFeatures("");
			}
		}

		// ReleaseYear
		// TODO: deal with user passing in non-numeric info
		if (newReleaseYear != "") {
			toPass.setReleaseYear(Integer.parseInt(newReleaseYear));
		} else {
			if (toPass.getReleaseYear() == null) {
				toPass.setReleaseYear(0);
			}
		}

		toPass.setLanguageId(1); // HARDCODING LANGUAGE ID AS 1

		Film sanitizedFilm = updateFilm(toPass);
		return sanitizedFilm;
	}
//	public Film(int id, String title, String description, Integer languageId, Integer rentalDuration, double rentalRate,
//			Integer length, double replacementCost, String rating, String specialFeatures, Integer releaseYear) {

	@Override
	public Film updateFilm(int filmId, String newTitle, String newDescription, String newLanguagePlainText,
			String newRentalDuration, String newRentalRate, String newLength, String newReplacementCost,
			String newRating, String newSpecialFeatures, String newReleaseYear) {
		Film sanitizedFilm = cleanUserFilm(filmId, newTitle, newDescription, newLanguagePlainText, newRentalDuration,
				newRentalRate, newLength, newReplacementCost, newRating, newSpecialFeatures, newReleaseYear);
		Film returnFilm = updateFilm(sanitizedFilm);
		return returnFilm;
	}

	@Override
	public Film createFilm(String newTitle, String newDescription, String newLanguagePlainText,
			String newRentalDuration, String newRentalRate, String newLength, String newReplacementCost,
			String newRating, String newSpecialFeatures, String newReleaseYear) {
		Film sanitizedFilm = cleanUserFilm(newTitle, newDescription, newLanguagePlainText, newRentalDuration,
				newRentalRate, newLength, newReplacementCost, newRating, newSpecialFeatures, newReleaseYear);
		Film returnFilm = createFilm(sanitizedFilm);
		return returnFilm;
	}

	@Override
	public Film updateFilm(Film f) {
		String sqlUpdateFilm = "UPDATE film " + "SET title=?, " // index 1
				+ "description=?, " // 2
				+ "length=?, " // 3
				+ "rating=?, " // 4
				+ "release_year=?, " // 5
				+ "rental_duration=?, " // 6
				+ "rental_rate=?, " // 7
				+ "replacement_cost=?, " // 8
				+ "special_features=?, " // 9
				+ "language_id=1 " // HARDCODING LANGUAGE ID AS 1
				+ "WHERE id=?"; // 10

		Film resultFilm = null;
		Connection conn = null;
		int filmId = f.getId();

		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION

			PreparedStatement ps = conn.prepareStatement(sqlUpdateFilm);
			ps.setString(1, f.getTitle());
			ps.setString(2, f.getDescription());

			ps.setInt(3, f.getLength());
			ps.setString(4, f.getRating());
			ps.setInt(5, f.getReleaseYear());
			ps.setInt(6, f.getRentalDuration());
			ps.setDouble(7, f.getRentalRate());
			ps.setDouble(8, f.getReplacementCost());
			ps.setString(9, f.getSpecialFeatures());
			ps.setInt(10, filmId); // this is for the WHERE clause, do not mess it up

			ps.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION

			resultFilm = f;
			return resultFilm;

		} catch (

		SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			// if insert fails: return null
			resultFilm = null;
			return resultFilm;
		}

	}
}
