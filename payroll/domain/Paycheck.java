package com.edu.study.payroll.domain;

import java.util.Date;
import java.util.Map;

public class Paycheck {
	private Date payPeriodStart;
	private Date payPeriodEnd;
	private double grossPay;//应付
	private double netPay;//实发
	private double deductions;//扣除
	private Map<String, String> itsFields;
	public Paycheck(Date payPeriodStart, Date payPeriodEnd){
		this.payPeriodStart = payPeriodStart;
		this.payPeriodEnd = payPeriodEnd;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
		
	}
	public void setDeductions(double deductions) {
		this.deductions  = deductions;		
	}
	public void setNetPay(double netPay){
		this.netPay = netPay;
	}
	public Date getPayPeriodEndDate() {
		
		return this.payPeriodEnd;
	}
	public Date getPayPeriodStartDate() {
		
		return this.payPeriodStart;
	}
}
