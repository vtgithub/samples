<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="/pages/css/style.css">
</head>
<body>
<h1>test page</h1>
<jsp:include page="/pages/test-header.jsp"></jsp:include>
code: ${testDto.code}
(EL JSP)
<br/>
value: ${testDto.value}
(EL JSP)
<br/>
<c:if test="${isTest == true}">
    <c:out value="${testValue}"></c:out>
</c:if>
<p class="TitleMaker">
    this is an html comes from a servlet by forward function
</p>
<jsp:include page="/pages/test-footer.jsp"></jsp:include>
</body>
</html>
