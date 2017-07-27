package com.edu.study.payroll.transaction;

import com.edu.study.payroll.domain.Employee;
import com.edu.study.payroll.domain.HoldMethod;
import com.edu.study.payroll.domain.PaymentClassification;
import com.edu.study.payroll.domain.PaymentMethod;
import com.edu.study.payroll.domain.PaymentSchedule;

public abstract class AddEmployeeTransaction {
	private String name;
	private String address;
	public AddEmployeeTransaction(String name,String address){
		this.name = name;
		this.address = address;
	}
	public abstract PaymentClassification getClassification();
	public abstract PaymentSchedule getSchedule();
	
	public void execute(){
		PaymentClassification pc = getClassification();
		PaymentSchedule ps = getSchedule();
		PaymentMethod  pm = new HoldMethod();
		Employee  e = new Employee(name, address);
		e.setClassification(pc);
		e.setSchedule(ps);
		e.setPaymentMethod(pm);		
		//保存到数据库, 略
	}
}
