package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.IUserDao;
import com.cdac.dto.User;

@Service
public class UserServiceImple implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Override
	public boolean addUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public boolean findUser(User user) {

		return userDao.checkUser(user);

	}

	@Override
	public boolean findAdmin(User user) {
		return userDao.checkAdmin(user);
	}

	@Override
	public boolean modifyUser(String seatType, int noOfPassengers, double totalPrice, int trainId, int userId) {
		return userDao.updateUser(seatType, noOfPassengers, totalPrice, trainId, userId);
	}

	@Override
	public User searchUser(int userId) {
		return userDao.findUser(userId);
	}

	@Override
	public List<User> findPassengers(int trainId) {
		return userDao.passengerList(trainId);
	}

}
