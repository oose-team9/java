package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View {
private String viewPath;
	
	public View(String viewPath) {
		this.viewPath = viewPath;
	}
	
	public void doGet(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		model.forEach((key, value) -> request.setAttribute(key, value));
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
	
	public void doPost(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		model.forEach((key, value) -> request.setAttribute(key, value));
		response.sendRedirect(viewPath);
	}
}
