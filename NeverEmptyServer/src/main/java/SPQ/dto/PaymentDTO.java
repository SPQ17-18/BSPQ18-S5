/** @package SPQ.dto
 *  This package prepare some object to transfer to client
 */
package SPQ.dto;

import java.io.Serializable;

/** 
 * @class PaymentDTO
 * @brief This class prepares a payment to be transferred.
 */
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
	
	/** Contructor of paymentDTO.class
	 * 
	 * @param total
	 * @param cardnumber
	 * @param cardholder
	 * @param expDate
	 * @param cvv
	 */
	public PaymentDTO(double total, long cardnumber, String cardholder, String expDate, int cvv) {
		super();
		this.total = total;
		this.cardnumber = cardnumber;
		this.cardholder = cardholder;
		this.expDate = expDate;
		this.cvv = cvv;
	}
	
	/** Contructor of paymentDTO.class
	 * 
	 * @param total
	 * @param username
	 * @param password
	 */
	public PaymentDTO(double total, String username, String password) {
		super();
		this.total = total;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Returns the total attribute of the class.
	 * @return total instance.
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * Sets total attribute of the class.
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	
	/**
	 * Returns the cardnumber attribute of the class.
	 * @return cardnumber instance.
	 */
	public long getCardnumber() {
		return cardnumber;
	}
	
	/**
	 * Sets cardNumber attribute of the class.
	 */
	public void setCardnumber(long cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	/**
	 * Returns the cardholder attribute of the class.
	 * @return cardholder instance.
	 */
	public String getCardholder() {
		return cardholder;
	}
	
	/**
	 * Sets cardHolder attribute of the class.
	 */
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	
	/**
	 * Returns the expDaate attribute of the class.
	 * @return ExpDate instance.
	 */
	public String getExpDate() {
		return expDate;
	}
	
	/**
	 * Sets expDate attribute of the class.
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	/**
	 * Returns the cvv attribute of the class.
	 * @return cvv instance.
	 */
	public int getCvv() {
		return cvv;
	}
	
	/**
	 * Sets cvv attribute of the class.
	 */
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	/**
	 * Returns the UserDTO attribute of the class.
	 * @return UserDTO instance.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets username attribute of the class.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Returns the UserDTO attribute of the class.
	 * @return UserDTO instance.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets password attribute of the class.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
