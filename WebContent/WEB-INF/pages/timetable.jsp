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
      <h1>Time Table</h1>
      ${msg }

      <table>
         <tr>
         	<th>Day</th>
            <th>4 p.m.- 5 p.m.</th>
            <th>5 p.m. - 6 p.m.</th>
            <th>6 p.m. - 7 p.m.</th>
            <th>7 p.m. - 8 p.m.</th>
         </tr>
		         <c:forEach items="${list}" var="l" varStatus="loop">
       			<tr>
           			<td>${l.day }</td>
           			<c:choose>
           			<c:when test="${l.day == day}">
           				<c:choose>
							<c:when test="${time==4 or time==5 }">
							<form action="courses">
							<input type="hidden" name="coursename" value=${l.four } />
							<td><button type="Submit" class="btn btn-info " >${l.four }</button></td>
							</form>
							</c:when>
							<c:otherwise><td>${l.four }</td></c:otherwise>           			
           				</c:choose>
           				<c:choose>
							<c:when test="${time==5 or time==6 }">
							<form action="courses">
							<input type="hidden" name="coursename" value=${l.five } />
							<td><button type="Submit" class="btn btn-info " >${l.five }</button></td>
							</form>
							</c:when>
							<c:otherwise><td>${l.five }</td></c:otherwise>           			
           				</c:choose>
           				<c:choose>
							<c:when test="${time==6 or time==7 }">
							<form action="courses">
							<input type="hidden" name="coursename" value=${l.six } />
							<td><button type="Submit" class="btn btn-info " >${l.six }</button></td>
							</form>
							</c:when>
							<c:otherwise><td>${l.six }</td></c:otherwise>           			
           				</c:choose>
           				<c:choose>
							<c:when test="${time==7 or time==8 }">
							<form action="courses">
							<input type="hidden" name="coursename" value=${l.seven } />
							<td><button type="Submit" class="btn btn-info " >${l.seven }</button></td>
							</form>
							</c:when>
							<c:otherwise><td>${l.seven }</td></c:otherwise>           			
           				</c:choose>
           			
           			</c:when>
           			<c:otherwise>
           			<td>${l.four }</td>
           			<td>${l.five }</td>
           			<td>${l.six }</td>
           			<td>${l.seven }</td>
           			</c:otherwise>
           			</c:choose>
     			</tr>
      	 </c:forEach>
         

      </table>
   </body>
</html>
