package com.cdac.dao;

import java.util.List;

import com.cdac.dto.User;

public interface IUserDao {
	boolean insertUser(User user);

	boolean checkUser(User user);

	boolean checkAdmin(User user);

	boolean updateUser(String seatType, int noOfPassengers, double totalPrice, int trainId, int userId);

	User findUser(int userId);

	List<User> passengerList(int trainId);
}
