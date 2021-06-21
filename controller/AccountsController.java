package controller;

import domain.AccountDeadline;
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
            modelAndView.setViewName("accounts/accounts-list");
            modelAndView.getModel().put("accounts", accounts);
        }
        else if(url.equals("/accounts/create")) {
            if(request.getMethod().equals("GET"))
                modelAndView.setViewName("accounts/accounts-form");
            else {
                Accounts accounts = new Accounts();
                accounts.setEmpNo(Integer.parseInt(request.getParameter("emp")));
                accounts.setBankName(request.getParameter("bank"));
                accounts.setAccNum(request.getParameter("acc"));
                accountsService.create(accounts);
                System.out.println(accounts.toString());
                ArrayList<Accounts> read = accountsService.read();
                modelAndView.setViewName("accounts/accounts-list");
                modelAndView.getModel().put("accounts", read);
            }
        }
        else if(url.equals("/accounts/delete")) {
            String[] ids = request.getParameterValues("chk");
            for(int i = 0; i < ids.length; i++)
                System.out.println(ids[i]);
            int delcnt = accountsService.delete(ids);
            System.out.println(delcnt);
            ArrayList<Accounts> read = accountsService.read();
            modelAndView.setViewName("accounts/accounts-list");
            modelAndView.getModel().put("accounts", read);

        }
        else
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return modelAndView;
    }
}
