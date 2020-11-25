package com.cdac.dao;

import java.util.List;

import com.cdac.dto.Train;

public interface ITrainDao {
	void addTrain(Train train);

	void deleteTrain(int trainId);

	Train selectTrain(int trainId);

	void upateTrain(Train train);

	List<Train> selectAll();

	List<Train> selectTrain(String source, String destination, String dateOfTravel);

	boolean updateSeats(int trainId, int seats, String seatType);

}
