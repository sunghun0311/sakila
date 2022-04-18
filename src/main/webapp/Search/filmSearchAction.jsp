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
	String category = request.getParameter("category");// nulll이 아닌 공백
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
	
	int rowPerPage = 10; // 변경이 가능하다.
	int beginRow = (currentPage-1)*rowPerPage;
	
	// FilmDao
	FilmDao filmDao = new FilmDao();
	// list에 젖아
	List<FilmList> list = filmDao.selectFilmListSearch(beginRow, rowPerPage, category, rating, price, length, title, actor); 
	System.out.println(list.size()); // 0
	
	// 마지막 페이지 변수 값 저장 코드
	int lastPage = 0;
	int totalCount = filmDao.selectFilmListTotalRow(category, rating, price, length, title, actor);
	lastPage = totalCount / rowPerPage;
	if(totalCount % rowPerPage != 0) {
		lastPage++;
	}
	System.out.println(lastPage + "<-- Ac.lastPage");
	
	/*
	lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage)); 
	// 4.0 / 2.0 = 2.0 -> 2.0
	// 5.0 / 2.0 = 2.5 -> 3.0
	*/
			
			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<h1>film Search</h1>
		<div>
			<h2><a href="<%=request.getContextPath()%>/index.jsp">Home</a></h2>
		</div>
		<table border="1">
			<tr>
					<td>FID</td>
					<td>Title</td>
					<td>Description</td>
					<td>Category</td>
					<td>Price</td>
					<td>Length</td>
					<td>Rating</td>
					<td>Actors</td>
			</tr>
			<%
				for(FilmList f : list) {
			%>
			<tr>
					<td><%=f.getFid() %></td>
					<td><%=f.getTitle() %></td>
					<td><%=f.getDesciption() %></td>
					<td><%=f.getCategory() %></td>
					<td><%=f.getPrice() %></td>
					<td><%=f.getLength() %></td>
					<td><%=f.getRating() %></td>
					<td><%=f.getActors() %></td>
			</tr>		
			<%
				}
			%>
		</table>
		<div>
				<%
					if(currentPage > 1){
				%>
						<a href="<%=request.getContextPath()%>/search/filmSearchAction.jsp?currentPage=<%=currentPage-1 %>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">이전</a>	
				<%
					}
				%>
				<%
					if(currentPage < lastPage){
				%>
						<a href="<%=request.getContextPath()%>/search/filmSearchAction.jsp?currentPage=<%=currentPage + 1 %>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">다음</a>	
				<%
					}
				%>	
		</div>
</div>
</body>
</html>