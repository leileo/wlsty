package com.edu.study.payroll.schedule;

import java.util.Date;

import com.edu.study.payroll.domain.PaymentSchedule;
import com.edu.study.payroll.util.DateUtil;


public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date date) {		
		return DateUtil.isFriday(date);
	}
	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {		
		return DateUtil.add(payPeriodEndDate, -6);
	}

}
