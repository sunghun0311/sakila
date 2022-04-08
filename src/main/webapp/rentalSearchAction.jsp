<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	// 받아온 값을 변수에 저장
	int storeId = -1;  // 데이터가 입력되지 않았을 때
	if(!request.getParameter("storeId").equals("")) { // 기본타입이므로 공백
		storeId = Integer.parseInt(request.getParameter("storeId"));
	} 
	String customerName = "";
	if(request.getParameter("customerName") != null) { // 참조타입이므로 null
		customerName = request.getParameter("customerName");
	} 
	
	String beginDate = "";
	if(request.getParameter("beginDate") != null) {
		beginDate = request.getParameter("beginDate");
	} 
	
	String endDate = "";
	if(request.getParameter("endDate") != null) {
		endDate = request.getParameter("endDate");
	} 
	
	// 디버깅
	System.out.println("가게번호:" +storeId+ "고객이름:" +customerName+ "시작날짜:" +beginDate);
	
	// 페이징 작업
	int currentPage = 1; // 현재페이지의 기본값이 1페이지
	if(request.getParameter("currentPage") != null) { // 이전, 다음 링크를 통해서 들어왔다면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+ "<-currentPage"); // 디버깅
	
	int rowPerPage = 10; // 보여주는 페이지
	int beginRow = (currentPage-1)*rowPerPage; // 현재페이지가 변경되면 beginRow도 변경된다.
	
	// Dao 호출
	RentalDao rentalDao = new RentalDao();
	List<Map<String,Object>> list = rentalDao.selectRentalSearchList(beginRow, rowPerPage, storeId, customerName, beginDate, endDate);

	// 마지막 페이지 변수 값 저장
	int lastPage = 0;
	int totalRow = rentalDao.totalRow(storeId, customerName, beginDate, endDate);	
	lastPage = totalRow / rowPerPage; // 나누었을 때
	if(totalRow % rowPerPage != 0) { // 나머지가 0이 아니면
			lastPage++; // +1
	}
	
	// lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage)); 
	// 4.0 / 2.0 = 2.0 -> 2.0
	// 5.0 / 2.0 = 2.5 -> 3.0


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rental search action</title>
</head>
<body>
	<h1>검색 결과 리스트</h1>
		<table dorder="1">
			<tr>
				<td>rental_id</td>
				<td>rental_date</td>
				<td>inventory_id</td>
				<td>r.customer_id</td>
				<td>r.return_date</td>
				<td>staff_id</td>
				<td>last_update</td>
				<td>name</td>
				<td>storeId</td>
				<td>filmId</td>
				<td>title</td>
			</tr>	
<%
			for(Map<String,Object> m : list) {
%>
			<tr>
				<td><%=m.get("r.rental_id")%></td>
				<td><%=m.get("r.rental_date")%></td>
				<td><%=m.get("r.inventory_id")%></td>
				<td><%=m.get("r.customer_id")%></td>
				<td><%=m.get("r.return_date")%></td>
				<td><%=m.get("r.staff_id")%></td>
				<td><%=m.get("r.last_update")%></td>
				<td><%=m.get("name")%></td>
				<td><%=m.get("storeId")%></td>
				<td><%=m.get("filmId")%></td>
				<td><%=m.get("title")%></td>			
			</tr>			
<%			
		}
%>
	</table>
		<!-- 페이징 작업 -->
		<div>
			<%
				if(currentPage>1) {
			%>
					<a herf=
	
	
	
	
	
		</div>
	
</body>
</html>
