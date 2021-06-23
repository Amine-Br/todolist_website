package com.example.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.TaskDAO;
import com.example.dao.TaskDAOimp;
import com.example.dao.UserDAO;
import com.example.dao.UserDAOimp;
import com.example.models.Task;
import com.example.models.User;


/**
 * Servlet implementation class tasksController
 */
@WebServlet("/tasksController")
public class tasksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userdao;   
    private TaskDAO taskdao;   
   
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	userdao=new UserDAOimp();
    	taskdao=new TaskDAOimp();

		super.init();
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public tasksController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		int user_id=Integer.parseInt(session.getAttribute("id").toString());
		request.setAttribute("tasks", taskdao.selectalltasks(user_id));
		this.getServletContext().getRequestDispatcher("/WEB-INF/tasks.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getParameter("action")) {
		case "logout":
			logout(request, response);
			break;
		case "add":
			addtask(request, response);
			break;
		case "delete":
			deletetask(request, response);
			break;
		case "complete":
			completetask(request, response);
			break;
		default:
			break;
		}

		
		}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.invalidate();
        response.sendRedirect("login");
	}
	public void addtask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Task task=new Task();

		HttpSession session=request.getSession();
		int user_id=Integer.parseInt(session.getAttribute("id").toString());
		String todo=request.getParameter("todo");
		String status="in progress";
		try {
			String date=request.getParameter("date");
			java.util.Date dateformatted=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Date datefinal=new Date(dateformatted.getTime());
			task.setTask_date(datefinal);
			task.setUser_id(user_id);
			task.setTodo(todo);
			task.setStatus(status);
			taskdao.addtask(task);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("tasks");
	}
	public void deletetask(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		taskdao.deletetaskbyid(id);
		response.sendRedirect("tasks");
	}
	public void completetask(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		taskdao.updatetaskstatusbyid("finished", id);
		response.sendRedirect("tasks");

		
	}
}
