package com.cdac.cntr;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdac.dto.User;
import com.cdac.service.IUserService;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/prep_reg_form.htm", method = RequestMethod.GET)
	public String preRegForm(ModelMap map) {
		map.put("user", new User());
		return "reg_form";
	}

	@RequestMapping(value = "/reg.htm", method = RequestMethod.POST)
	public String register(User user, ModelMap map) {
		boolean b = userService.addUser(user);
		if (b) {
			return "login_form";
		} else
			return "reg_form";

	}

	@RequestMapping(value = "/prep_log_form.htm", method = RequestMethod.GET)
	public String preLogIn(ModelMap map) {
		map.put("user", new User());
		return "login_form";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String login(User user, ModelMap map, HttpSession session) {

//		System.out.println(user.getUserName().equalsIgnoreCase("admin"));
		if (user.getUserName().equalsIgnoreCase("admin")) {
			boolean a = userService.findAdmin(user);
			if (a) {
				session.setAttribute("user", user);

				return "home_admin";
			}
		}
		boolean b = userService.findUser(user);
		if (b) {
			session.setAttribute("user", user);
			return "home";
		} else {
			map.put("user", new User());
			return "login_form";
		}

	}
}
