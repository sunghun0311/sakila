<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="dao.ActorInfoDao" %>
<%@ page import ="vo.ActorInfo" %>
<%

	
	// 페이징
	int currentPage = 1; // 현재페이지의 기본값이 1페이지
	if(request.getParameter("currentPage") != null){ // 이전, 다음 링크를 통해서 들어왔다면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage + "<-- currentPage");
	}
	int rowPerPage = 5; // 보여지는 페이지의 수(변경가능)
	int beginRow = (currentPage-1)*rowPerPage; // 현재페이지가 변경되면 beginRow도 변경된다.
	 
	int totalRow = 0;
	int lastPage = 0;
	
	// ActorInfoDao 호출
	List<ActorInfo> list = new ArrayList<ActorInfo>();
	ActorInfoDao actorInfoDao = new ActorInfoDao();
	list = actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage);
	
	totalRow = actorInfoDao.selectActorInfoTotalRow();
	System.out.println(totalRow+"<--selectActorInfoTotalRow");
	// 마지막 페이지 구하기
	lastPage = totalRow / rowPerPage; // 마지막 페이지는 -> 전체 데이터수 / 화면당 보여지는 데이터 수
	if(totalRow % rowPerPage != 0){
		lastPage++;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp" >index</a>
	<table border="1">
			<thead>
				<tr>
					<th>actorId</th>
					<th>firstName</th>
					<th>lastName</th>
					<th>filmInfo</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(ActorInfo a : list) {
				%>
				<tr>
					<td><%=a.getActorId() %></td>
					<td><%=a.getFirstName() %></td>
					<td><%=a.getLastName() %></td>
					<td><%=a.getFilmInfo() %></td>				
				</tr>
						<%
								}
						%>
			</tbody>
	</table>
	<!-- 페이지 부분 -->
	<div>
		<%
			if(currentPage > 1) {
		%>
				<a href="<%=request.getContextPath() %>/actorInfoList.jsp?currentPage=<%=currentPage-1 %>">이전</a>
		<%
			}
		%>
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath() %>/actorInfoList.jsp?currentPage=<%=currentPage+1 %>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html>