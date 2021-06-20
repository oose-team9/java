package service;

import domain.ExaminationChecklistDTO;
import persistence.ExaminationChecklistRepository;

public class ExaminationChecklistService {
	private final static ExaminationChecklistRepository examinationRepository= ExaminationChecklistRepository.getInstance();
	
	//검수체크리스트 수정
	public static int updateExamination(ExaminationChecklistDTO dto)
	{
		examinationRepository.updateExamination(dto);
		
		return 1;
	}
}
