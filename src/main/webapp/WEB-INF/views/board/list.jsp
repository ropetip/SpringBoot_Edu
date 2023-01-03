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
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link active"aria-current="page" href="/">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/community"><spring:message code="menu.community"/></a></li>
					<li class="nav-item"><a class="nav-link" href="/notice"><spring:message code="menu.notice"/></a></li>
					<li class="nav-item"><a class="nav-link" href="/faq"><spring:message code="menu.faq"/></a></li>
					<li class="nav-item"><a class="nav-link" href="/inquiry"><spring:message code="menu.inquiry"/></a></li>
				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>

	<h2>총갯수: ${totalCount}</h2>
	<form id="form" method="get" action="/board/list">
		<input type="hidden" name="boardType" value="COMMUNITY" />
		<div class="row mb-3">
			<label for="title" class="col-sm-2 col-form-label"><spring:message
					code="search.keyword" /></label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="keyword" name="keyword"
					value="${param.keyword}"
					placeholder="<spring:message code="search.keyword"/>를 <spring:message code="placeholder.required"/>">
			</div>
		</div>
		<button type="submit" class="btn btn-primary">
			<spring:message code="button.search" />
		</button>
	</form>

	<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-2">
		<a href="/board/form" class="btn btn-primary me-md-2" type="button"><spring:message
				code="button.form" /></a>
	</div>

	<table class="table caption-top">
		<caption>List of users</caption>
		<thead>
			<tr>
				<th scope="col">#</th>
				<td><spring:message code="board.title" /></td>
				<td><spring:message code="board.viewCount" /></td>
				<td><spring:message code="board.regDate" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList}" varStatus="status">
				<tr>
					<th scope="row">${status.count}</th>
					<td><a href="/board/detail/${board.boardSeq}" />${board.title}</td>
					<td>${board.viewCount}</td>
					<td><fmt:formatDate value="${board.regDate}"
							pattern="yyyy.MM.dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(boardList) == 0}">
				<tr>
					<td colspan="4"><spring:message code="msg.board.empty" /></td>
			</c:if>
		</tbody>
	</table>
</body>
</html>