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
		//검토체크리스트 수정
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
				//성공
				if(result == 1) {
					modelAndView.setViewName("reviewChecklist/update");
				}
				else {
					modelAndView.setViewName("reviewChecklist/update");
				}
			}
		}//검토체크리스트 조회
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
