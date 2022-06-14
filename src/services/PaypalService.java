package services;

import java.util.InputMismatchException;

public class PaypalService implements OnlinePaymentService {

	private static final double INTEREST_TAX = 0.01;
	private static final double PAYMENT_FEE = 0.02;

	public PaypalService() {
	}
	
	@Override
	public double paymentFee(Double amount) {
		return amount*PAYMENT_FEE;
	}

	@Override
	public double interest(Double amount, Integer months) {

		if (months == 0) {
			throw new InputMismatchException("Insira uma quantidade de meses valida");
		}

		return amount * INTEREST_TAX * months;
	}

}
