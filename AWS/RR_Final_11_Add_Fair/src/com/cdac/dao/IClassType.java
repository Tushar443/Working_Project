package com.cdac.dao;

public interface IClassType {
	double getPriceofClass(String className, int trainId);

	boolean updateFair(String className, int trainId, double price);

	void addFair(String className, int trainId, double price);

	boolean findFair(String className, int trainId);

}
