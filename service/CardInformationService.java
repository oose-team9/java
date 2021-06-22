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
		System.out.println("findBoardsΩ√¿€");
		System.out.println(cardInformationRepository);
		ArrayList<CardInformation> arr = null;
		arr = cardInformationRepository.findAll();
        return cardInformationRepository.findAll();
    }
}