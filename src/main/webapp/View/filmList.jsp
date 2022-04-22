<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"  %>
<%@ page import="vo.*"  %>
<%@ page import="java.util.*"  %>
<%
	// 현재 페이지 1페이지 
	int currentPage=1;
	if(request.getParameter("currentPage") !=null){ // 이전 다음 링크를 통해서 왔다면 
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	
	// 보여줄 페이지 행수 
	int rowPerPage = 10; // 변경 가능
	
	// 보고 싶은 행의 갯수
	// 현재 페이지 currentPage 변경 -> beginRow로 
	int beginRow = (currentPage-1)*rowPerPage;
	
	// CustomerListDao 호출 -> Dao
	FilmListDao filmListDao = new FilmListDao();
	// CustomerList 호출 -> vo
	FilmList filmList = new FilmList();
	// selectCustomerListByPage -> 쿼리
	List<FilmList> list = filmListDao.selectFilmListByPage(beginRow,rowPerPage);
	// selectCustomerTotalRow -> 총 개수 페이지, 전체 페이지(페이징 작업) -> 마지막 페이지
	// 마지막 페이지 변수 값 저장 코드
	int lastPage = 0;
	int totalCount = filmListDao.selectFilmListTotalRow();
	// 마지막 페이지는 (전체 데이터 수 / 화면당 보여지는 데이터 수) 가 됨
	/*
	lastPage = totalCount / rowPerPage;
	if(totalCount % rowPerPage != 0) {
		lastPage++;
	}
	*/
	lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage)); 
	// 4.0 / 2.0 = 2.0 -> 2.0
	// 5.0 / 2.0 = 2.5 -> 3.0
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
				<th>FID</th>
				<th>title</th>
				<th>description</th>
				<th>category</th>
				<th>price</th>	
				<th>length</th>	
				<th>rating</th>	
				<th>actors</th>				
			</tr>
		</thead>
		<tbody>
			<%
				for(FilmList f : list) {
			%>
				<tr>
					<td><%=f.getFid()%></td>
					<td><%=f.getTitle()%></td>
					<td><%=f.getDescription()%></td>
					<td><%=f.getCategory()%></td>
					<td><%=f.getPrice()%></td>
					<td><%=f.getLength()%></td>
					<td><%=f.getRating()%></td>
					<td><%=f.getActors()%></td>
				</tr>
			<%
				}
			%>
				
		</tbody>

	</table>
	<!-- 페이징 -->
	<div>
		<%
			if(currentPage > 1) {
		%>
				<a href="<%=request.getContextPath()%>/ViewTable/filmList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%		
			}
		%>
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/ViewTable/filmList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html> 