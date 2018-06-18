package SPQ.data;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import SPQ.dto.PaymentDTO;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Account{
	private String cardholder;
	private long cardnumber;
	private int cvv;
	private double balance;
	private String expDate;
	
	public Account(String cardholder, long cardnumber, int cvv, double balance, String expDate) {
		super();
		this.cardholder = cardholder;
		this.cardnumber = cardnumber;
		this.cvv = cvv;
		this.balance = balance;
		this.expDate = expDate;
	}
	
	public Account( PaymentDTO paymentDTO) {
		super();
		this.cardholder = paymentDTO.getCardholder();
		this.cardnumber = paymentDTO.getCardnumber();
		this.cvv = paymentDTO.getCvv();
		this.balance = 0;
		this.expDate = paymentDTO.getExpDate();
	}

	public String getCardholder() {
		return cardholder;
	}

	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(int cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	

	
}