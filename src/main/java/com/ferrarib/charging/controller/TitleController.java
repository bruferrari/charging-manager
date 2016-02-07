package com.ferrarib.charging.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ferrarib.charging.model.Title;
import com.ferrarib.charging.model.TitleStatus;
import com.ferrarib.charging.repository.filter.TitleFilter;
import com.ferrarib.charging.service.TitleRegisterService;

@Controller
@RequestMapping("/titles")
public class TitleController {
	
	private static final String REGISTER_VIEW = "TitleRegister";
	
	@Autowired
	private TitleRegisterService titleRegisterService;

	@RequestMapping("/new")
	public ModelAndView newRegister() {
		ModelAndView mv = new ModelAndView(REGISTER_VIEW);
		mv.addObject(new Title());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@Validated Title title, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			return REGISTER_VIEW;
		}
		try {
			titleRegisterService.save(title);
			attributes.addFlashAttribute("message", title.getDescription() + " has been stored with success!");
			return "redirect:/titles/new"; 
		} catch (IllegalArgumentException e) {
			errors.rejectValue("expirationDate", null, e.getMessage());
			return REGISTER_VIEW;
		}

	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView search(@ModelAttribute("filter") TitleFilter filter) {
		
		List<Title> result = titleRegisterService.filter(filter);
		
		ModelAndView mv = new ModelAndView("SearchTitles");
		mv.addObject("titles", result);
		return mv;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Title title) {
		ModelAndView mv = new ModelAndView(REGISTER_VIEW);
		mv.addObject("title", title);
		return mv;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String remove(@PathVariable Long id, RedirectAttributes attr) {
		titleRegisterService.remove(id);
		
		attr.addFlashAttribute("message", "Title has been removed with success!");
		return "redirect:/titles";
	}
	
	@RequestMapping(value="/{id}/receive", method=RequestMethod.PUT)
	public @ResponseBody String receive(@PathVariable Long id) {
		System.out.println(">>> id: " + id);
		return titleRegisterService.receive(id);
	}
	
	@ModelAttribute("allTitleStatuses")
	public List<TitleStatus> allTitleStatus() {
		return Arrays.asList(TitleStatus.values());
	}

}













