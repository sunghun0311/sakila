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
	
	// NicerButSlowerFilmListDao 호출 -> Dao
	NicerButSlowerFilmListDao nicerButSlowerFilmListDao = new NicerButSlowerFilmListDao();
	// NicerButSlowerFilmList 호출 -> vo
	NicerButSlowerFilmList nicerButSlowerFilmList = new NicerButSlowerFilmList();
	// selectNicerButSlowerFilmListByPage -> 쿼리
	List<NicerButSlowerFilmList> list = nicerButSlowerFilmListDao.selectNicerButSlowerFilmListByPage(beginRow,rowPerPage);
	// selectNicerButSlowerFilmListTotalRow -> 총 개수 페이지, 전체 페이지(페이징 작업) -> 마지막 페이지
	// 마지막 페이지 변수 값 저장 코드
	int lastPage = 0;
	int totalCount = nicerButSlowerFilmListDao.selectNicerButSlowerFilmListTotalRow();
	// 마지막 페이지는 (전체 데이터 수 / 화면당 보여지는 데이터 수) 가 됨
	
	lastPage = totalCount / rowPerPage;
	if(totalCount % rowPerPage != 0) {
		lastPage++;
	}
	
	
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
				for(NicerButSlowerFilmList n : list) {
			%>
				<tr>
					<td><%=n.getFid()%></td>
					<td><%=n.getTitle()%></td>
					<td><%=n.getDescription()%></td>
					<td><%=n.getCategory()%></td>
					<td><%=n.getPrice()%></td>
					<td><%=n.getLength()%></td>
					<td><%=n.getRating()%></td>
					<td><%=n.getActors()%></td>
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
				<a href="<%=request.getContextPath()%>/View/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%		
			}
		%>
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/View/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html> 