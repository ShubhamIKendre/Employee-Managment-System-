<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>
        <head>
            <title>Employee Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        </head>

        <body class="p-3 mb-2 bg-dark " >

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Employee Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employees</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5 ">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${employee != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${employee == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${employee != null}">
                                    Edit employee
                                </c:if>
                                <c:if test="${employee == null}">
                                    Add New employee
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${employee != null}">
                            <input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>First Name</label> <input type="text" value="<c:out value='${employee.firstname}' />" class="form-control" name="fname" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Last Name</label> <input type="text" value="<c:out value='${employee.lastname}' />" class="form-control" name="lname" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Employee Email</label> <input type="email" value="<c:out value='${employee.email}' />" class="form-control" name="email">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Password</label> <input type="password" value="<c:out value='${employee.password}' />" class="form-control" name="password" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Employee City</label> <input type="text" value="<c:out value='${employee.city}' />" class="form-control" name="city">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Company Name</label> <input type="text" value="<c:out value='${employee.company_name}' />" class="form-control" name="companyname" required="required">
                        </fieldset>

                        
                        <fieldset class="form-group">
                            <label>Contact</label> <input type="number" value="<c:out value='${employee.contact}' />" maxlength="10" class="form-control" name="contact" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>
