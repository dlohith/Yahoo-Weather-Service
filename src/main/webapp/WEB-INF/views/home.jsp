<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/wx-conditions.css" />
</head>
<body>

	<a href="https://www.yahoo.com/?ilc=401" target="_blank"> <img
		src="https://poweredby.yahoo.com/white.png" width="134" height="29" />
	</a>
	<br>
	<br>
	<br>

	<div
		style="align: center width:     45%; border-radius: 5px; border: 2px; padding: 20px;">
		<form action="getforecast">
			<table>
				<tr>
					<td><label><font size="3" color=#FFF>Select a
								city</font></label></td>
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
				
				<div id="wx-forecast-container">
				<br>
				<label > <font size="3" color=#FFF> City : <c:out
							value="${city}"></c:out></font></label>
					<div class="wx-24hour wx-module wx-grid3of6 wx-weather">

						<c:forEach var="forecast" items="${forecastList}">
							<div class="wx-daypart">
								<h3>
									<c:out value="${forecast.day}"></c:out>
									, <span class="wx-label"><c:out value="${forecast.date}"></c:out></span>
								</h3>
								<div class="wx-conditions">
									<img
										src="http://s.imwx.com/v.20131006.215409/img/wxicon/100/<c:out value="${forecast.code}"></c:out>.png"
										height="70" width="70"
										alt="<c:out value="${forecast.text}"></c:out>"
										class="wx-weather-icon">
									<p class="wx-phrase">
										<c:out value="${forecast.text}"></c:out>
									</p>

									<p class="wx-temp">
										<c:out value="${forecast.high}"></c:out>
										<sup>°F</sup><span class="wx-label"></span>
									</p>
									<p class="wx-temp-alt">
										<c:out value="${forecast.low}"></c:out>
										<sup>°F</sup><span class="wx-label"></span>
									</p>

								</div>
							</div>
							<br>
							<hr>
						</c:forEach>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>



	</div>
</body>
</html>
