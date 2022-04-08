<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<% 
	StatsDataDao statsDataDao = new StatsDataDao();	
	
	//1. customer별 총 amount
	List<Map<String, Object>> amountByCoustomer = statsDataDao.amountByCoustomer();
	// 2. 금액별 film 수
	List<Map<String, Object>> filmCountByRentalRate = statsDataDao.filmCountByRentalRate();
	// 3. 상영등급별 film 수
	List<Map<String,Object>> filmCountByRating = statsDataDao.filmCountByRating();
	// 4. language 별 영화수
	List<Map<String,Object>> languageFilmCount = statsDataDao.languageFilmCount(); 
	// 5. length별 영화 개수
	List<Map<String,Object>> lengthFilmCount = statsDataDao.lengthFilmCount();



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>1. amountByCoutomere(180이상)</h1>
	<table border="1">
		<tr>
			<th>고객아이디</th>
			<th>고객이름</th>
			<th>총지불액</th>
		</tr>
	<%
		for(Map<String, Object> m : amountByCoustomer) {
	%>
			<tr>
				<td><%=m.get("customerId")%></td>
				<td><%=m.get("name")%></td>
				<td><%=m.get("total")%></td>
			</tr>
	<%
		}
	%>
	
	<h1>2. rental_rate별 영화 갯수</h1>
		<tr>
			<th>대여료</th>
			<th>총 갯수</th>
		</tr>
	<%
		for(Map<String, Object> m : filmCountByRentalRate) {
	%>
			<tr>
				<td><%=a.get("rentalRate")%></td>
				<td><%=a.get("rentalRateCount")%></td>
			</tr>
	<%
		}
	%>
	
	<h1>3. rating별 영화 갯수</h1>
		<tr>
			<th>등급</th>
			<th>총 갯수</th>
		</tr>
	<%
		for(Map<String, Object> m : filmCountByRating) {
	%>
			<tr>
				<td><%=c.get("rating")%></td>
				<td><%=c.get("ratingCount")%></td>
			</tr>
	<%
		}
	%>
	
	
	<h1>4. language별 영화 갯수</h1>
		<tr>
			<th>등급</th>
			<th>총 갯수</th>
		</tr>
	<%
		for(Map<String, Object> m : languageFilmCount) {
	%>
			<tr>
				<td><%=c.get("name")%></td>
				<td><%=c.get("languageCount")%></td>
			</tr>
	<%
		}
	%>
	
	
	<h1>5. length별 영화 갯수</h1> <!-- 구간과 기준을 정해서 -->
		<tr>
			<th>등급</th>
			<th>총 갯수</th>
		</tr>
	<%
		for(Map<String, Object> m : lengthFilmCount) {
	%>
			<tr>
				<td><%=c.get("length2")%></td>
				<td><%=c.get("lengthCount")%></td>
			</tr>
	<%
		}
	%>
	
	</table>
</body>
</html>