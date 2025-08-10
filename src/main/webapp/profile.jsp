<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<%
  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login");
    return;
  }
%>

<html>
<head>
  <title>Profile</title>
</head>
<body>
<h2>Welcome, <c:out value="${sessionScope.username}"/></h2>
<p>This is your profile page.</p>
<form method="post" action="logout">
  <input type="submit" value="Logout">
</form>
</body>
</html>
