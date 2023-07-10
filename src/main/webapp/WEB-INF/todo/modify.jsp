<%--
  Created by IntelliJ IDEA.
  User: pyeonghwajeong
  Date: 2023/07/10
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Todo Modify/Remove
    </title>
</head>
<body>
<form id="form1" action="/todo/modify" method="post">
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}">
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}">
    </div>
    <div>
        <input type="date" name="finished" value="${dto.finished ? "checked" : ""}">
    </div>

    <div>
        <button type="submit">Modify</button>
    </div>
</form>

<form id="form2" action="/todo/remove" method="post">
    <div>
        <input type="hidden" name="tno" value="${dto.tno}" readonly>
    </div>

    <div>
        <button type="submit">Remove</button>
    </div>
</form>
</body>
</html>
