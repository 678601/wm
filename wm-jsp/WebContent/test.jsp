<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

 function loading()

{ document.write("JavaScript输出"); }

</script>
</head>
<body onLoad="loading()">
<!-- 注释掉onLoad 方法，才显示下面内容 -->
 <%
    out.println("Hello World!");
%>
</body>
</html>