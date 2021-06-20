package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.AccessRight;
import service.AccessRightService;

public class AccessRightController implements Controller{

	private final AccessRightService accessRightService = new AccessRightService();
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {		
		ModelAndView modelAndView = new ModelAndView();
		if(url.equals("/accessRight/list")) {
			ArrayList<AccessRight> accessRights = accessRightService.findBoards();
			modelAndView.setViewName("accessRight/accessRight-list");
			modelAndView.getModel().put("accessRights", accessRights);
		}
		else if (url.equals("/accessRight/write")) {
			if(request.getMethod().equals("GET")) {
				modelAndView.setViewName("accessRight/accessRight-write");
			}
			else if(request.getMethod().equals("POST")) {
				try {
					AccessRight accessRight = new AccessRight();
					int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
					int employeeNo = Integer.parseInt(request.getParameter("employeeNo"));
					accessRight.setCardNumber(cardNumber);
					accessRight.setEmployeeNo(employeeNo);
					accessRightService.write(accessRight);
					ArrayList<AccessRight> accessRights = accessRightService.findBoards();
					modelAndView.setViewName("accessRight/accessRight-list");
					modelAndView.getModel().put("accessRights", accessRights);
	            } catch (Exception e) {
	               PrintWriter out = response.getWriter();
	               out.println(
	                     "<script>alert('입력 값이 올바르지 않습니다.'); location.href='/Accountancy/front/accessRight/write';</script>");
	               out.close();
	               e.printStackTrace();
	            }
			}
		}
		else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return modelAndView;
	}
}
