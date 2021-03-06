package com.skilldistilery.film.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.film.dao.FilmDAOJDBCImpl;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

class DatabaseAccessTests {
	private FilmDAOJDBCImpl db;

	@BeforeEach
	void setUp() throws Exception {
		db = new FilmDAOJDBCImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}

//
	@Test
	void test_findFilmById_with_invalid_id_returns_null() {
		Film f = db.findFilmById(-42);
		assertNull(f);
	}

	@Test
	void test_findFilmById_with_id_409_returns_HEARTBREAKERS_BRIGHT() {
		// Film test constructor: id, title, languageId, rentalDuration, releaseYear
		Film expected = new Film(409, "HEARTBREAKERS BRIGHT", 1, 3, 2000);
		Film actual = db.findFilmById(409);
		assertEquals(expected, actual);
	}

//
	@Test
	void test_findActorById_with_invalid_id_returns_null() {
		Actor a = db.findActorById(-84);
		assertNull(a);
	}

	@Test
	void test_findActorById_with_id_26_returns_Rip_Crawford() {
		Actor expected = new Actor(26, "Rip", "Crawford");
		Actor actual = db.findActorById(26);
		assertEquals(expected, actual);
	}

//
	@Test
	void test_findActorsByFilmId_with_invalid_film_id_returns_null() {
		List<Actor> la = db.findActorsByFilmId(-100);
		assertNull(la);
	}

	@Test
	void test_findActorsByFilmId_with_film_id_XXX_returns_XXX() {
		fail();
	} // TODO fill in method name; implement
		// implementation challenging because actors lists typically 20-30 elements
//

//	@Test
//	void test_findActorsByFilmId_overloaded_version_with_invalid_film_id_returns_null() {
//		fail();
//	} // TODO implement  // TODO would this cause NullPointerException upstream?

	@Test
	void test_findActorsByFilmId_overloaded_version_with_film_id_XXX_returns_XXX() {
		fail();
	} // TODO fill in method name; implement
		// implementation challenging because actors lists typically 20-30 elements
//

	@Test
	void test_findFilmById_with_invalid_film_id_returns_null() {
		Film f = db.findFilmById(25492);
		assertNull(f);
	}

	@Test
	void test_findFilmById_with_film_id_962_returns_WASTELAND_DIVINE() {
		// Film test constructor: id, title, languageId, rentalDuration, releaseYear
		Film expected = new Film(962, "WASTELAND DIVINE", 1, 7, 1987);
		Film actual = db.findFilmById(962);
		assertEquals(expected, actual);
	}

//	@Test
//	void test_findFilmById_overloaded_version__with_invalid_film_id_returns_null() {
//		fail();
//	} // TODO implement  // TODO would this cause NullPointerException upstream?

	@Test
	void test_findFilmById_overloaded_version_with_film_id_570_returns_MERMAID_INSECTS() {
		// instantiate Connection to pass to method
		String URL = "jdbc:mysql://localhost:3306/sdvid";
		try (Connection con = DriverManager.getConnection(URL, "student", "student")) {
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		Film expected = new Film(570, "MERMAID INSECTS", 1, 5, 2000);

		// TODO: this method is private and cannot be accessed

		fail();
	} // TODO implement
//

	@Test
	void test_findFilmsByKeyword_with_no_match_returns_null() {
		fail();
	} // TODO implement

	@Test
	void test_findFilmsByKeyword_with_keyword_west_lowercase_returns_XXX() {
		fail();
	} // TODO fill in method name; implement

	@Test
	void test_findFilmsByKeyword_with_keyword_WEST_uppercase_returns_XXX() {
		fail();
	} // TODO fill in method name; implement

	@Test
	void test_findFilmsByKeyword_with_keyword_wEsT_mixedcase_returns_XXX() {
		fail();
	} // TODO fill in method name; implement
//

	@Test
	void test_findLanaguageOfFilm_with_invalid_film_returns_null() {
		fail();
	} // TODO: implement

	@Test
	void test_findLanguageOfFilm_with_film_XXX_returns_XXX() {
		fail();
	} // TODO fill in method name; implement
//

//	@Test
//	void test_findLanguageOfFilm_overloaded_version_with_null_film_returns_null() {
//		fail();
//	} // TODO: implement  // TODO: will this cause NullPointerException upstream?

	void test_findLanguageOfFilm_overloaded_version_with_film_XXX_returns_XXX() {
		fail();
	}
//

	// TODO: tests for exception throws?

}
