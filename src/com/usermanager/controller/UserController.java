package com.usermanager.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usermanager.dao.UserDAO;
import com.usermanager.helper.Helper;
import com.usermanager.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao = new UserDAO();
	private Helper helper = new Helper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = null;
		
		if (request.getParameter("delete") != null) {
			try {
				userDao.deleteUser(Integer.parseInt(request.getParameter("delete")));
				request.setAttribute("message", "User Deleted!");
				request.setAttribute("allUsersList", userDao.getUsers());
				page = "/users.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("search") != null) {
			try {
				request.setAttribute("allUsersList", userDao.searchUser(request.getParameter("search")));
				
				page = "/users.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("filter") != null) {
			try {
				List<User> allUsersList = new ArrayList<>(userDao.getUsers());
			
			
				if (request.getParameter("filter").equals("family")) {
					request.setAttribute("allUsersList", helper.sortByFamily(allUsersList));	
					request.setAttribute("radio", "1");
				}
				if (request.getParameter("filter").equals("dob")) {
					request.setAttribute("allUsersList", helper.sortByDob(allUsersList));				
					request.setAttribute("radio", "2");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			page = "/users.jsp";				
		} else if (request.getParameter("page") != null) {
			page = "/" + request.getParameter("page") + ".jsp";
			if (page.equals("/users.jsp")) {
				try {
					request.setAttribute("allUsersList", userDao.getUsers());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("edit") != null) {
				try {
					request.setAttribute("user", userDao.getUserById(Integer.parseInt(request.getParameter("edit"))));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} else page = "/error.jsp";
		
		
		getServletContext()
			.getRequestDispatcher(page)
			.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User newUser = new User();
		newUser.setFirstName(request.getParameter("firstname"));
		newUser.setLastName(request.getParameter("lastname"));
		newUser.setDateOfBirth(request.getParameter("dateofbirth"));
		newUser.setPhoneNumber(request.getParameter("phonenumber"));
		newUser.setEmail(request.getParameter("email"));
		
		
		try {
			if (request.getParameter("userid").equals("")) {
				userDao.createUser(newUser);
				request.setAttribute("message", "User Created!");
			}
			if(request.getParameter("userid") != null ){
				newUser.setUserId(Integer.parseInt(request.getParameter("userid")));
				userDao.editUser(newUser);
				request.setAttribute("message", "User Edited!");
			}
			request.setAttribute("allUsersList", userDao.getUsers());
			getServletContext()
			.getRequestDispatcher("/users.jsp")
			.forward(request, response);
			
		} catch (SQLException e) {
				e.printStackTrace();
			}	
	}

	
}
