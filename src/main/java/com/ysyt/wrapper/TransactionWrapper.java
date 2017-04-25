package com.ysyt.wrapper;

import java.sql.Timestamp;

public class TransactionWrapper {

	
	private Timestamp joinDate;
	private Timestamp vacateDate;
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	public Timestamp getVacateDate() {
		return vacateDate;
	}
	public void setVacateDate(Timestamp vacateDate) {
		this.vacateDate = vacateDate;
	}

}
