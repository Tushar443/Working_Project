package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.ITrainDao;
import com.cdac.dto.Train;

@Service
public class TrainServiceImple implements ITrainService {
	@Autowired
	private ITrainDao trainDao;

	@Override
	public void addTrain(Train train) {
		trainDao.addTrain(train);
	}

	@Override
	public List<Train> selectAll() {
		return trainDao.selectAll();
	}

	@Override
	public void removeTrain(int trainId) {
		trainDao.deleteTrain(trainId);

	}

	@Override
	public void modifyTrain(Train train) {
		trainDao.upateTrain(train);
	}

	@Override
	public Train findTrain(int trainId) {
		return trainDao.selectTrain(trainId);

	}

	@Override
	public List<Train> selectTrainUser(String source, String destination, String dateOfTravel) {
		return trainDao.selectTrain(source, destination, dateOfTravel);
	}

	@Override
	public boolean modifySeats(int trainId, int seats, String seatType) {

		return trainDao.updateSeats(trainId, seats, seatType);
	}

}
