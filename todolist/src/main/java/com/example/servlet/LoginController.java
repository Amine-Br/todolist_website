package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserDAO;
import com.example.dao.UserDAOimp;
import com.example.models.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userdao;   
   
	
	
	
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	userdao=new UserDAOimp();
		super.init();
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//for logout
		/*
    	HttpSession session=request.getSession();
		session.invalidate();*/
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User utilisateur = new User();
        utilisateur.setEmail(request.getParameter("email"));
        utilisateur.setPassword(request.getParameter("pass"));
        boolean result=userdao.login(utilisateur);
        if(result) {
        	HttpSession session=request.getSession();
        	session.setAttribute("email", request.getParameter("email"));
        	session.setAttribute("password", request.getParameter("pass"));
        	request.setAttribute("login_result", "success");
        }else {
        	request.setAttribute("login_result", "error");
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/tasks.jsp").forward(request, response);

	}

}
