package com.cdac.cntr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdac.service.IUserService;

@Controller
public class LogoutController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String preRegForm(ModelMap map) {
		return "logout_end";
	}
}
