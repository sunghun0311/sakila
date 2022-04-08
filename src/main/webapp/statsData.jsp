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
	// 6. 총매출
	List<Map<String,Object>> amount = statsDataDao.amount();	
	// 7. staff별 매출
	List<Map<String,Object>> salesByStaff = statsDataDao.salesByStaff();	
	// 8. staff별 년도별 매출
	List<Map<String,Object>> annualSalesBystaff = statsDataDao.annualSalesBystaff();
	
	// 9. store별 매출
	List<Map<String,Object>> salesByStore = statsDataDao.salesByStore();
	// 10. 카테고리별 영화
	List<Map<String,Object>> moviesByCategory = statsDataDao.moviesByCategory();


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
				<td><%=m.get("rentalRate")%></td>
				<td><%=m.get("rentalRateCount")%></td>
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
				<td><%=m.get("rating")%></td>
				<td><%=m.get("ratingCount")%></td>
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
				<td><%=m.get("name")%></td>
				<td><%=m.get("languageCount")%></td>
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
				<td><%=m.get("length2")%></td>
				<td><%=m.get("lengthCount")%></td>
			</tr>
	<%
		}
	%>
	
	<h1>6. 총 매출</h1>
		<tr>
			<th>총 매출</th>
		</tr>
	<%
		for(Map<String, Object> m : amount) {
	%>
			<tr>
				<td><%=m.get("sum")%></td>
			</tr>
	<%
		}
	%>
	
	
	<h1>7. staff별 매출</h1>
		<tr>
			<th>스테프 아이디</th>
			<th>총 매출</th>
		</tr>
	<%
		for(Map<String, Object> m : salesByStaff) {
	%>
			<tr>
				<td><%=m.get("staffId")%></td>
				<td><%=m.get("sum")%></td>
			</tr>
	<%
		}
	%>
	
	<h1>8. staff별 년도 매출</h1>
		<tr>
			<th>스테프 아이디</th>
			<th>총 매출</th>
		</tr>
	<%
		for(Map<String, Object> m : annualSalesBystaff) {
	%>
			<tr>
				<td><%=m.get("staffId")%></td>
				<td><%=m.get("year")%></td>
				<td><%=m.get("sum")%></td>
			</tr>
	<%
		}
	%>
	
	<h1>9. store별 매출</h1>
		<tr>
			<th>스토어 아이디</th>
			<th>총 매출</th>
		</tr>
	<%
		for(Map<String, Object> m : salesByStore) {
	%>
			<tr>
				<td><%=m.get("stordId")%></td>
				<td><%=m.get("sum")%></td>
			</tr>
	<%
		}
	%>
	
		<h1>10. 카테고리별 매출</h1>
		<tr>
			<th>스토어 아이디</th>
			<th>총 매출</th>
		</tr>
	<%
		for(Map<String, Object> m : moviesByCategory) {
	%>
			<tr>
				<td><%=m.get("categoryId")%></td>
				<td><%=m.get("name")%></td>
				<td><%=m.get("cnt")%></td>
			</tr>
	<%
		}
	%>
	
	</table>
</body>
</html>