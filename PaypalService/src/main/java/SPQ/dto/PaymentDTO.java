package SPQ.dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private double total;
	//Visa
	private long cardnumber;
	private String cardholder;
	private String expDate;
	private int cvv;
	//Paypal
	private String username;
	private String password;
	
	
	public PaymentDTO(double total, long cardnumber, String cardholder, String expDate, int cvv) {
		super();
		this.total = total;
		this.cardnumber = cardnumber;
		this.cardholder = cardholder;
		this.expDate = expDate;
		this.cvv = cvv;
	}
	public PaymentDTO(double total, String username, String password) {
		super();
		this.total = total;
		this.username = username;
		this.password = password;
	}
	
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public long getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(long cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
