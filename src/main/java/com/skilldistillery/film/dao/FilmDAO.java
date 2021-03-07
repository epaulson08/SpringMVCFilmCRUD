package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);

	public List<Film> findFilmsByKeyword(String keyword);

	public List<Category> findCategoriesByFilmId(int filmId);

	public String findLanguageOfFilm(Film f);

	public Film createFilm(Film f);

	public Film deleteFilm(Film f);
	
	public Film deleteFilm(int filmId);

	public Film updateFilm(Film f);

	public Film updateFilm(int filmId, String title);
	
	public Film updateFilm(int filmId, String newTitle, String newDescription, String newLanguagePlainText, Integer newRentalDuration, double newRentalRate, 
			Integer newLength, double newReplacementCost, String newRating, String newSpecialFeatures, Integer newReleaseYear);

	public Film cleanUserFilm(int filmId, String newTitle, String newDescription, String newLanguagePlainText, String newRentalDuration, String newRentalRate, 
			String newLength, String newReplacementCost, String newRating, String newSpecialFeatures, String newReleaseYear);
	
	public Film updateFilm(int filmId, String newTitle, String newDescription, String newLanguagePlainText,
			String newRentalDuration, String newRentalRate, String newLength, String newReplacementCost,
			String newRating, String newSpecialFeatures, String newReleaseYear);
	

	public Film createFilm(String newTitle, String newDescription, String newLanguagePlainText,
			String newRentalDuration, String newRentalRate, String newLength, String newReplacementCost,
			String newRating, String newSpecialFeatures, String newReleaseYear);

}
