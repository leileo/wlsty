package com.edu.study.payroll.afflication;

import java.util.Date;
import java.util.Map;

import com.edu.study.payroll.domain.Affiliation;
import com.edu.study.payroll.domain.Paycheck;
import com.edu.study.payroll.util.DateUtil;

public class UnionAffiliation implements Affiliation {

	

	private String memberID;
	private double weeklyDue;
	private Map<Date, ServiceCharge> serviceCharges;
	
	public void addServiceCharge(ServiceCharge sc){
		serviceCharges.put(sc.getDate(), sc);
	}
	
	@Override
	public double calculateDeductions(Paycheck pc) {
		double totalPay = 0;
		for (ServiceCharge sc : serviceCharges.values()) {
			if (DateUtil.between(sc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				totalPay+=sc.getAmount();	//会费
			}
		}
		return totalPay+calculatePayForWeeklyDue(pc);
	}
	/**
	 * 服务费
	 * 每周应付款，计算出有多少个周五*单周服务费
	 * @param pc
	 * @return
	 */
	private double calculatePayForWeeklyDue(Paycheck pc) {
		int interval=(int) DateUtil.getDaysBetween( pc.getPayPeriodStartDate(),pc.getPayPeriodEndDate());
		return interval/7*weeklyDue;
	}
}
