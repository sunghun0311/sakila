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
	
	// SalesByFilmCategoryDao 호출 -> Dao
	SalesByFilmCategoryDao salesByFilmCategoryDao = new SalesByFilmCategoryDao();
	// SalesByFilmCategory 호출 -> vo
	SalesByFilmCategory salesByFilmCategory = new SalesByFilmCategory();
	// selectSalesByFilmCategoryByPage -> 쿼리
	List<SalesByFilmCategory> list = salesByFilmCategoryDao.selectSalesByFilmCategoryByPage(beginRow,rowPerPage);
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
				<th>Category</th>
				<th>TotalSales</th>		
			</tr>
		</thead>
		<tbody>
			<%
				for(SalesByFilmCategory s : list) {
			%>
				<tr>
					<td><%=s.getCategory()%></td>
					<td><%=s.getTotalSales()%></td>
				</tr>
			<%
				}
			%>			
		</tbody>
	</table>
</body>
</html> 