package service;

import java.util.ArrayList;
import domain.CardInformation;
import persistence.CardInformationRepository;

public class CardInformationService {
	private final CardInformationRepository cardInformationRepository = CardInformationRepository.getInstacne();
	public CardInformationService() {}
	public void delete(int id) {		
		cardInformationRepository.delete(id);
	}
	public void write(CardInformation cardInformation) {
		cardInformationRepository.save(cardInformation);
	}
	public ArrayList<CardInformation> findBoards() {
        	return cardInformationRepository.findAll();
    }
}
