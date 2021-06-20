<!-- 필터를 적용해도 한글 깨짐이 있을 경우
charset과 pageEncoding을 utf-8로 변경 --> 

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/shop/front/member/join" method="post">
		<label for="uname"><b>이름</b></label> 
		<input type="text" placeholder="Enter Username" name="name">
		
		<label for="uname"><b>나이</b></label> 
		<input type="text" placeholder="Enter age" name="age">
		<button type="submit">회원가입</button>
	</form>
</body>
</html>