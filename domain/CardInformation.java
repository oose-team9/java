package domain;

public class CardInformation {
	private int employeeNo;
	private int cardNumber;
	private String bank;
	private int accountNumber;
	private String password;
	
	public CardInformation() {}
	public CardInformation(int employeeNo, int cardNumber, String bank, int accountNumber, String password) {
		this.employeeNo = employeeNo;
		this.cardNumber = cardNumber;
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.password = password;
	}
	
	public int getEmployeeNo() { return employeeNo; }
	public void setEmployeeNo(int employeeNo) { this.employeeNo = employeeNo; }
	public int getCardNumber() { return cardNumber; }
	public void setCardNumber(int cardNumber) { this.cardNumber = cardNumber; }
	public String getBank() { return bank; }
	public void setBank(String bank) { this.bank = bank; }
	public int getAccountNumber() { return accountNumber; }
	public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
