<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>전체목록</h2>

		<c:forEach var="v" items="${datas}">
			<c:set var="m" value="${v.m}" />

			<h3>
				[${m.memid}] ${m.msg} &gt;&gt; [좋아요 ${m.favcount} | 댓글  
				${m.replycount} | ${m.day}] 

			</h3>
			<ol>

				<c:forEach var="r" items="${v.rlist}">
					<li>${r.memid}>>${r.rmsg}[${r.day}]</li>

				</c:forEach>
			</ol>
			</c:forEach>
</body>
</html>