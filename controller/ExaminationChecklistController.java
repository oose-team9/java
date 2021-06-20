package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ExaminationChecklistDTO;
import service.ExaminationChecklistService;

public class ExaminationChecklistController implements Controller {

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView();	
		//GET,POST
		//검수체크리스트 수정
		if(url.equals("/examinationChecklist/update")) {
			if(request.getMethod().equals("GET")) {
				modelAndView.setViewName("updateExaminationChecklist");
			}
			else if(request.getMethod().equals("POST")) {
				ExaminationChecklistDTO  exam= new ExaminationChecklistDTO();
                exam.setExaminationNum(Integer.parseInt(request.getParameter("examinationNum")));
                exam.setExaminationContent(request.getParameter("examinationContents"));
                int result=ExaminationChecklistService.updateExamination(exam);
				//성공
				if(result == 1) {
					modelAndView.setViewName("examinationChecklist/update");
				}
				else {
					modelAndView.setViewName("examinationChecklist/update");
				}
			}
		}
		return modelAndView;
	}
}
