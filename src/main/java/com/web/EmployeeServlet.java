package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.EmployeeDao;
import com.model.Employee;


@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	
//	private static final long serialVersionUID = 1 ;
    private EmployeeDao employeeDao;

    public void init() {
        employeeDao = new EmployeeDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/list":
                	listEmployee(request, response);
                    break;
                case "/insert":
                    insertEmployee(request, response);
                    break;
                case "/delete":
                    deleteEmployee(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateEmployee(request, response);
                    break;
                case "/new":
                	showNewForm(request, response);
                	break;
                case "/login":
                	loginform(request,response);
                	break;
                default:
                	break;
                	
                	
                	
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Employee > listEmployee = employeeDao.selectAllEmployees();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("List_Employees.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Employee_Form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = employeeDao.selectEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Employee_Form.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);

    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
//    	int id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String city = request.getParameter("city");
        String companyname = request.getParameter("companyname");
        String contact = request.getParameter("contact");
        Employee newEmployee = new Employee(firstname,lastname,email,password,city,companyname, contact);
        employeeDao.insertEmployee(newEmployee);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String city = request.getParameter("city");
        String companyname = request.getParameter("companyname");
        String contact = request.getParameter("contact");
        
        Employee register = new Employee(id, firstname,lastname,email,password,city,companyname, contact);
        employeeDao.updateEmployee(register);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDao.deleteEmployee(id);
        response.sendRedirect("list");

    }
    
    private void loginform(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword(password);

        try {
            
        	if(employeeDao.validate(employee))
        	{
        		listEmployee(request, response);
            } 
        	else {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}