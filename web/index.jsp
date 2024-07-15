<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/views/others/layuiError.jsp" %>
<html>
<head>
    <title>项目起始页</title>
</head>
<body>
<%
    request.getRequestDispatcher("/app/course?method=showCourseGroupByType").forward(request, response);
%>
</body>
</html>