package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import domain.entities.Contract;
import domain.entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {
	
	public static void main (String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date dueDate = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		Double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number,dueDate,totalValue);
		
		System.out.print("Enter number of installments: ");
		int N = sc.nextInt();
		
		ContractService cs = new ContractService(new PaypalService());
		
		cs.processContract(contract, N);
		
		for(Installment is : contract.getInstallments()) {
			
			System.out.println(is);
		}
		

		sc.close();
		
	}

}
