<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"  %>
<%@ page import="vo.*"  %>
<%@ page import="java.util.*"  %>
<%
	// 현재 페이지 1페이지 
	int currentPage=1;
	if(request.getParameter("currentPage") !=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	} // 이전과 다음 링크를 통해서 들어왔다면
	
	// 보여줄 페이지 행수 
	int rowPerPage = 10; // 변경 가능
	
	// 보고 싶은 행의 갯수
	// 현재 페이지 currentPage 변경 -> beginRow로 
	int beginRow = (currentPage-1)*rowPerPage;
	
	// StaffListDao 호출 -> Dao
	StaffListDao staffListDao = new StaffListDao();
	// StaffList 호출 -> vo
	StaffList staffList = new StaffList();	// selectStaffListByPage -> 쿼리
	List<StaffList> list = staffListDao.selectStaffListByPage(beginRow, rowPerPage);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customerList</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	<h1>Customer List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>staffId</th>
				<th>name</th>
				<th>address</th>
				<th>zipCode</th>	
				<th>phone</th>	
				<th>city</th>
				<th>country</th>	
				<th>storeId</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(StaffList s : list) {
			%>
				<tr>
					<td><%=s.getId()%></td>
					<td><%=s.getName()%></td>
					<td><%=s.getAddress()%></td>	
					<td><%=s.getZipCode()%></td>
					<td><%=s.getPhone()%></td>	
					<td><%=s.getCity()%></td>
					<td><%=s.getCountry()%></td>
					<td><%=s.getSid()%></td>						
				</tr>
			<%
				}
			%>
				
		</tbody>
	</table>	
</body>
</html> 