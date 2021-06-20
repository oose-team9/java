package service;

import java.util.ArrayList;

import domain.ContractbookDTO;
import persistence.ContractbookRepository;

public class ContractbookService {
	private final static ContractbookRepository bookRepository= ContractbookRepository.getInstance();
	
	//계약대장등록
	public static int createBook(ContractbookDTO book)
	{
		//계약대장등록
		bookRepository.createContractbook(book);
		
		return 1;
	}
	
	//계약대장조회
	public static ContractbookDTO readBook(int bookNum)
	{
		//계약대장조회
		return bookRepository.readContractbook(bookNum);
	}
}
