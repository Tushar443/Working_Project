package com.cdac.service;

import org.springframework.stereotype.Service;

@Service
public interface IClassTypeService {
	double findPrice(String className, int trainId);

	boolean updateFair(String className, int trainId, double price);

	public void inserFair(String className, int trainId, double price);

	boolean findFair(String className, int trainId);
}
