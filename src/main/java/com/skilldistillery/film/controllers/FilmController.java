package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

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

	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "index";
	}

	@RequestMapping(path = "findById.do", method = RequestMethod.GET)
	public ModelAndView findById(Integer filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("singleFilm");
		mv.addObject("film", filmDAO.findFilmById(filmId));
		return mv;
	}

	@RequestMapping(path = "findByKeyword.do")
	public ModelAndView findByKeyword(String filmKeyword) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("films");
		mv.addObject("films", filmDAO.findFilmsByKeyword(filmKeyword));
		return mv;
	}

	@RequestMapping(path = "updateFilm.do")
	public ModelAndView updateFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateFilm");
//		define model to update film		
		return mv;
	}
	
	@RequestMapping(path = "addFilm.do")
	public ModelAndView addFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editFilm");
//		define model to add film  		
		return mv;
	}
		
	@RequestMapping(path = "deleteFilm.do")
	public ModelAndView deleteFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deleteFilm");
//		define model to delete film		
		return mv;
	}
	
	
	
}
