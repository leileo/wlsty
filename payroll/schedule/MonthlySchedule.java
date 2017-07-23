package com.edu.study.payroll.schedule;

import java.util.Date;

import com.edu.study.payroll.domain.PaymentSchedule;
import com.edu.study.payroll.util.DateUtil;


public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date date) {		
		return DateUtil.isLastDayOfMonth(date);
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {		
		return DateUtil.getFirstDay(payPeriodEndDate);
	}

}
