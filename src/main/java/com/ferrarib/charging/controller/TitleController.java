package com.ferrarib.charging.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ferrarib.charging.model.Title;
import com.ferrarib.charging.model.TitleStatus;
import com.ferrarib.charging.repository.Titles;

@Controller
@RequestMapping("/titles")
public class TitleController {
	
	@Autowired
	private Titles titles;

	@RequestMapping("/new")
	public ModelAndView newRegister() {
		ModelAndView mv = new ModelAndView("TitleRegister");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(Title title) {
		System.out.println(">>>>" + title.getDescription());
		titles.save(title);
		ModelAndView mv = new ModelAndView("TitleRegister");
		mv.addObject("message", title.getDescription() + " has been stored with success!");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView search() {
		List<Title> allTitles = titles.findAll();
		
		ModelAndView mv = new ModelAndView("SearchTitles");
		mv.addObject("titles", allTitles);
		return mv;
	}
	
	@ModelAttribute("allTitleStatuses")
	public List<TitleStatus> allTitleStatus() {
		return Arrays.asList(TitleStatus.values());
	}

}
