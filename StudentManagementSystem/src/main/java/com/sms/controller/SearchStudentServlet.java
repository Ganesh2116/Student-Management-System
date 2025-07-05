package com.sms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sms.bo.Student;
import com.sms.dao.StudentDAO;

/**
 * Servlet implementation class SearchStudentServlet
 */
@WebServlet("/searchStudent")
public class SearchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String query = request.getParameter("query");
        List<Student> list =  StudentDAO.searchStudents(query);
        
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<h2>Search Results</h2>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Class</th><th>Address</th><th>Actions</th></tr>");

        for (Student s : list) 
        {
            out.println("<tr>");
            out.println("<td>" + s.getId() + "</td>");
            out.println("<td>" + s.getName() + "</td>");
            out.println("<td>" + s.getAge() + "</td>");
            out.println("<td>" + s.getClassName() + "</td>");
            out.println("<td>" + s.getAddress() + "</td>");
            out.println("<td><a href='updateStudent?id=" + s.getId() + "'>Edit</a> | "
                    + "<a href='deleteStudent?id=" + s.getId() + "' onclick='return confirm(\"Are you sure?\")'>Delete</a></td>");
            out.println("</tr>");
        }
        
        out.println("</table>");
        out.println("<br><a href='dashboard.html'>Back to Dashboard</a>");
        out.println("</body></html>");
	}

}
