package com.sms.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sms.bo.Student;
import com.sms.dao.StudentDAO;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String className = request.getParameter("className");
		String address = request.getParameter("address");
		
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		student.setClassName(className);
		student.setAddress(address);
		
		int status = StudentDAO.insertStudent(student);
		if (status > 0)
		{
            response.getWriter().println("<h3 style='color:green;'>Student Added Successfully!</h3>");
        }
		else
		{
            response.getWriter().println("<h3 style='color:red;'>Error Adding Student</h3>");
        }
		
		RequestDispatcher rd = request.getRequestDispatcher("add_student.html");
        rd.include(request, response);
	}

}
