package com.ysyt.wrapper;

import java.sql.Timestamp;

public class TransactionWrapper {

	
	private Timestamp joiningDate;
	private Timestamp vacateDate;
	


	public Timestamp getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Timestamp getVacateDate() {
		return vacateDate;
	}
	public void setVacateDate(Timestamp vacateDate) {
		this.vacateDate = vacateDate;
	}

}
