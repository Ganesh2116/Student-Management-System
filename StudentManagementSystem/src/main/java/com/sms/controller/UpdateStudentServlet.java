package com.sms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sms.bo.Student;
import com.sms.dao.StudentDAO;

/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		Student s = StudentDAO.getStudentById(id);
		
		PrintWriter out = response.getWriter();
        out.println("<html><head><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<h2>Update Student</h2>");
        out.println("<form action='updateStudent' method='post'>");
        out.println("<input type='hidden' name='id' value='" + s.getId() + "'>");
        out.println("Name: <input type='text' name='name' value='" + s.getName() + "' required><br>");
        out.println("Age: <input type='number' name='age' value='" + s.getAge() + "' required><br>");
        out.println("Class: <input type='text' name='className' value='" + s.getClassName() + "' required><br>");
        out.println("Address: <input type='text' name='address' value='" + s.getAddress() + "' required><br>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");
        out.println("</body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student s = new Student();
        s.setId(Integer.parseInt(request.getParameter("id")));
        s.setName(request.getParameter("name"));
        s.setAge(Integer.parseInt(request.getParameter("age")));
        s.setClassName(request.getParameter("className"));
        s.setAddress(request.getParameter("address"));

        int status = StudentDAO.updateStudent(s);

        if (status > 0) 
        {
            response.sendRedirect("view");
        } 
        else 
        {
            response.getWriter().println("<h3>Error updating student</h3>");
        }
    }

}
