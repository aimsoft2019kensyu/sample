package jp.co.aimsoft.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showTop() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("top");

		return modelAndView;
	}
}
