package com.cdac.cntr;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.dto.Train;
import com.cdac.dto.User;
import com.cdac.service.IClassTypeService;
import com.cdac.service.ITrainService;
import com.cdac.service.IUserService;

@Controller
public class BookingController {

	@Autowired
	private ITrainService trainService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IClassTypeService classTypeService;

	@RequestMapping(value = "/book_train_seat.htm", method = RequestMethod.GET)
	public String bookTrainSeat(ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("user");

		map.put("user", user);
		return "select_train_details";
	}

	@RequestMapping(value = "/book_train_form.htm", method = RequestMethod.GET)
	public String bookTrainForm(@RequestParam int trainId, ModelMap map, HttpSession session) {
		Train train = trainService.findTrain(trainId);
		session.setAttribute("bookTrainId", trainId);
		map.put("train", train);
		return "book_form";
	}

	@RequestMapping(value = "/book_train.htm", method = RequestMethod.POST)
	public String bookTrain(User user, ModelMap map, HttpSession session) {
		System.out.println("Book Train Controller Called");
		User sessionUser = (User) session.getAttribute("user");
		sessionUser.setSeatType(user.getSeatType());
		sessionUser.setNoOfPassengers(user.getNoOfPassengers());
		double price = classTypeService.findPrice(user.getSeatType(), (int) session.getAttribute("bookTrainId"));
		double totalPrice = price * user.getNoOfPassengers();
		sessionUser.setTotalPrice(totalPrice);
		System.out.println(sessionUser + "   contr");
		session.setAttribute("user", sessionUser);
		System.out.println("Controller End");
		return "payment";
	}

	@RequestMapping(value = "/cancel_seat.htm", method = RequestMethod.GET)
	public String cancelBooking(ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		User dbUser = userService.searchUser(user.getUserId());
		Train train = trainService.findTrain(dbUser.getTrainId());
		System.out.println(train);
		System.out.println(dbUser);
		session.setAttribute("user", dbUser);
		session.setAttribute("train", train);
		return "cancel_booking";
	}

	@RequestMapping(value = "/cancel_train.htm", method = RequestMethod.GET)
	public String cancelTrain(ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Train train = (Train) session.getAttribute("train");

		userService.modifyUser(null, 0, 0.0, 0, user.getUserId());
		int seats = 0;
		String search = user.getSeatType();
		switch (search) {
		case "gen":
			seats = train.getGen();
			break;
		case "ac1":
			seats = train.getAc1();
			break;
		case "ac2":
			seats = train.getAc2();
			break;

		case "fc":
			seats = train.getFc();
			break;
		case "slp":
			seats = train.getSlp();
			break;
		}
		seats = subtractSeat(seats, user.getNoOfPassengers());
		trainService.modifySeats(train.getTrainId(), seats, user.getSeatType());
		return "home";
	}

	private int subtractSeat(int seats, int passengers) {
		return seats + passengers;
	}

	@RequestMapping(value = "/booking_status.htm", method = RequestMethod.GET)
	public String bookingStatus(HttpSession session, ModelMap map) {
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		User dbUser = userService.searchUser(user.getUserId());
		System.out.println(dbUser);
		Train train = trainService.findTrain(dbUser.getTrainId());

		map.put("user", dbUser);
		map.put("train", train);
		return "booking_status";
	}

}
