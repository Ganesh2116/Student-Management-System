package com.sms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sms.bo.Student;

public class StudentDAO 
{
	private static String url = "jdbc:mysql://localhost:3306/student_management";
	private static String user = "root";
	private static String password = "7248984472";
	
	private static Connection getConnection()
	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =  DriverManager.getConnection(url, user, password);
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static int insertStudent(Student s)
	{
		int status = 0;
		String sql = "insert into students(name,age,class,address) values(?,?,?,?)";
		Connection con = StudentDAO.getConnection();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setString(3, s.getClassName());
			ps.setString(4,s.getAddress());
			
			status = ps.executeUpdate();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static List<Student> getAllStudent()
	{
		List<Student> list = new ArrayList();
		String sql = "select * from students";
		Connection con = StudentDAO.getConnection();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setAge(rs.getInt(3));
				s.setClassName(rs.getString(4));
				s.setAddress(rs.getString(5));
				
				list.add(s);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return list;	
	}
	
	public static Student getStudentById(int id)
	{
		String sql = "select * from students where id=?";
		Connection con = getConnection();
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));
                s.setClassName(rs.getString(4));
                s.setAddress(rs.getString(5));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	
	public static int updateStudent(Student s) {
        String sql = "update students set name=?, age=?, class=?, address=? where id=?";
        Connection con = getConnection();
        
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getClassName());
            ps.setString(4, s.getAddress());
            ps.setInt(5, s.getId());
            return ps.executeUpdate();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static int deleteStudent(int id)
    {
        String sql = "delete from students where id=?";
        Connection con = getConnection();
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Student> searchStudents(String query) {
        List<Student> list = new ArrayList<>();
        String sql = "select * from students where name like ? OR class like ?";
        Connection con = getConnection();
        
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            String keyword = "%" + query + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));
                s.setClassName(rs.getString(4));
                s.setAddress(rs.getString(5));
                list.add(s);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
