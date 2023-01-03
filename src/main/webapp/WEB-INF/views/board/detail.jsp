<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
</head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script>
	$(function() {
	})
</script>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header">${board.title}</div>
			<div class="card-body">
				<blockquote class="blockquote mb-0">
					<p>${board.contents}</p>
					<footer class="blockquote-footer">
						<fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm:ss"/>
					</footer>
				</blockquote>
			</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-2">
			<a href="/board/list" class="btn btn-primary me-md-2" type="button"><spring:message code="button.list"/></a>
			<a href="/board/edit/${board.boardSeq}" class="btn btn-primary" type="button"><spring:message code="button.edit"/></a>
		</div>
	</div>
</body>
</html>