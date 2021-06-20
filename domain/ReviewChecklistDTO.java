package domain;

public class ReviewChecklistDTO {
	private int reviewNum;
	private String reviewContent;
	
	public ReviewChecklistDTO() {}
	
	public ReviewChecklistDTO(int num, String content)
	{
		this.reviewNum=num;
		this.reviewContent=content;
	}
	
	public int getReviewNum()
	{
		return this.reviewNum;
	}
	
	public void setReviewNum(int num)
	{
		this.reviewNum=num;
	}
	
	public String getReviewContent()
	{
		return this.reviewContent;
	}
	
	public void setReviewContent(String content)
	{
		this.reviewContent=content;
	}
}
