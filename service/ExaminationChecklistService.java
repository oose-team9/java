package service;

import domain.ExaminationChecklistDTO;
import persistence.ExaminationChecklistRepository;

public class ExaminationChecklistService {
	private final static ExaminationChecklistRepository examinationRepository= ExaminationChecklistRepository.getInstance();
	
	//�˼�üũ����Ʈ ����
	public static int updateExamination(ExaminationChecklistDTO dto)
	{
		examinationRepository.updateExamination(dto);
		
		return 1;
	}
}
