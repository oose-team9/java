package service;

import java.util.ArrayList;

import domain.ContractbookDTO;
import persistence.ContractbookRepository;

public class ContractbookService {
	private final static ContractbookRepository bookRepository= ContractbookRepository.getInstance();
	
	//��������
	public static int createBook(ContractbookDTO book)
	{
		//��������
		bookRepository.createContractbook(book);
		
		return 1;
	}
	
	//��������ȸ
	public static ContractbookDTO readBook(int bookNum)
	{
		//��������ȸ
		return bookRepository.readContractbook(bookNum);
	}
}
