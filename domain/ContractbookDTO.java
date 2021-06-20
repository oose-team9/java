package domain;

public class ContractbookDTO {
	private int contractbookNum;
	private int contractNum;
	private String contractName;
	private String manager;
	private String contractDate;
	private int price;
	private String period;
	private String contractingParties;
	
	public ContractbookDTO() {}
	
	public ContractbookDTO(int bookNum, int contractNum, String contractName, String manager, String contractDate, int price, String period, String parties)
	{
		this.contractbookNum=bookNum;
		this.contractNum=contractNum;
		this.contractName=contractName;
		this.manager=manager;
		this.contractDate=contractDate;
		this.price=price;
		this.period=period;
		this.contractingParties=parties;
		
	}
	
	public int getContractbookNum()
	{
		return this.contractbookNum;
	}
	
	public void setContractbookNum(int num)
	{
		this.contractbookNum=num;
	}
	
	public int getContractNum()
	{
		return this.contractNum;
	}
	
	public void setContractNum(int contractNum)
	{
		this.contractNum=contractNum;
	}
	
	public String getContractName()
	{
		return this.contractName;
	}
	
	public void setContractName(String contractName)
	{
		this.contractName=contractName;
	}
	
	public String getManager()
	{
		return this.manager;
	}
	
	public void setManager(String name)
	{
		this.manager=name;
	}
	
	public String getContractDate()
	{
		return this.contractDate;
	}
	
	public void setContractDate(String date)
	{
		this.contractDate=date;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	public void setPrice(int price)
	{
		this.price=price;
	}
	
	public String getPeriod()
	{
		return this.period;
	}
	
	public void setPeriod(String period)
	{
		this.period=period;
	}
	
	public String getContractingParties()
	{
		return this.contractingParties;
	}
	
	public void setContractingParties(String parties)
	{
		this.contractingParties=parties;
	}
}
