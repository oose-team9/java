package controller;

import domain.Accounts;
import service.AccountsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AccountsController", urlPatterns = "/accounts/*")
public class AccountsController implements Controller{
    private final AccountsService accountsService = new AccountsService();
    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        if(url.equals("/accounts/read")) {
            ArrayList<Accounts> accounts = accountsService.read();
            modelAndView.setViewName("accounts-list");
        }
        else if(url.equals("/accounts/create")) {
            Accounts account = new Accounts();
            accountsService.create(account);
            modelAndView.setViewName("accounts-form");
        }
        else if(url.equals("/accounts/delete")) {
            String id = request.getParameter("id");
            accountsService.delete(id);
        }
        else
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return modelAndView;
    }
}
