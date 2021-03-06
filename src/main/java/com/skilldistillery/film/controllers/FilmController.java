package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(path="findById.do", method = RequestMethod.GET)
	public ModelAndView findById(Integer filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("singleFilm");
		mv.addObject("film", filmDAO.findFilmById(filmId));		
		return mv;
	}
		
	
}

///// ERIC LAND BELOW; ERIC NOT TO EDIT ABOVE LINE 37





































///// ERIK LAND AFTER LINE 75
