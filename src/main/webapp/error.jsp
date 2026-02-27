<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn29
  Date: 26. 2. 26.
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Error Page</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
    <table>
      <tbody>
        <tr>
          <th>status_code</th>
          <td><c:out value="${status_code}" default="500"/></td>
        </tr>
        <tr>
          <th>exception_type</th>
          <td><c:out value="${exception_type}" /></td>
        </tr>
        <tr>
          <th>message</th>
          <td><c:out value="${message}" default="알 수 없는 오류"/> </td>
        </tr>
        <tr>
          <th>exception</th>
          <td><c:out value="${exception}"/></td>
        </tr>
        <tr>
          <th>request_uri</th>
          <td><c:out value="${request_uri}"/></td>
        </tr>
      </tbody>
    </table>
  </body>
</html>
