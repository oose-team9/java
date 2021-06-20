package controller;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domain.ContractbookDTO;
import domain.ReviewChecklistDTO;
import service.ContractbookService;
import service.ReviewChecklistService;

public class ContractbookController implements Controller {

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		//GET,POST
		//계약대장 등록
		if(url.equals("/contractbook/create")) {
			if(request.getMethod().equals("GET")) {
				modelAndView.setViewName("createContractbook");
			}
			else if(request.getMethod().equals("POST")) {
				ContractbookDTO book = new ContractbookDTO();
				book.setContractbookNum(Integer.parseInt(request.getParameter("contractbookNum")));
				book.setContractNum(Integer.parseInt(request.getParameter("contractNum")));
				book.setContractName(request.getParameter("contractName"));
				book.setManager(request.getParameter("manager"));
				book.setContractDate(request.getParameter("date"));
				book.setPrice(Integer.parseInt(request.getParameter("price")));
				book.setPeriod(request.getParameter("periodStart")+"~"+request.getParameter("periodEnd"));
				book.setContractingParties(request.getParameter("parties"));
				
				int result=ContractbookService.createBook(book);
				
				//성공
				if(result == 1) {
					modelAndView.setViewName("contractbook/create");
				}
				else {	//실패
					modelAndView.setViewName("createContractbook");
				}
			}
		}//계약대장 조회
		else if(url.equals("/contractbook/read")) 
		{
			ContractbookDTO contractbook;
			if(request.getParameter("contractbookNum")==null)
			{
				contractbook = ContractbookService.readBook(0);
			}
			else
			{
				contractbook = ContractbookService.readBook(Integer.parseInt(request.getParameter("contractbookNum")));

			}
			modelAndView.setViewName("readContractbook");
			modelAndView.getModel().put("contractbook", contractbook);
			
		}
		else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return modelAndView;
	}
}
