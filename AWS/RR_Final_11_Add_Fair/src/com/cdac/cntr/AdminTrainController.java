package com.cdac.cntr;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.dto.ClassType;
import com.cdac.dto.Train;
import com.cdac.dto.User;
import com.cdac.service.IClassTypeService;
import com.cdac.service.ITrainService;
import com.cdac.service.IUserService;

@Controller
public class AdminTrainController {
	@Autowired
	private ITrainService trainService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IClassTypeService classTypeService;

	@RequestMapping(value = "/add_train.htm", method = RequestMethod.GET)
	public String reqAddTrain(ModelMap map) {
		map.put("train", new Train());
		return "train_form";
	}

	@RequestMapping(value = "/train_add_form.htm", method = RequestMethod.POST)
	public String addTrain(Train train, ModelMap map) {
		trainService.addTrain(train);
		return "home_admin";
	}

	@RequestMapping(value = "/searchByAdmin.htm", method = RequestMethod.GET)
	public String searchTrain(Train train, ModelMap map) {
		List<Train> list = trainService.selectAll();
		map.put("tList", list);
		return "allTrain";
	}

	@RequestMapping(value = "/train_delete.htm", method = RequestMethod.GET)
	public String deleteTrain(@RequestParam int trainId, ModelMap map) {
		trainService.removeTrain(trainId);
		List<Train> list = trainService.selectAll();
		map.put("tList", list);
		return "allTrain";
	}

	@RequestMapping(value = "/update_train_form.htm", method = RequestMethod.GET)
	public String update(@RequestParam int trainId, ModelMap map) {
		Train train = trainService.findTrain(trainId);
		map.put("train", train);
		return "update_train_form";
	}

	@RequestMapping(value = "/update_train.htm", method = RequestMethod.POST)
	public String updateForm(Train train, ModelMap map) {
		trainService.modifyTrain(train);
		List<Train> list = trainService.selectAll();
		map.put("tList", list);
		return "allTrain";
	}

	@RequestMapping(value = "/passengers_list.htm", method = RequestMethod.GET)
	public String passengerList(@RequestParam int trainId, ModelMap map) {

		Train train = trainService.findTrain(trainId);
		List<User> list = userService.findPassengers(trainId);
		map.put("passengerList", list);
		map.put("train", train);
		return "passenger_list";
	}

	/**
	 * Fair
	 * 
	 * @param train
	 * @param map
	 * @return
	 */

	@RequestMapping(value = "/update_fair.htm", method = RequestMethod.GET)
	public String updateFair(ModelMap map) {
		List<Train> list = trainService.selectAll();
		map.put("tList", list);
		System.out.println(list);

		return "display_fair";
	}

	@RequestMapping(value = "/train_fair.htm", method = RequestMethod.GET)
	public String updateTrainFair(@RequestParam int trainId, ModelMap map, HttpSession session) {
		Train train = (Train) trainService.findTrain(trainId);
		map.put("train", train);

		session.setAttribute("trainId", trainId);
		map.put("user", new User());
		return "fairTrain";
	}

	@RequestMapping(value = "/update_faire_form.htm", method = RequestMethod.POST)
	public String updateTrainForm(User user, HttpSession session, ModelMap map) {
		System.out.println(user);
		int trainId = (int) session.getAttribute("trainId");
		double price = classTypeService.findPrice(user.getSeatType(), trainId);
		session.setAttribute("seatTypeUser", user);
		ClassType ct = new ClassType();
		ct.setPrice(price);
		map.put("classType", ct);
		return "faire_change";
	}

	@RequestMapping(value = "/update_DB.htm", method = RequestMethod.POST)
	public String updateDB(ClassType ct, ModelMap map, HttpSession session) {
		int trainId = (int) session.getAttribute("trainId");
		User user = (User) session.getAttribute("seatTypeUser");
		boolean b = classTypeService.updateFair(user.getSeatType(), trainId, ct.getPrice());
		System.out.println(ct);
		return "home_admin";
	}

	/**
	 * Add Fair
	 * 
	 * @param map
	 * @return
	 */

	@RequestMapping(value = "/add_fair.htm", method = RequestMethod.GET)
	public String addFair(ModelMap map) {

		List<Train> list = trainService.selectAll();
		map.put("tList", list);

		return "Add_Fair";
	}

	@RequestMapping(value = "/faire_train_form.htm", method = RequestMethod.GET)
	public String addTrainFair(@RequestParam int trainId, ModelMap map, HttpSession session) {
		session.setAttribute("trainId", trainId);
		map.put("user_add_fair", new User());
		return "Add_Fair_Form";
	}

	@RequestMapping(value = "/train_fair_form.htm", method = RequestMethod.POST)
	public String addTrainFairForm(User user_add_fair, ModelMap map, HttpSession session) {
		int trainId = (int) session.getAttribute("trainId");
		boolean valid = classTypeService.findFair(user_add_fair.getSeatType(), trainId);
		System.out.println("line 159 " + valid);
		if (!valid) {
			classTypeService.inserFair(user_add_fair.getSeatType(), trainId, user_add_fair.getTotalPrice());
		} else {
			return "home_admin";
		}

		map.put("user_add_fair", new User());
		return "Add_Fair_Form";
	}
}
