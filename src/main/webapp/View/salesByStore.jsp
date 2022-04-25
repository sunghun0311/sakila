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
	
	// SalesByStoreDao 호출 -> Dao
	SalesByStoreDao salesByStoreDao = new SalesByStoreDao();
	// SalesByStore 호출 -> vo
	SalesByStore salesByStore = new SalesByStore();	// selectSalesByStoreByPage -> 쿼리
	List<SalesByStore> list = salesByStoreDao.selectSalesByStoreByPage(beginRow,rowPerPage);
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
				<th>store</th>
				<th>manager</th>
				<th>totalSales</th>	
			</tr>
		</thead>
		<tbody>
			<%
				for(SalesByStore n : list) {
			%>
				<tr>
					<td><%=n.getStore()%></td>
					<td><%=n.getManager()%></td>
					<td><%=n.getTotalSales()%></td>					
				</tr>
			<%
				}
			%>
				
		</tbody>
	</table>	
</body>
</html> 