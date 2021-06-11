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
	function setcid(ecid) {
		const cid = document.getElementById("courseid");
		document.set
		cid.value=ecid;
	}
	
</script>
     
</head>
<body>
<h4>Student Id : ${sid }<br>
<br>
Student Name : ${sname }<br>
<a href="view_timetable">View Timetable</a><br></h4>
<form action="getattdate" method="post">
<table>
         <tr>
         	<th>course</th>
            <th>course Id</th>
            <th>Total Classes</th>
            <th>Present</th>
            <th>Absent</th>
            <th></th>
            <th>action</th>
            
         </tr>
         <c:forEach items="${list}" var="l" varStatus="loop">
       			<tr>
           			<td>${l.cname }</td>
           			<td> ${l.cid }</td>
           			<td> ${l.total_classes }</td>
           			<td> ${l.present }</td>
           			<td>${l.total_classes-l.present}</td>
           			<td><input type="hidden" name="courseid" id="courseid" value=${l.cid } /></td>	
           			<td><button type="Submit" class="btn btn-info " onclick="setcid(${l.cid})">View Details</button></td>
     			</tr>
      	 </c:forEach>
   
</table>
	
</form>
</body>
</html>