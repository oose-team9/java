package domain;

public class ExaminationChecklistDTO
{
	private int examinationNum;
	private String examinationContent;
	
	public ExaminationChecklistDTO() {}
	
	public ExaminationChecklistDTO(int num, String content)
	{
		this.examinationNum=num;
		this.examinationContent=content;
	}
	
	public int getExaminationNum()
	{
		return this.examinationNum;
	}
	
	public void setExaminationNum(int num)
	{
		this.examinationNum=num;
	}
	
	public String getExaminationContent()
	{
		return this.examinationContent;
	}
	
	public void setExaminationContent(String content)
	{
		this.examinationContent=content;
	}
}
