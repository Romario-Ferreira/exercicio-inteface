package services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import domain.entities.Contract;
import domain.entities.Installment;

public class ContractService  {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
	
	private OnlinePaymentService payServ;
	
	
	public ContractService(OnlinePaymentService payServ) {
		
		this.payServ=payServ;
	}
	
	public void processContract(Contract contract,Integer months) {
		
		double basicQuota = contract.getTotalValue()/months;
		
		for (int i=1; i<=months ; i++) {
			
			double updatedQuota = basicQuota + payServ.interest(basicQuota, i);
			double finalQuota = updatedQuota + payServ.paymentFee(updatedQuota);
			
			Date dueDate = addMonth(contract.getDate(),i);
			
			contract.getInstallments().add(new Installment(dueDate,finalQuota));
		}
		
		
	}
	
	public Date addMonth(Date date , int N) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.MONTH, N);
		return cal.getTime();
		
	}

}
