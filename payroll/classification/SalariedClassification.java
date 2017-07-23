package com.edu.study.payroll.classification;

import com.edu.study.payroll.domain.Paycheck;
import com.edu.study.payroll.domain.PaymentClassification;

public class SalariedClassification implements PaymentClassification {
	private double salary;
	public SalariedClassification(double salary){
		this.salary = salary;
	}
	@Override
	public double calculatePay(Paycheck pc) {		
		return salary;
	}

}
