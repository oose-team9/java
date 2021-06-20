package domain;

import java.sql.Date;

public class AccessRight {
	private int id;
	private int employeeNo;
	private int cardNumber;
	private Date authorizationDate;
	
	public AccessRight() {}
	public AccessRight(int id, int employeeNo, int cardNumber, Date authorizationDate) {
		this.id = id;
		this.employeeNo = employeeNo;
		this.cardNumber = cardNumber;
		this.authorizationDate = authorizationDate;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public int getEmployeeNo() { return employeeNo; }
	public void setEmployeeNo(int employeeNo) { this.employeeNo = employeeNo; }
	public int getCardNumber() { return cardNumber; }
	public void setCardNumber(int cardNumber) { this.cardNumber = cardNumber; }
	public Date getAuthorizationDate() { return authorizationDate; }
	public void setAuthorizationDate(Date authorizationDate) { this.authorizationDate = authorizationDate; }
}