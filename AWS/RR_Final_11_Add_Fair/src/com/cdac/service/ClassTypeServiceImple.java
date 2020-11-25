package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.IClassType;

@Service
public class ClassTypeServiceImple implements IClassTypeService {
	@Autowired
	private IClassType classType;

	@Override
	public double findPrice(String className, int trainId) {
		return classType.getPriceofClass(className, trainId);
	}

	@Override
	public boolean updateFair(String className, int trainId, double price) {
		return classType.updateFair(className, trainId, price);
	}

	@Override
	public void inserFair(String className, int trainId, double price) {
		classType.addFair(className, trainId, price);
	}

	@Override
	public boolean findFair(String className, int trainId) {
		return classType.findFair(className, trainId);
	}

}
