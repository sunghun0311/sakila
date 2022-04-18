<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.FilmDao" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("utf-8"); // 인코딩
	// filmInstockForm에서 값받아오기
	int filmId = Integer.parseInt(request.getParameter("filmId"));
	int storeId = Integer.parseInt(request.getParameter("storeId"));
	System.out.println(filmId+"<--filmId");
	System.out.println(storeId+"<--storeId");
	
	// Dao호출
	FilmDao filmDao = new FilmDao();
	Map<String, Object>map = filmDao.filmInStock(filmId, storeId); // Map
	List<Integer> list = (List<Integer>)map.get("list"); // List
	
	int count = 0;
	count = (int)map.get("count"); // 몇개가 남아있는지와 SQL count 저장
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film In Stock</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp">Home</a>
	
		<h1>Film In Stock</h1>
		<div><%=filmId %>번 영화가 <%=storeId %>번 가게에 <%=count %>개 남음</div>
		<%
			for(int id : list){
		%>
			<%=id %>번,
		<%
			}
		%>
		<div><%=count %>개 남음</div>
</body>
</html>