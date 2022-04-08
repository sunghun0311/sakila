<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%

	// 페이징
	int currentPage = 1;
	System.out.println(currentPage+"<- currentPage");
	if(request.getParameter("currentPage") !=null){ // 이전 다음 링크를 통해 들어왔다면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	// 값을 받아와서 변수에 저장
	String category = request.getParameter("categoryName");// nulll이 아닌 공백
	String rating = request.getParameter("rating");
	double price = -1; // price데이터가 입력되지 않았을 때
	if(!request.getParameter("price").equals("")) {
		price = Double.parseDouble(request.getParameter("price"));
	}
	int length = -1;  // length데이터가 입력되지 않았을 때
	if(!request.getParameter("length").equals("")) {
		length = Integer.parseInt(request.getParameter("length"));
	} 
	String title = request.getParameter("title");
	String actor = request.getParameter("actor");
	
	int beginRow = 1;
	int rowPerPage = 10;
	
	FilmDao filmDao = new FilmDao();
	List<FilmList> list = filmDao.selectFilmListSearch(beginRow, rowPerPage, category, rating, price, length, title, actor); 
	System.out.println(list.size()); // 0
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<%
			for(FilmList f : list) {
		%>
				<td><%=f.getFid() %></td>
				<td><%=f.getTitle() %></td>
				<td><%=f.getDesciption() %></td>
				<td><%=f.getCategory() %></td>
				<td><%=f.getPrice() %></td>
				<td><%=f.getLength() %></td>
				<td><%=f.getRating() %></td>
				<td><%=f.getActors() %></td>
				
		<%
			}
		%>
	</table>

</body>
</html>