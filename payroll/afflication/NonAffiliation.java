package com.edu.study.payroll.afflication;


import com.edu.study.payroll.domain.Affiliation;
import com.edu.study.payroll.domain.Paycheck;

public class NonAffiliation implements Affiliation{
	@Override
	public double calculateDeductions(Paycheck pc) {
		
		return 0;
	}
}
