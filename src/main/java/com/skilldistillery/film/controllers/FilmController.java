package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.film.dao.FilmDAO;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;
	public FilmDAO getFilmDAO() { 	
		return filmDAO;
	}
	public void setStateDao(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	
	@RequestMapping(path={"/", "home.do"})
	public String home() {
		return "index";
	}
}
