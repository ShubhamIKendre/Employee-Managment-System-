<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Employee Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Employee
     Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">employees</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Employee</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Employee</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Password</th>
                                <th>City</th>
                                <th>Company Name</th>
                                <th>Contact</th>
                                <th>Operations</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="employee" items="${listEmployee}">

                                <tr>
                                    <td>
                                        <c:out value="${employee.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.firstname}" />
                                    </td>
                                     <td>
                                        <c:out value="${employee.lastname}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.password}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.city}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.company_name}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.contact}" />
                                    </td>
                                    <td><a href="edit?id=<c:out value='${employee.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${employee.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>