package com.cdac.cntr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdac.dto.Train;
import com.cdac.service.ITrainService;

@Controller
public class UserTrainController {
	@Autowired
	private ITrainService trainService;

	@RequestMapping(value = "/search.htm", method = RequestMethod.GET)
	public String userSearch(ModelMap map) {
		map.put("train", new Train());
		return "search_train_form";
	}

	@RequestMapping(value = "/search_train_form.htm", method = RequestMethod.POST)
	public String searchForm(Train train, ModelMap map) {

		List<Train> dbTrain = trainService.selectTrainUser(train.getDeparts(), train.getReach(),
				train.getDateOfTravel());

		map.put("train", dbTrain);
		return "display_train";
	}
}
