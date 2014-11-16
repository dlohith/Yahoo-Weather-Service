<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<body>

	<form action="getforecast">
		<table>
			<tr>
				<td>Select a city</td>
				<td><select name="zipcode">
						<option value="73301">Austin</option>
						<option value="75014">Irving</option>
						<option value="75023">Plano</option>
						<option value="75029">Lewisville</option>
						<option value="75083">Richardson</option>
						<option value="75210">Dallas</option>
						<option value="75551">Atlanta</option>
						<option value="78202">San Antonio</option>
						<option value="76001">Arlington</option>
						<option value="77701">Beaumont</option>
						<option value="75601">Longview</option>
				</select></td>
				<td><input type="submit" value="Get Forecast"></td>
			</tr>
		</table>
	</form>

	<c:choose>
		<c:when test="${not empty forecastList}">
			<div
				style="float: left; width: 45%; border-radius: 5px; border: 2px solid #e3daa8; padding: 20px;">
				<h4 align="center"></h4>

				<c:forEach var="forecast" items="${forecastList}">
					<c:out value="${forecast.text}"></c:out>,
					<c:out value="${forecast.day}"></c:out>,
					<c:out value="${forecast.date}"></c:out>,
					<c:out value="${forecast.high}"></c:out>,				
					<c:out value="${forecast.low}"></c:out>
					<br>
					<hr>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>



</body>
</html>
