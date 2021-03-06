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

	public boolean deleteFilm(Film f);

	public Film updateFilm(Film f, String newTitle);

}
