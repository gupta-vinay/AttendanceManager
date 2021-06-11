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
<script>
	function present(ebuid) {
		const attendance = document.getElementById(ebuid);
		attendance.value=1;
	}
	function absent(ebuid) {
		const attendance = document.getElementById(ebuid);
		attendance.value=0;
	}
</script>
     
</head>

   <body>
      <h1>Attendance</h1>
      <form action="updateattendance" method="post">
      <table>
         <tr>
         	<th>Student RollNo</th>
            <th>Student Name</th>
            <th>Attendance</th>
            <th>Action</th>
         </tr>
         <c:forEach items="${list}" var="l" varStatus="loop">
       			<tr >
           			<td>${l.sid }</td>
           			<td>${l.sname }</td>
           			<td> <input type="text" id="${l.sid }" name="${l.sid }" required=""/></td>
           			<td><button type="button" class="btn btn-info " onClick="present(${l.sid})">Present</button>
           			<button type="button" class="btn btn-info " onclick="absent(${l.sid})">Absent</button></td>
     			</tr>
      	 </c:forEach>
      </table>
      
      <input type="hidden" name="batch" value="${batch}" />
      <button type="Submit" class="btn btn-info " >Submit</button>
	</form>   
   </body>
</html>
