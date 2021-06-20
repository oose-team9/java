package service;

import java.util.ArrayList;

import domain.ReviewChecklistDTO;
import persistence.ReviewChecklistRepository;

public class ReviewChecklistService {
	private final static ReviewChecklistRepository reviewRepository= ReviewChecklistRepository.getInstance();
	
	//����üũ����Ʈ ����
	public static int updateReview(ReviewChecklistDTO dto)
	{
		reviewRepository.updateReview(dto);
		
		return 1;
	}
	
	//����üũ����Ʈ ��ȸ
	public static ArrayList<ReviewChecklistDTO> readReview()
	{
		ArrayList<ReviewChecklistDTO> reviewList=reviewRepository.readReview();
		
		return reviewList;
	}
}
