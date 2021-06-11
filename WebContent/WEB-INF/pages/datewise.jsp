<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">

      <style>
         table, th, td {
            border: 1px solid black;
         }
      </style>
     
</head>
<body>
<h4>Student Id : ${sid }<br>
<br>
Student Name : ${sname }<br></h4>
<table>
         <tr>
            <th>Date of class</th>
            <th>Attendance</th>
         </tr>
         <c:forEach items="${list}" var="l" varStatus="loop">
       			<tr>
           			<td> ${l.d}</td>
           			<td> ${l.present }</td>
     			</tr>
      	 </c:forEach>
   
</table>
	
</body>
</html>