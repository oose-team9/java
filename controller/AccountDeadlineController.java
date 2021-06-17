package controller;

import domain.AccountDeadline;
import service.AccountDeadlineService;
import service.AccountsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AccountDeadlineController", urlPatterns = "/account-deadline/*")
public class AccountDeadlineController implements Controller{
    private final AccountDeadlineService accountDeadlineService = new AccountDeadlineService();
    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        if(url.equals("/account-deadline/read")) {
            ArrayList<AccountDeadline> deadlines = accountDeadlineService.read();
            modelAndView.setViewName("account-deadline/account-deadline-list");
        }
        else if(url.equals("/account-deadline/create")) {
            AccountDeadline deadline = new AccountDeadline();
            accountDeadlineService.create(deadline);
            modelAndView.setViewName("account-deadline/account-deadline-form");
        }
        else
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return modelAndView;
    }
}
