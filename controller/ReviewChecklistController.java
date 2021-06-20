package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ContractbookDTO;
import domain.ReviewChecklistDTO;
import service.ContractbookService;
import service.ReviewChecklistService;

public class ReviewChecklistController implements Controller {
	
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView();	
		//GET,POST
		//����üũ����Ʈ ����
		if(url.equals("/reviewChecklist/update")) {
			if(request.getMethod().equals("GET")) {
				ArrayList<ReviewChecklistDTO> reviews = ReviewChecklistService.readReview();
				modelAndView.setViewName("updateReviewChecklists");
				modelAndView.getModel().put("reviews", reviews);
			}
			else if(request.getMethod().equals("POST")) {
				ReviewChecklistDTO  review= new ReviewChecklistDTO();
                review.setReviewNum(Integer.parseInt(request.getParameter("reviewNum")));
                review.setReviewContent(request.getParameter("reviewContents"));
                int result=ReviewChecklistService.updateReview(review);
				//����
				if(result == 1) {
					modelAndView.setViewName("reviewChecklist/update");
				}
				else {
					modelAndView.setViewName("reviewChecklist/update");
				}
			}
		}//����üũ����Ʈ ��ȸ
		else if(url.equals("/reviewChecklist/read"))
		{
			ArrayList<ReviewChecklistDTO> reviews = ReviewChecklistService.readReview();
			modelAndView.setViewName("readReviewChecklist");
			modelAndView.getModel().put("review", reviews);
		}
		else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return modelAndView;
	}
}
