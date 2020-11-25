package com.cdac.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "train")
public class Train {
	@Id
	@Column(name = "train_id")
	@GeneratedValue
	private int trainId;
	@Column(name = "train_no")
	private int trainNo;
	@Column(name = "train_name")
	private String trainName;
	@Column(name = "departs")
	private String departs;
	@Column(name = "reach")
	private String reach;
	@Column(name = "gen")
	private int gen;
	@Column(name = "ac1")
	private int ac1;
	@Column(name = "ac2")
	private int ac2;
	@Column(name = "fc")
	private int fc;
	@Column(name = "slp")
	private int slp;
	@Column(name = "date_of_travel")
	private String dateOfTravel;

	public Train(int trainId) {
		super();
		this.trainId = trainId;
	}

	@Override
	public String toString() {
		return trainId + " " + trainNo + " " + trainName + " " + departs + " " + reach + " " + gen + " " + ac1 + " "
				+ ac2 + " " + fc + " " + slp + " " + dateOfTravel;
	}

	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDeparts() {
		return departs;
	}

	public void setDeparts(String departs) {
		this.departs = departs;
	}

	public String getReach() {
		return reach;
	}

	public void setReach(String reach) {
		this.reach = reach;
	}

	public int getGen() {
		return gen;
	}

	public void setGen(int gen) {
		this.gen = gen;
	}

	public int getAc1() {
		return ac1;
	}

	public void setAc1(int ac1) {
		this.ac1 = ac1;
	}

	public int getAc2() {
		return ac2;
	}

	public void setAc2(int ac2) {
		this.ac2 = ac2;
	}

	public int getFc() {
		return fc;
	}

	public void setFc(int fc) {
		this.fc = fc;
	}

	public int getSlp() {
		return slp;
	}

	public void setSlp(int slp) {
		this.slp = slp;
	}

	public String getDateOfTravel() {
		return dateOfTravel;
	}

	public void setDateOfTravel(String dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}

	public Train(int trainId, int trainNo, String trainName, String departs, String reach, int gen, int ac1, int ac2,
			int fc, int slp, String dateOfTravel) {
		super();
		this.trainId = trainId;
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.departs = departs;
		this.reach = reach;
		this.gen = gen;
		this.ac1 = ac1;
		this.ac2 = ac2;
		this.fc = fc;
		this.slp = slp;
		this.dateOfTravel = dateOfTravel;
	}

}
