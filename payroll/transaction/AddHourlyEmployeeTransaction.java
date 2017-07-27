package com.edu.study.payroll.transaction;

import com.edu.study.payroll.classification.HourlyClassification;
import com.edu.study.payroll.domain.PaymentClassification;
import com.edu.study.payroll.domain.PaymentSchedule;
import com.edu.study.payroll.schedule.WeeklySchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction{
	private double rate;
	AddHourlyEmployeeTransaction(String name, String address, double hourlyRate) {
		super(name, address);
		this.rate = hourlyRate;
	}
	@Override
	public PaymentClassification getClassification() {		
		return new HourlyClassification(rate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		
		return new WeeklySchedule();
	}	
}

