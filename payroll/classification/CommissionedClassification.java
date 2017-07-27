package com.edu.study.payroll.classification;

import java.util.Date;
import java.util.Map;

import com.edu.study.payroll.domain.Paycheck;
import com.edu.study.payroll.domain.PaymentClassification;
import com.edu.study.payroll.domain.SalesReceipt;
import com.edu.study.payroll.util.DateUtil;

public class CommissionedClassification implements PaymentClassification {
	double salary;
	double rate;
	public CommissionedClassification(double salary , double rate){
		this.salary = salary;
		this.rate = rate;
	}
	Map<Date, SalesReceipt> receipts;
	@Override
	public double calculatePay(Paycheck pc) {
		double commission = 0.0;
		for(SalesReceipt sr : receipts.values()){
			if(DateUtil.between(sr.getSaleDate(), pc.getPayPeriodStartDate(), 
					pc.getPayPeriodEndDate())){
				commission += sr.getAmount() * rate;
			}
		}
		return salary + commission;
	}

}
