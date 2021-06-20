package service;

import java.util.ArrayList;

import domain.ReviewChecklistDTO;
import persistence.ReviewChecklistRepository;

public class ReviewChecklistService {
	private final static ReviewChecklistRepository reviewRepository= ReviewChecklistRepository.getInstance();
	
	//검토체크리스트 수정
	public static int updateReview(ReviewChecklistDTO dto)
	{
		reviewRepository.updateReview(dto);
		
		return 1;
	}
	
	//검토체크리스트 조회
	public static ArrayList<ReviewChecklistDTO> readReview()
	{
		ArrayList<ReviewChecklistDTO> reviewList=reviewRepository.readReview();
		
		return reviewList;
	}
}
