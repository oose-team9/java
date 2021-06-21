package controller;

import domain.Extrawork;
import service.ExtraworkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

@WebServlet(name = "ExtraworkController", urlPatterns = "/extrawork/*")
public class ExtraworkController implements Controller {
    private final ExtraworkService extraworkService = new ExtraworkService();

    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        Extrawork extrawork = null;
        Timestamp time = null;
        boolean methodResult = true;

        // 시간외근무 결재 신청
        if (url.equals("/extrawork/write")) {
            if (request.getMethod().equals("GET")) {
                modelAndView.setViewName("extrawork/extrawork-write");
            } else if (request.getMethod().equals("POST")) {
                extrawork = new Extrawork();
                extrawork.setApplicant(Integer.parseInt(request.getParameter("applicant")));
                extrawork.setWorkingDate(Timestamp.valueOf(request.getParameter("workingDate")));
                extrawork.setWorkingTimes(Integer.parseInt(request.getParameter("workingTimes")));

                methodResult = extraworkService.createExtrawork(extrawork);
                if (methodResult) {
                    modelAndView.setViewName("extrawork/success");
                } else {
                    modelAndView.setViewName("extrawork/failure");
                }
            }
        }
        // 시간외근무 전체조회
        else if (url.equals("/extrawork/list")) {
            ArrayList<Extrawork> extraworks = new ExtraworkService().readAllExtraworks();
            modelAndView.setViewName("extrawork/extrawork-list");
            modelAndView.getModel().put("extraworks", extraworks);
        }
        // 시간외근무 상세조회
        else if (url.equals("/extrawork/read")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                extrawork = extraworkService.readExtrawork(id);
                modelAndView.setViewName("extrawork/extrawork-read");
                modelAndView.getModel().put("extrawork", extrawork);
            }catch (Exception e){
                modelAndView.setViewName("extrawork/failure");
            }
        }
        // 시간외근무 삭제
        else if (url.equals("/extrawork/delete")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                extraworkService.deleteExtrawork(id);

                ArrayList<Extrawork> extraworks = new ExtraworkService().readAllExtraworks();
                modelAndView.setViewName("extrawork/extrawork-list");
                modelAndView.getModel().put("extraworks", extraworks);
            }catch(Exception e){
                modelAndView.setViewName("extrawork/failure");
            }
        }
        // 시간외근무 수정 신청
        else if (url.equals("/extrawork/update")) {
            if (request.getMethod().equals("GET")) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    extrawork = extraworkService.readExtrawork(id);
                    modelAndView.setViewName("extrawork/extrawork-update");
                    modelAndView.getModel().put("extrawork", extrawork);
                }catch (Exception e){
                    modelAndView.setViewName("extrawork/failure");
                }
            } else if (request.getMethod().equals("POST")) {
                extrawork = new Extrawork();
                extrawork.setId(Integer.parseInt(request.getParameter("id")));
                extrawork.setApplicant(Integer.parseInt(request.getParameter("applicant")));
                extrawork.setWorkingDate(Timestamp.valueOf(request.getParameter("workingDate")));
                extrawork.setWorkingTimes(Integer.parseInt(request.getParameter("workingTimes")));

                if(extraworkService.updateExtrawork(extrawork)) {
                    modelAndView.setViewName("extrawork/success");
                }else{
                    modelAndView.setViewName("extrawork/failure");
                }
            }
        } else {
            // 이외의 요청은 404 NOT FOUND
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return modelAndView;
    }
}
