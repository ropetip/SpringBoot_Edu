<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script>
$(function() {
	var $form = $('#form');
	$form.bind('submit', function() {
		
		$.ajax({
			url: '/board/save'
			,type: 'post'
			,data: $form.serialize()
			,dataType: 'json'
			,success: function(data) {
				if(data.code == "SUCCESS") {
					alert("저장하였습니다.");
				} else {
					alert(data.message);
				}
			}
		});
		return false;
	});
})
</script>
<body>
	<div class="container">
		<form id="form">
			<input type="hidden" name="boardType" value="COMMUNITY"/>
		    <div class="row mb-3">
		        <label for="title" class="col-sm-2 col-form-label"><spring:message code="board.title"/></label>
		        <div class="col-sm-10">
		        	<input type="text" class="form-control" id="title" name="title" value="${board.title}" placeholder="<spring:message code="placeholder.required"/>">
		        </div>
		    </div>
			<div class="row mb-3">
			    <label for="contents" class="col-sm-2 col-form-label"><spring:message code="board.contents"/></label>
			    <div class="col-sm-10">
			        <textarea class="form-control" id="contents" name="contents" value="${board.contents}" placeholder="<spring:message code="placeholder.required"/>">${board.contents}</textarea>
			    </div>
			</div>
			<button type="submit" class="btn btn-primary"><spring:message code="button.save"/></button>
	    </div>
	</form>
</body>
</html>