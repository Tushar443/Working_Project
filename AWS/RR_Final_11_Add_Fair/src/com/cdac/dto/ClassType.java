package com.cdac.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "class_type")
public class ClassType {
	@Id
	@GeneratedValue
	@Column(name = "class_id")
	private int classId;
	@Column(name = "train_id")
	private int trainId;
	@Column(name = "class_name")
	private String className;
	@Column(name = "price")
	private double price;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CalssType [classId=" + classId + ", trainId=" + trainId + ", className=" + className + ", price="
				+ price + "]";
	}

	public ClassType() {

	}

}
