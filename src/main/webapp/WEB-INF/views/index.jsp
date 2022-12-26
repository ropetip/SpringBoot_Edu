<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script>
function test() {
	$("#result").text("계산 중..");
	
	$.ajax({
	    type : 'get',            	// 타입 (get, post, put 등등)
	    url : '/getBtnStatus',        // 요청할 서버url
	    async : true,            // 비동기화 여부 (default : true)
	    dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
	    data : {  // 보낼 데이터 (Object , String, Array)
	        "num" : "1000000"
      	},
	    success : function(result) { // 결과 성공 콜백함수
	        $("#result").text(result);
	        $("#result2").text("");
	    },
	    error : function(request, status, error) { // 결과 에러 콜백함수
	    	$("#result").text("error :"+ error);
	    }
	});
	
	$.ajax({
	    type : 'get',            	// 타입 (get, post, put 등등)
	    url : '/getData',        // 요청할 서버url
	    async : true,            // 비동기화 여부 (default : true)
	    dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
	    data : {  // 보낼 데이터 (Object , String, Array)
	        "num" : "1000000"
      	},
	    success : function(result) { // 결과 성공 콜백함수
	        $("#result").text(result);
	        $("#result2").text("");
	    },
	    error : function(request, status, error) { // 결과 에러 콜백함수
	    	$("#result").text("error :"+ error);
	    }
	});
}

function test2(){
	$("#result2").text("gggggg");
}
</script>
</head>
<body>
	<form action="/file/save" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile"/>
		<button type="submit">파일업로드</button>
	</form>
	
	<div style="margin-top: 15px;">
		<input type="button" onclick="test();" value="테스트"/>
		<span id="result"></span>
	</div>
	
	<div style="margin-top: 15px;">
		<input type="button" onclick="test2();" value="테스트2"/>
		<span id="result2"></span>
	</div>
</body>
</html>