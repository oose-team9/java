package controller;

import domain.AccountDeadline;
import service.AccountDeadlineService;
import service.AccountsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "AccountDeadlineController", urlPatterns = "/account-deadline/*")
public class AccountDeadlineController implements Controller{
    private final AccountDeadlineService accountDeadlineService = new AccountDeadlineService();
    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        if(url.equals("/account-deadline/read")) {
            AccountDeadline deadline = accountDeadlineService.read();
            modelAndView.setViewName("account-deadline/account-deadline-list");
            modelAndView.getModel().put("deadline", deadline);
        }
        else if(url.equals("/account-deadline/create")) {
            if(request.getMethod().equals("GET"))
                modelAndView.setViewName("account-deadline/account-deadline-form");
            else {
                AccountDeadline deadline = new AccountDeadline();
                deadline.setStartDay(java.sql.Date.valueOf(request.getParameter("start")));
                deadline.setEndDay(java.sql.Date.valueOf(request.getParameter("end")));
                accountDeadlineService.create(deadline);
                System.out.println(deadline.toString());
                AccountDeadline read = accountDeadlineService.read();
                modelAndView.setViewName("account-deadline/account-deadline-list");
                modelAndView.getModel().put("deadline", read);
            }
        }
        else
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return modelAndView;
    }
}
