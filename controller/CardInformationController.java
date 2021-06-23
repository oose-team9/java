package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.CardInformation;
import service.CardInformationService;

public class CardInformationController implements Controller{

	private final CardInformationService cardInformationService = new CardInformationService();
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {		
		ModelAndView modelAndView = new ModelAndView();
		if(url.equals("/cardInformation/list")) {
			ArrayList<CardInformation> informations = cardInformationService.findBoards();
			modelAndView.setViewName("cardInformation/cardInformation-list");
			modelAndView.getModel().put("informations", informations);
		}
		else if (url.equals("/cardInformation/write")) {
			if(request.getMethod().equals("GET")) {
				modelAndView.setViewName("cardInformation/cardInformation-write");
			}
			else if(request.getMethod().equals("POST")) {
	            try {
					CardInformation cardInformation = new CardInformation();
					int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
					String bank = request.getParameter("bank");
					int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
					String password = request.getParameter("password");
					int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
					
					if (bank == "" || password == "") {
						throw new Exception();
					}
					
					cardInformation.setCardNumber(cardNumber);
					cardInformation.setBank(bank);
					cardInformation.setAccountNumber(accountNumber);
					cardInformation.setPassword(password);
					cardInformation.setEmployeeNo(employeeNo);
					cardInformationService.write(cardInformation);
					ArrayList<CardInformation> informations = cardInformationService.findBoards();
					modelAndView.setViewName("cardInformation/cardInformation-list");
					modelAndView.getModel().put("informations", informations);
	            } catch (Exception e) {
	               PrintWriter out = response.getWriter();
	               out.println(
	                     "<script>alert('입력 값이 올바르지 않습니다.'); location.href='/Accountancy/front/cardInformation/write';</script>");
	               out.close();
	               e.printStackTrace();
	            }
			}
		}
		else if (url.equals("/cardInformation/delete")) {
			cardInformationService.delete(Integer.parseInt(request.getParameter("cardNumber")));
			ArrayList<CardInformation> informations = cardInformationService.findBoards();
			modelAndView.setViewName("cardInformation/cardInformation-list");
			modelAndView.getModel().put("informations", informations);
		}
		else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return modelAndView;
	}
}
