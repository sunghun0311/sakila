<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>INDEX</h1>
	<ol>
		<li><a href="<%=request.getContextPath()%>/storeList.jsp">Store List</a></li>
		<li><a href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
	</ol>
	<h3>뷰</h3>
	<ol>
		<!-- view 7개 리스트 -->
		<li><a href="<%=request.getContextPath()%>/ActorInfoList.jsp">ActorInfoList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/CustomerList.jsp">CustomerList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/FilmList.jsp">FilmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/NicerButSlowerFilmList.jsp">NicerButSlowerFilmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/SalesByFilmCategory.jsp">SalesByFilmCategory(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/SalesByStore.jsp">SalesByStore(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/staffList.jsp">staffList(view)</a></li>								
	</ol>
	<h3>프로시저</h3>
	<ol>
		<li><a href="<%=request.getContextPath()%>/filmInStock.jsp">filmInStock</a></li>
		<li><a href="<%=request.getContextPath()%>/filmNotInStock.jsp">filmNotInStock</a></li>
		<li><a href="<%=request.getContextPath()%>/rewardReport.jsp">rewardReport</a></li>
	</ol>
	<h3>상세검색</h3>
	<ol>
		<li><a href="<%=request.getContextPath()%>/filmSearchForm.jsp">필름 상세검색</a></li>
		<li><a href="<%=request.getContextPath()%>/rentalSearchForm.jsp">대여 상세검색</a></li>
	</ol>
	<h3>통계 정보</h3>
	<ol>
		<li><a href="<%=request.getContextPath()%>/StatsData.jsp">StatsData</a></li>
	</ol>
</body>
</html>