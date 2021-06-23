package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.models.User;
import com.example.dao.*;

/**
 * Servlet implementation class AuthController
 */
@WebServlet("/AuthController")
public class SignupController extends HttpServlet {
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
    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		register(request);
		opensession(request);
        response.sendRedirect("tasks");

	}
	public void register(HttpServletRequest request) {
		User utilisateur = new User();
        utilisateur.setName(request.getParameter("name"));
        utilisateur.setEmail(request.getParameter("email"));
        utilisateur.setPassword(request.getParameter("pass"));
        userdao.adduser(utilisateur);
	}
	public void opensession(HttpServletRequest request) {
		String email=request.getParameter("email");
		User user =userdao.selectuserbyemail(email);
    	HttpSession session=request.getSession();
		session.setAttribute("id", user.getId());
		session.setAttribute("name", user.getName());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("password", user.getPassword());
	}
	
}
