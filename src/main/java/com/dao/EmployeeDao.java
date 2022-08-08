package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Employee;



public class EmployeeDao{
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/emp_details?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee" +
            "  (firstname, lastname, email, password, city, companyname,contact) VALUES " +
            " (?, ?, ?, ?,?,?,?);";
    
    private static final String SELECT_EMPLOYEE_BY_ID = "select id,firstname,lastname,email,password,city,companyname,contact from employee where id =?";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employee";
    private static final String DELETE_EMPLOYEE_SQL = "delete from employee where id = ?;";
    private static final String UPDATE_EMPLOYEE_SQL = "update employee set firstname = ?, lastname = ?, email= ?, password=?,city =?, companyname = ?, contact=? where id = ?;";

    public EmployeeDao() {}
    
    //Connection
    protected Connection getConnection() 
    {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    //Insertion
    public void insertEmployee(Employee employee) throws SQLException 
    {
        System.out.println(INSERT_EMPLOYEE_SQL);
        
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); 
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) 
        {
//        	preparedStatement.setInt(0, employee.getId());
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.setString(5, employee.getCity());
            preparedStatement.setString(6, employee.getCompany_name());
            preparedStatement.setString(7, employee.getContact());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } 
        catch (SQLException e) {
            printSQLException(e);
        }
    }
	
	//Selection
	public Employee selectEmployee(int id) 
	{
        Employee employee = null;
        
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
        
        // Step 2:Create a statement using connection object
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) 
        {
            
        	preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
        
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String city = rs.getString("city");
                String companyname = rs.getString("companyname");
                String contact = rs.getString("contact");
                
                employee = new Employee(id,firstname, lastname, email, password, city, companyname, contact);
            }
        } 
        catch (SQLException e) 
        {
            printSQLException(e);
        }
        return employee;
    }
	
	//Select all employee
	public List < Employee > selectAllEmployees() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Employee > employee = new ArrayList < > ();
        
        // Step 1: Establishing a Connection
        try (
        		Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
            System.out.println(preparedStatement);
            
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String city = rs.getString("city");
                String companyname = rs.getString("companyname");
                String contact = rs.getString("contact");
                employee.add(new Employee(id, firstname,lastname, email,password, city,companyname,contact));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }
	
	
	//Deletion
	public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
	
	
	//Updation
	public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); 
        	
        PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) 
        {
        	statement.setString(1, employee.getFirstname());
            statement.setString(2, employee.getLastname());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPassword());
            statement.setString(5, employee.getCity());
            statement.setString(6, employee.getCompany_name());
            statement.setString(7, employee.getContact());
            statement.setInt(8, employee.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        System.out.println(rowUpdated);
        return rowUpdated;
    }
	
	public boolean validate(Employee employee) throws SQLException 
	{
        boolean status = false;

        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where email = ? and password = ? ");) 
        {
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setString(2, employee.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }

	
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
	
	

}