package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(path = "findByKeyword.do", method = RequestMethod.GET)
	public ModelAndView findByKeyword(String filmKeyword) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("films");
		mv.addObject("films", filmDAO.findFilmsByKeyword(filmKeyword));
		return mv;
	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.GET)
	public ModelAndView enterUpdates(@RequestParam int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateFilm");
		mv.addObject("film", filmDAO.findFilmById(filmId));
		return mv;
	}
	
	@RequestMapping(path = "updateConfirmed.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(@RequestParam int filmId, @RequestParam String title) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateConfirmed");
		mv.addObject("film", filmDAO.updateFilm(filmId, title));
		return mv;
	}
	
	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addFilm");
		mv.addObject("film", filmDAO.createFilm(film));
		return mv;
	}
		
	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("filmId") int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deleteFilm");
		mv.addObject("film", filmDAO.deleteFilm(filmId));
		return mv;
	}
	
	
	
}
