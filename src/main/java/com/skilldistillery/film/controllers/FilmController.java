package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping(path="findById.do")
	public ModelAndView findById(Integer filmId) { // put parameters in that will be used by model
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("film", filmDAO.findFilmById(filmId));		
		return mv;
	}
}
